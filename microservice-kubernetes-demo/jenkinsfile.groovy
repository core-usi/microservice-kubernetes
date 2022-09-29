def repoName
def repoURL
def branchName

pipeline{
    agent any
      tools {
     
      maven "maven3.6.3"
      }
    
     environment {
        buildnumber = "${BUILD_NUMBER}"
        jobName    = "${env.JOB_NAME}"
                    }
       
       stages{
       
         
         stage(' K8s Deploy'){
               steps{
                 sh '''
                   ls
                   pwd
                   cd ./microservice-kubernetes-demo
                   pwd
                   mvn clean package -DskipTests
                   
                   '''
              
                 
          } 
    }
       
         
         stage(' Docker build and push'){
               steps{
                withCredentials([usernamePassword(credentialsId: 'docker-sreeram', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                 sh ''' 
                  ls
                  pwd
                  cd ./microservice-kubernetes-demo
                  pwd
                  
                   docker login -u="${USERNAME}" -p="${PASSWORD}"
                   ./docker-build.sh ${USERNAME}
                   '''
                  cleanWs()
                  

}
                 
          } 
    }
         

         

    }
}
