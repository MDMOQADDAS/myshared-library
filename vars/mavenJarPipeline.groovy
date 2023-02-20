def call(Map config){
    node{
    
       stage("Build"){
        git 'https://github.com/MDMOQADDAS/mavenJarPipeline.git'
        sh 'mvn package'

       }

        stage("Test"){
            sh 'mvn test'
        }

         stage("Push"){
            sh 'docker build -t moqaddas/mavanapplication:$EXECUTOR_NUMBER .'
            sh 'docker login https://docker.io -u $dockerUser -p $dockerpassword'
            sh 'docker push moqaddas/mavanapplication:$EXECUTOR_NUMBER'
        }
    }
}