def call(Map config){
    node{

        stage("Install"){
            git 'https://github.com/MDMOQADDAS/reactJsPipeline.git'
           
           
        }
    
       stage("Build"){
       
        sh 'docker build -t moqaddas/reactapplication:$BUILD_NUMBER .'

       }

        stage("Test"){
            sh "docker rm -f reactapp || date"
            sh "docker run -d --name reactapp -p 80:3000 moqaddas/reactapplication:$BUILD_NUMBER"
            sh "IP_ADDR=`docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' reactapp`"
            sh "curl $IP_ADDR | grep Moqaddas"
        }

         stage("Push"){
           
           
           withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass}  https://docker.io"
            }

          


        }
    }
}