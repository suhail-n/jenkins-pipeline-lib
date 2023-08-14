def call(config = [:]) {
    dockerImage = config.registry + ":$BUILD_NUMBER"
    sh "docker build -t ${dockerImage} ."
}