pipeline {
    agent {
        docker {
            image("gradle:jdk11")
        }
    }

    options{
        gitlabConnection('gitlab-con')
    }

    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
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