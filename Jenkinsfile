pipeline {
    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:$PATH"
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Finish') {
            steps {
                echo 'Pipeline успешно завершён!'
            }
        }
    }
}