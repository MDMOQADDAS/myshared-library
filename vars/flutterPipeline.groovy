def call(Map config){
    node{

        stage("Install"){
            git 'https://github.com/MDMOQADDAS/flutter-application.git'
        }
    
       stage("Build"){
       
        sh 'docker build -t moqaddas/pythonapplication:$BUILD_NUMBER .'

       }

        stage("Test"){
            sh "docker rm -f reactapp || date"
            sh "docker run -d --name reactapp -p 80:3000 moqaddas/pythonapplication:$BUILD_NUMBER"
        }

         stage("Push"){
           withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass}  https://docker.io"
             
             sh "docker logout"
            }

          


        }
    }
}