def call(Map pipelineParams){
    node{

        stage("SCM"){
            git "$pipelineParams.apprepo"

          
        }
    
       stage("Build"){
       
        sh "docker build -t $pipelineParams.registryUserName/$pipelineParams.containerImageName:$BUILD_NUMBER ."

       }

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