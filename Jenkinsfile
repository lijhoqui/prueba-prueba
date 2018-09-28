pipeline {
    agent {
        label 'Slave_Induccion'
    }
         
    tools {
	   jdk 'JDK7_Centos'
	   gradle 'Gradle4.5_Centos'
	}
	
    stages {
        
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git<------------'
                    //checkout([$class: 'GitSCM', branches: [[name: 'master']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: '7fe28495-6f45-4577-8c7b-dce727e78f14', url: 'https://github.com/lijhoqui/prueba_artifactory']]])
                    git branch: 'master', url: 'https://github.com/lijhoqui/prueba_artifactory'
            }
        }
        
          stage('Compile') {
           steps {
				   echo '------------>Build<------------'
					sh "gradle compileJava"
			   }
       }
		
     
        stage('Publish') {
            when {
                anyOf { branch 'master' }
            }
            steps{
                echo '------------>Publicar en Artifactory<------------'
                script{
                    def server = Artifactory.server 'ar7if4c70ry@c318a'

                    def uploadSpec = '''
                        {"files": [{
                            "pattern": "build/libs/sura-am-steps-pruebas-aceptacion-1.0-all.jar",
                            "target": "libs-release-local/sura-am/build/"
                        }]}'''
                    
                    def buildInfo = server.upload(uploadSpec)

                    server.publishBuildInfo(buildInfo)
                }
            }
            
        }
      
    }
   
}
