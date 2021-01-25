pipeline {
    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git poll: true, branch: 'jenkinsFriendly', url: 'https://github.com/Uncle-Jules/Nordic-Motorhome-Eksamen.git'
                // Run Maven on a Unix agent.
                sh "mvn install -DskipTests clean package"
            }
        }
        stage('Test'){
            steps{
                sh 'mvn test'
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Deploy'){
            steps{
                sh 'kill $(ps -fC java | grep \'jenkins\' | grep \'NMP-1.0.0.jar\' | awk \'{print $2}\') || true'
            }
            post{
                success {
                    sh 'JENKINS_NODE_COOKIE=dontKillMe nohup java -jar /var/lib/jenkins/workspace/NMP-Pipeline/target/NMP-1.0.0.jar &'
                }
            }
        }
    }
}