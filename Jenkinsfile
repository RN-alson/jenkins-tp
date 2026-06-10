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
                sh 'docker build -t alson2410/monapp:1.0.0 .'
            }
        }

        stage('Push DockerHub') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-pass', variable: 'DOCKER_PASS')]) {
                    sh 'docker login -u alson2410 -p $DOCKER_PASS'
                    sh 'docker push alson2410/monapp:1.0.0'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline terminé avec succès !'
        }
        failure {
            echo 'Pipeline échoué.'
            emailext(
                subject: "ÉCHEC Pipeline: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Le pipeline ${env.JOB_NAME} a échoué.

Build : #${env.BUILD_NUMBER}
URL   : ${env.BUILD_URL}

Consultez les logs pour plus de détails.
                """,
                to: 'randriamampiadanaalsoncedrick@gmail.com',
                credentialsId: 'gmail-smtp'
            )
        }
    }
}
