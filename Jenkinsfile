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

    post {
          failure {
            updateGitlabCommitStatus name: 'build', state: 'failed'
          }
          success {
            updateGitlabCommitStatus name: 'build', state: 'success'
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

        stage('Publish artifact') {
            when{ branch 'master' }
            steps {
                echo 'Publishing....'
                sh '''
                    version=$(git describe  --abbrev=0 --tag | cut -b  2-)
                    gradle -Pversion=$version publish
                '''
            }
        }

    }
}