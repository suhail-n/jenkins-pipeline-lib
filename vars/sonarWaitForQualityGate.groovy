def call() {
    timeout(time: 1, unit: 'HOURS') {
        // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
        // true = set pipeline to UNSTABLE, false = don't
        // waitForQualityGate abortPipeline: true
        def qg = waitForQualityGate()
        if (qg.status != 'OK') {
            error "Pipeline aborted due to quality gate failure: ${qg.status}"
        }
    }
}
