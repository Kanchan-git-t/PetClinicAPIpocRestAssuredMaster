pipeline {
	environment {
    		def APP_NAME = "api-testing-poc-restAssured-master"
    		def GIT_REPO_NAME = "Kanchan-git-t"
    		def DEPLOY_ENV = "dev"
	}
    	agent { dockerfile true }
	stages {
		stage('Initialize') {
			steps {
				echo 'Placeholder.'
				//sh 'rm -f /var/lib/jenkins/workspace/FEI_PetClinic_RestAssuredapi/build/reports/tests/test/index.html'
				sh """
					JOB_NAME=${env.JOB_BASE_NAME}
					rm -rf /var/lib/jenkins/workspace/\$JOB_NAME/build/reports/tests/test
					cp -R /app/build/reports/tests/test/ /var/lib/jenkins/workspace/\$JOB_NAME/api-testing-testng/build/reports/tests/
				   """

				 //cp -r /var/lib/jenkins/workspace/Job1/allure-results/ /var/lib/jenkins/workspace/Job1_AllureReport/
				//sh 'cp /app//build/reports/tests/test/index.html /var/lib/jenkins/workspace/${env.BUILD_TAG}'

			}
		}

    	}
	post {
		success {
		    sh 'echo "Your test execution is done and reports will be available at - http://tnt-aks-automator.eastus.cloudapp.azure.com/build/reports/tests/test/index.html" in sometime.'
		}
		failure {
		    echo "Please check logs for more details."
		}
    	}
}