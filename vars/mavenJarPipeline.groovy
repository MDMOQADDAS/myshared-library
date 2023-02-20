def call(Map config){
    node{
        stage("Dev"){
            echo "Is's a Dev Stage"
        }

       stage("Build"){
        echo "It's Build Stage"
       }
    }
}