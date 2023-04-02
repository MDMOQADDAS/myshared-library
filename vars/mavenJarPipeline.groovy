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
        sh '/usr/bin/bin/mvn package'
        //sh "mvn package"
       }
       /*
        Conducting the unit test for the application
        mvn we'll test the application give the analysis report of the code
        we are doing some function testing
        */
        stage("Test"){
            sh '/usr/bin/bin/mvn test'
        }
        /*
        Containerization tool : Docker
        Jenkins Credential Manager
        make sure to replace the credentialsId
        after testing pushing the artifact to the docker
        logout after pushing the artifact.
        */
         stage("Delivery"){
            sh "docker build -t $pipelineParams.registryUserName/$pipelineParams.containerImageName:$BUILD_NUMBER ."
            withCredentials([usernamePassword(credentialsId: "$pipelineParams.credentialsId", passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass}  "
             sh "docker push $pipelineParams.registryUserName/$pipelineParams.containerImageName:$BUILD_NUMBER"

            sh "docker logout"
            }
          
        }
    }
}