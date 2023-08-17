def call(Closure object) {
    Map config = [:]

    object.resolveStrategy = Closure.DELEGATE_FIRST
    object.delegate = config
    object()

    pipeline{
        agent {
            label config.agent
        }
        tools {
            maven 'maven-3.8.5'
            git 'Default'
        }
        stages{
            stage("Checkout the project"){
                steps{
                    echo "========Checkout Project========"
                    git branch : config.gitBranch, url: config.gitUrl
                }

            }
            stage('Build') {
                steps {
                    mavenCleanPackage()
                }
            }
             stage('Sonar Quality Check') {

                 steps {
                     mavenSonarScan config
                 }
             }
             stage("Quality Gate") {
                 steps {
                     sonarWaitForQualityGate()
                 }
             }
             stage("Building the image"){
                 steps{
                     buildDockerImage config
                 }
             }
        }
        post{
            always{
                echo "========always========"
            }
            success{
                echo "========pipeline executed successfully ========"
            }
            failure{
                echo "========pipeline execution failed========"
            }
        }
    }
}