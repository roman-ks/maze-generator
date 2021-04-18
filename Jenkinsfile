def getRepoURL() {
  sh "git config --get remote.origin.url > .git/remote-url"
  return readFile(".git/remote-url").trim()
}

void setBuildStatus(String message, String state) {
  repoUrl = getRepoURL()
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: repoUrl],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}

pipeline {
    agent {
        docker {
            image "${env.docker_reg_prefix}/gradle:jdk8"
            registryUrl "https://${env.docker_reg_host}/"
        }
    }

    post {
        failure {
            setBuildStatus("Build failed", "FAILURE");
        }
        success {
            setBuildStatus("Build succeeded", "SUCCESS");
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