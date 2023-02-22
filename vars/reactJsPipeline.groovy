def call(Map config){
    node{

        stage("Install"){
            git 'https://github.com/MDMOQADDAS/reactJsPipeline.git'
           
           
        }
    
       stage("Build"){
       
        sh 'docker build -t moqaddas/reactapplication:$EXECUTOR_NUMBER .'

       }

        stage("Test"){
            echo "testing"
        }

         stage("Push"){
           
           
           withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass}  https://docker.io"
            }

          


        }
    }
}