pipeline { 
    agent any
    tools { 
        jdk 'jdk8'
    }
    options {
            disableConcurrentBuilds()
            timeout(time: 2, unit: 'HOURS')
            timestamps()
    }
    environments {
        PATH = '/usr/bin:/sbin:/usr/sbin:/bin:$PATH'
    }
    stages { 
        stage('Build') { 
            steps {
                timeout(value: 20, unit: 'SECONDS') {
                    sh './gradlew clean'
                }
                timeout(value: 20, unit: 'MINUTES') {
                    sh './gradlew assemble'
                }
            }
        }
        stage('Build') {
                    steps {
                        timeout(value: 20, unit: 'SECONDS') {
                            sh './gradlew test'
                        }
                    }
        }
    }
    post {
            always {
                archive 'build/tests/*'
                junit allowEmptyResults: true, keepLongStdio: true, testResults: 'build/tests/*.xml'
                deleteDir()
            }
    }
}