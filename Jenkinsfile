pipeline {
    agent any

    tools {
        maven 'maven'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Code récupéré depuis GitHub'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t alson2410/monapp:latest -t alson2410/monapp:${BUILD_NUMBER} .'
            }
        }

        stage('Push DockerHub') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-pass', variable: 'DOCKER_PASS')]) {
                    sh 'docker login -u alson2410 -p $DOCKER_PASS'
                    sh 'docker push alson2410/monapp:latest'
                    sh 'docker push alson2410/monapp:${BUILD_NUMBER}'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/'
                sh 'kubectl rollout status deployment/monapp --timeout=60s'
                sh 'kubectl get pods -l app=monapp'
            }
        }
    }

    post {
        success {
            echo 'Pipeline termine avec succes !'
            emailext(
                subject: "OK Pipeline: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Le pipeline ${env.JOB_NAME} s'est termine avec succes.\n\nBuild : #${env.BUILD_NUMBER}\nURL   : ${env.BUILD_URL}",
                to: 'randriamampiadanaalsoncedrick@gmail.com',
                from: 'randriamampiadanaalsoncedrick@gmail.com',
                replyTo: 'randriamampiadanaalsoncedrick@gmail.com',
                mimeType: 'text/plain',
                recipientProviders: []
            )
        }
        failure {
            echo 'Pipeline echoue.'
            emailext(
                subject: "ECHEC Pipeline: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Le pipeline ${env.JOB_NAME} a echoue.\n\nBuild : #${env.BUILD_NUMBER}\nURL   : ${env.BUILD_URL}\n\nConsultez les logs pour plus de details.",
                to: 'randriamampiadanaalsoncedrick@gmail.com',
                from: 'randriamampiadanaalsoncedrick@gmail.com',
                replyTo: 'randriamampiadanaalsoncedrick@gmail.com',
                mimeType: 'text/plain',
                recipientProviders: []
            )
        }
    }
}
