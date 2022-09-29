def loadValuesYaml(){
  def valuesYaml = readYaml (file: './metadata.yaml')
  return valuesYaml;
}
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
        stage('SCM Checkout'){
            steps{
                script{

                
               
                    

                         sh '''
                        ls
                       echo "before clone"
                        pwd
                        git_repo="https://github.com/core-usi/microservice-kubernetes.git"
 
                        git clone $git_repo
                        echo "after clone"
                        ls
                       
                       
                                       
                        '''
                    
                
             
             }
         }
          



        } 
         
         stage(' Maven Clean Package'){
               steps{
                 sh '''
                    cd ./microservice-kubernetes-demo
                   mvn clean package -DskipTests
                   
                   '''
                 
          } 
    }
       
         
         stage(' Docker build and push'){
               steps{
                withCredentials([usernamePassword(credentialsId: 'docker-sreeram', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                  sh 'cd ./microservice-kubernetes-demo'
                  sh 'docker login -u="${USERNAME}" -p="${PASSWORD}"'
                  ./docker-build.sh ${USERNAME}
                  cleanWs()
                  

}
                 
          } 
    }
         

         

    }
}
