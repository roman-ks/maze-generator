pipeline {
    agent {
        docker {
            image("gradle:jdk11")
        }
    }

    options{
        gitLabConnection('gitlab-con')
    }

    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
    }

    stages {
        stage('Build & Test') {
            steps {
                echo 'Building..'
                updateGitlabCommitStatus(name: 'build', state: 'pending')
                sh '''
                    gradle clean test
                '''
                updateGitlabCommitStatus(name: 'build', state: 'success')
            }
        }

        stage('Publish artifact') {
            steps {
                echo 'Publishing....'
                sh '''
                    gradle publish
                '''
            }
        }
    }
}