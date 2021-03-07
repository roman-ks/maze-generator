pipeline {
    agent {
        docker {
            image("gradle:jdk11")
        }
    }

    stages {
        stage('Build & Test') {
            steps {
                echo 'Building..'
                sh '''
                    gradle clean test
                '''
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}