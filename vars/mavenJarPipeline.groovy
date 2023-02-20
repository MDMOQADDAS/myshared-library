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
            echo "Is's a Push Stage"
        }
    }
}