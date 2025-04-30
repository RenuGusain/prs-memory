pipeline {
    agent any

    environment {
        IMAGE_NAME = 'prs-inmemory' // Replace with your Docker image name
        IMAGE_TAG = 'latest'
        DOCKER_REGISTRY = 'https://hub.docker.com/repositories/rgusain' // e.g., docker.io/yourusername or ECR repo
    }

    stages {
          stage('Clean Workspace')
           {
                    steps
                     {
                        cleanWs()
                    }
           }
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { return env.DOCKER_REGISTRY != '' }
            }
            steps {
                withDockerRegistry(credentialsId: 'dockerhub-creds', url: '') {
                    script {
                        docker.image("${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}
