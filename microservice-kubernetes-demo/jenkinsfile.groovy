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
                        mkdir ${BUILD_NUMBER}
                        cd ${BUILD_NUMBER}
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
                    cd ./microservice-kubernetes/microservice-kubernetes-demo
                   mvn clean package -DskipTests
                     cleanWs()
                     '''
          } 
    }
   /*        
    stage('Build Docker Image'){
      steps{
        
        sh 'docker build -t gcr.io/ford-project-319713/petclinic:${BUILD_NUMBER} . '
    }
    }
       
    stage('Push Docker Image to gcr.io'){
    steps{
        sh 'gcloud config set account gcr-jenkins-35@ford-project-319713.iam.gserviceaccount.com'
        sh 'gcloud auth configure-docker'
        sh 'docker push gcr.io/ford-project-319713/petclinic:${BUILD_NUMBER} >&1 | tee docker.txt'
        
             }
     }
    
     
       stage('Clean Docker images and workspace'){
    steps{
         sh 'docker rmi gcr.io/ford-project-319713/petclinic:${BUILD_NUMBER}'
        cleanWs()
             }
     }
         
   */   
         
         
         

    }
}
