#!/bin/sh

docker build --tag=microservice-kubernetes-demo-apache apache
docker tag microservice-kubernetes-demo-apache $1/microservice-kubernetes-demo-apache:latest
docker push $1/microservice-kubernetes-demo-apache

docker build --tag=microservice-kubernetes-demo-catalog microservice-kubernetes-demo-catalog
docker tag microservice-kubernetes-demo-catalog $1/microservice-kubernetes-demo-catalog:latest
docker push $1/microservice-kubernetes-demo-catalog

docker build --tag=microservice-kubernetes-demo-customer microservice-kubernetes-demo-customer
docker tag microservice-kubernetes-demo-customer $1/microservice-kubernetes-demo-customer:latest
docker push $1/microservice-kubernetes-demo-customer

docker build --tag=microservice-kubernetes-demo-order microservice-kubernetes-demo-order
docker tag microservice-kubernetes-demo-order $1/microservice-kubernetes-demo-order:latest
docker push $1/microservice-kubernetes-demo-order
