def call(Map config){
    node{

        stage("Install"){
            git 'https://github.com/MDMOQADDAS/reactJsPipeline.git'
            sh "npm install"
        }
    
       stage("Build"){
       
        sh 'npm build'

       }

        stage("Test"){
            echo "testing"
        }

         stage("Push"){
            sh 'docker build -t moqaddas/reactapplication:$EXECUTOR_NUMBER .'
            sh 'docker login https://docker.io -u $dockerUser -p $dockerPassword'
            sh 'docker push moqaddas/reactapplication:$EXECUTOR_NUMBER'
        }
    }
}