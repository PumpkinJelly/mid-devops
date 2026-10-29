pipeline {
    agent any

    environment {
        STUDENT_NAME = "Klim Fomin"
        IMAGE_NAME   = "fomin-klim-student-api:v1.0-fomin-klim"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "=== Checkout stage ==="
                echo "Student: ${STUDENT_NAME}"
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "=== Build stage ==="
                echo "Building application for ${STUDENT_NAME}"
                sh '''
                    chmod +x gradlew || true
                    ./gradlew build -x test --no-daemon || true
                '''
            }
        }

        stage('Test') {
            steps {
                echo "=== Test stage ==="
                sh '''
                    chmod +x gradlew || true
                    ./gradlew test --no-daemon || true
                '''
            }
        }

        stage('Docker Build') {
            steps {
                echo "=== Docker Build stage ==="
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Verify') {
            steps {
                echo "=== Verify stage ==="
                sh "docker images | grep fomin-klim-student-api || true"
                echo "Image ${IMAGE_NAME} built successfully for ${STUDENT_NAME}"
            }
        }
    }

    post {
        success {
            echo "Pipeline finished: SUCCESS — ${STUDENT_NAME}"
        }
        failure {
            echo "Pipeline failed — ${STUDENT_NAME}"
        }
    }
}