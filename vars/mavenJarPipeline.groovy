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
            sh 'docker build -t moqaddas/mavanapplication:$BUILD_NUMBER .'
            withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass}  https://docker.io"
            }
        }
    }
}