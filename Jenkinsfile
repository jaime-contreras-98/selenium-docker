pipeline {
    agent any
    stages {
            stage('Build Jar') {
                steps {
                    sh "mvn clean package -DskipTests"
                }
            }
            stage('Build Image') {
                steps {
                    sh 'docker build -t=jaimecontreras98/selenium .'
                }
            }
            stage('Push Image') {
                environment {
                    DOCKER_HUB = credentials('dockerhub-creds')
                }
                steps {
                    sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_PSW} --password-stdin'
                    sh 'docker push jaimecontreras98/selenium'
                }
            }
    }
    post {
        always {
            sh "docker logout"
        }
    }
}