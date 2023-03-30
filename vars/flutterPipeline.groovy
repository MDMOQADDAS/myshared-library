def call(Map config){
    node{

        stage("SCM"){
            git 'https://github.com/MDMOQADDAS/flutter-application.git'
        }
    
       stage("Build"){
       
        sh '/usr/bin/flutter/bin/flutter build apk'

       }

        stage("Test"){
            sh "/usr/bin/flutter/bin/flutter test"
            
        }

         stage("Delivery"){
            echo "Code will be release to github"
        }
    }
}