def call(Map config){
    node{
    
       stage("SCM"){
        git 'https://github.com/MDMOQADDAS/mavenJarPipeline.git'
        

       }
       stage("Build"){
        sh 'mvn package'
       }

        stage("Test"){
            sh 'mvn test'
        }

         stage("Delivery"){
            sh 'docker build -t moqaddas/mavanapplication:$BUILD_NUMBER .'
            withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass}  https://docker.io"
            }
        }
    }
}