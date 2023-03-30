def call(Map config){
    node{

        stage("SCM"){
            git 'https://github.com/MDMOQADDAS/flutter-application.git'
        }
    
       stage("Build"){
       
        sh 'flutter build apk'

       }

        stage("Test"){
            sh "flutter test"
            
        }

         stage("Delivery"){

        }
    }
}