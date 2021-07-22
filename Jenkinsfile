pipeline {
    agent any 
    environment {
        SONARQUBE_SERVER = "SonarQube"
        BACKEND_FOLDER = ""
        TEST_FOLDER = "build/test-results/test"
        DOCKERHUB_CREDENTIALS = credentials('rodolfato-dockerhub')
        SPRING_DB_URL = credentials('database-url')
        SPRING_DB_USERNAME = credentials('database-username')
        SPRING_DB_PASSWORD = credentials('database-password')
    }
    stages {
        stage("Inicio del Pipeline") {
            steps {
                echo "Iniciando Pipeline: ${env.JOB_NAME}" 
            }
        }
        
        stage("Pruebas unitarias y de integración con JUnit"){            
            steps{
                dir("${env.WORKSPACE}/${env.TEST_FOLDER}") {
                    echo 'Limpiando carpeta de resultados.'
					sh 'touch test-results.xml'
					sh 'rm *.xml'
				}
                catchError(buildResult: "SUCCESS", stageResult: "FAILURE") {
                	dir("${env.WORKSPACE}") {
                        sh "chmod +x ./gradlew"
						sh "./gradlew test"
					}
            	}
                dir("${env.WORKSPACE}/${env.TEST_FOLDER}") {
                    echo "Formateando el resultado de los tests realizados."
					junit "*.xml"
				}
            }
        }
        stage("Analisis de SonarQube") {
            steps {
				dir("${env.WORKSPACE}") {
					withSonarQubeEnv("${env.SONARQUBE_SERVER}") {
						sh "chmod +x ./gradlew"
						sh "./gradlew sonarqube"
    					}
				}
  		    }
        }
        stage("Creacion de container de aplicacion y subida a dockerhub"){
            steps{
                dir("${env.WORKSPACE}"){
                    echo 'Construyendo .jar de aplicacion'
                    sh 'chmod +x ./gradlew'
                    sh './gradlew bootJar'
                }
                dir("${env.WORKSPACE}"){
                    echo 'Ejecutando Dockerfile'
                    sh 'docker build -t rodolfato/microservicio1 .'
                }
                dir("${env.WORKSPACE}"){
                    echo 'Login a Dockerhub'
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
                dir("${env.WORKSPACE}"){
                    echo 'Push imagen a Dockerhub'
                    sh 'docker push rodolfato/microservicio1'
                }
            }
        }
        stage("Deployment on GCP Compute Engine"){
            steps{
                def runContainer = 'docker run -p 3000:3000 -d -name diplomados-front rodolfato/microservicio3'
                sshagent(credentials: ['GCP_DEPLOYMENT_SERVER']){
                    sh 'ssh -o StrictHostKeyChecking=no rodolfoandresm_gmail_com@34.85.179.249 ${runContainer}'
                }
            }

        }
        stage("Fin del Pipeline") {
            steps {
                echo "Finalizando Pipeline: ${env.JOB_NAME}" 
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}