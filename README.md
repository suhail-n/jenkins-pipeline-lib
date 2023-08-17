# jenkins-pipeline-lib
A Jenkins library made for testing the extended shared library feature


Build maven pipeline

```groovy
@Library('shared-lib') _

buildMavenPipeline {
    agent = 'java agent'
    gitBranch = 'develop'
    gitUrl = 'my git url'
    sonarToken = 'sonar-token'
    installationName = 'sonarqube-10-agent'
    registry = 'docker registry name'
}
```