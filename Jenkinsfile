pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                echo 'Repo cloné avec succès'
            }
        }
        stage('Build') {
            steps {
                sh 'echo "Build OK"'
            }
        }
        stage('Test') {
            steps {
                sh 'echo "Tests OK"'
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "Deploy OK"'
            }
        }
    }
}
