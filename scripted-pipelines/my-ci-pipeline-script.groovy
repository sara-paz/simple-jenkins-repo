pipeline {
    agent any

    environment {
        GIT_SSH_COMMAND = 'ssh -o StrictHostKeyChecking=no' // Skip host key checking
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'basic-scripts', credentialsId: '7f3523bf-4b7b-49df-bb38-1574a7f2b2c8', url: 'https://github.com/sara-paz/simple-cd-flow'
            }
        }

        stage('Terraform init') {
            steps {
                dir('terraform') {
                    bat 'terraform init'
                }
            }
        }
        
        stage('Terraform apply') {
            steps {
                dir('terraform') {
                    bat 'terraform apply --auto-approve '
                }
            }
        }
        
        stage('Ansible') {
            steps {
                echo 'terraform done, ansible pending'
            }
        }
    }
}
