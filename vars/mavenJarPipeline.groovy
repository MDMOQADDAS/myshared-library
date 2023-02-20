def call(Map config){
    node{
        echo "Hello ${config.applicationName}."
    }
}