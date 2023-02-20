def call(Map config){
    node{
        echo "Hello React ${config.applicationName}."
    }
}