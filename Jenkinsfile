pipeline {
    agent {
        docker {
            image("gradle:jdk11")
        }
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh '''
                    gradle clean build
                '''
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}