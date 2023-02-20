def call(Map config){
    node{

        stage("Install"){
            git 'https://github.com/MDMOQADDAS/reactJsPipeline.git'
           
            sh "npm install"
        }
    
       stage("Build"){
       
        sh 'npm run build'

       }

        stage("Test"){
            echo "testing"
        }

         stage("Push"){
            sh 'docker build -t moqaddas/reactapplication:$EXECUTOR_NUMBER .'
           
           withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) {
    // the code here can access $pass and $user
}

           sh "docker login -u ${user} -p ${pass}  https://docker.io"


        }
    }
}