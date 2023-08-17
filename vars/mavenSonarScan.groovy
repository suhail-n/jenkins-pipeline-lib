def call(config = [:]) {
    withSonarQubeEnv(credentialsId: config.sonarToken, installationName: config.installationName ?: 'sonarqube-10-agent') {
        sh 'mvn sonar:sonar -Dsonar.analysisCache.enabled=false'
    }
}
