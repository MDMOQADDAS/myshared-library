def call(Map pipelineParams){
    node{

        stage("SCM"){
            git "$pipelineParams.apprepo"
        }
    
       stage("Build"){
       
        sh "sudo /root/Downloads/flutter/bin/flutter build $pipelineParams.platform"

       }

        stage("Test"){
            sh "sudo /root/Downloads/flutter/bin/flutter test"
            
        }

         stage("Delivery"){
            echo "Code will be release to github"
        }
    }
}