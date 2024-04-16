pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JAVA_HOME'
    }
    stages {
        stage('Build Maven') {
            steps {
                script {
                    // Parcourir les répertoires des microservices et exécuter Maven pour construire
                    def microservices = ['association-service','discovery', 'config-service', 'donation-service', 'enrollment', 'merchant-service', 'user-service', 'geteway']

                    microservices.each { service ->
                        dir(service) {
                            checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sofiawh/foodhero-parent']]])
                            bat "mvn clean install"
                        }
                    }
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    // Parcourir les répertoires des microservices et exécuter les tests avec Maven
                    def microservices = ['discovery', 'association-service', 'config-service', 'donation-service', 'enrollment', 'merchant-service', 'user-service', 'geteway']

                    microservices.each { service ->
                        dir(service) {
                            bat  "mvn test"
                        }
                    }
                }
            }
        }
        stage('Build and Deploy with Docker Compose') {
            steps {
                // Construction et déploiement avec Docker Compose
                bat 'docker-compose -f docker-compose.yml up -d --build'
            }
        }
    }
}