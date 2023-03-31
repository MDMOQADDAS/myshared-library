def call(Map pipelineParams){
    // It'll take all the parameter as a map data strcture
    //it'll run on the node, it can be manage by the jenkins admin
    node{
        //here, the source code repository come as a parameter
        //developer have to give the source code repo
        stage("SCM"){
            git "$pipelineParams.apprepo"
        }
    //Build Stage - Build the application and containerized it
    //developer have to give the container registery username
    //Because container registry store the image like this naming convention
    //give the contaienr image name as a parameter
    //version it'll asign on the fly, as a build number
       stage("Build"){ 
        sh "docker build -t $pipelineParams.registryUserName/$pipelineParams.containerImageName:$BUILD_NUMBER ."
       }
    // Test - Stage, it'll run the application if not ok then it'll intrupt the pipeline
    
        stage("Test"){
            sh "docker rm -f $pipelineParams.applicationName || date"
            sh "docker run -d --name $pipelineParams.applicationName -p 80:3000 $pipelineParams.registryUserName/$pipelineParams.containerImageName:$BUILD_NUMBER"
        }

         stage("Delivery"){
           withCredentials([usernamePassword(credentialsId: '4636fbc0-97d9-4b53-a309-7121c3d91395', passwordVariable: 'pass', usernameVariable: 'user')]) 
           {
             sh "docker login -u ${user} -p ${pass} "
             
             // sh "docker push $pipelineParams.registryUserName/$pipelineParams.containerImageName:$BUILD_NUMBER"
              sh "docker logout"
            }
           


        }
    }
}