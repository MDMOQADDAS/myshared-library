def call(Map pipelineParams){
      /*
        pipelineParams: map data structure
        make sure to give the correct key name
        1. apprepo
        2. registryUserName
        3. containerImageName
        Run in any node
    */
    node{
         /*
            SCM: pull the code to build the application
            we are using maven for the java application
            we are using git as a SCM
        */

        stage("SCM"){
            git "$pipelineParams.apprepo"
        }
         /*
        Build the code using maven
        you can set the maven path.
        make sure to check the slave node for building java application
        default one is '/usr/bin/bin/mvn'
        required mvn 3.9.0 or above version to build the code
       */
    
       stage("Build"){
       
        sh "sudo /root/Downloads/flutter/bin/flutter build $pipelineParams.platform"

       }
       /*   
       Testing the application
       conducting the unit test

       */

        stage("Test"){
            sh "sudo /root/Downloads/flutter/bin/flutter test"
            
        }

         stage("Delivery"){
            echo "Code will be release to github..."
        }
    }
}