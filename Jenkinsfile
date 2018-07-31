pipeline { 
    agent any
    tools { 
        jdk 'jdk8'
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
            }
        }
    }
}