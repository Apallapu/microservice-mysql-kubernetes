# microservice-mysql-kubernetes
microservice-mysql-kubernetes

step to deploy the micro service in kubernate
===========================================

1.create the spring boot project


2.create the docker file for spring boot project

Example docker file below
=========================
Dockerfile
FROM openjdk:10.0.2-jre-slim COPY target/mybus-domain-service-0.0.1-SNAPSHOT.jar . CMD /usr/bin/java -Xmx400m -Xms400m -jar mybus-domain-service-0.0.1-SNAPSHOT.jar EXPOSE 9191


3.Build the spring boot project with below docker commands

example:

docker build -t mybus-domain-service .

above command build the spring boot project and create docker image in local system and how to check the docker images in local system

example: 

docker images ls


4.push the docker image into docker hub,

before pushing the docker image into docker hub ,create the tag the docker images

example:

docker tag mybus-service ankammapallapu/mybus-domain-service:latest

login the docker hub by using the below commands

docker login -u=userid -p=password

then push the docker image into docker hub by using below command

docker push ankammapallapu/mybus-domain-service:latest




5.its time to deploy the micro service in kubernate.

6. install the minikube and kubectl command

create the pod and service yaml file for spring boot project
==========================================================

pod.deploy.yaml
==================
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mybus-domain-service
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mybus-domain-service
  template:
    metadata:
      labels:
        app: mybus-domain-service
    spec:
      containers:
      - name: mybus-service
        image: ankammapallapu/mybus-domain-service:latest


example of service.yaml file
============================
service.yaml
apiVersion: v1
kind: Service
metadata:
  name: mybus-domain-service
spec:
  selector:
    app: mybus-domain-service
  ports:
  - name: http
    port: 9191
    nodePort: 30096
  type: NodePort

  
 create the mysql-deploy.yaml file
 example
 mysql-deploy.yaml
 apiVersion: v1
kind: Pod
metadata:
  name: mysql
  labels:
    app: mysql
spec:
  containers:
   - name: mysql
     image: mysql:8.0.17
     env:
      # Use secret in real life
      - name: MYSQL_ROOT_PASSWORD
        value: testone
      - name: MYSQL_DATABASE
        value: testdb
---
kind: Service
apiVersion: v1
metadata:
  name: database
spec:
  selector:
    app: mysql
  ports:
  - port: 3306
  type: ClusterIP

 
 
 
deploy the micro service application in kubernate with below commands


kubectl apply -f pod-deploy.yaml 

kubect apply -f service.yaml


check the below url for application working or not

http://minikubeipaddress:30096/swagger-ui.html




install the winpty in cygwin
==============================
1.curl -L https://github.com/rprichard/winpty/releases/download/0.4.3/winpty-0.4.3-cygwin-2.8.0-x64.tar.gz>winpty.tar.gz

2.type ls command

3.cd bin/

4.type ls command

5.cp */usr/local/bin/

6.type "winpty"

install the mysql client
==============================
below command for install and run the mysql client in cygwin


kubectl run -it --rm --image=mysql:8.0.17 --restart=Never mysql-client -- mysql -h database -ptestone



using kubectl get all command we can see the mysql-client pod in list


@LAPTOPTK568D /cygdrive/c/microservice-tutorial/class-1/mybus-domain-service
$ winpty kubectl exec -it mysql-client sh

# mysql -h database -uroot -ptestone
mysql: [Warning] Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 63
Server version: 8.0.17 MySQL Community Server - GPL

Copyright (c) 2000, 2019, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show tables
    -> ;
    
    
ERROR 1046 (3D000): No database selected
mysql> show databases;


+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| testdb             |
+--------------------+
5 rows in set (0.01 sec)

mysql> use testdb


Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables

    -> ;
+------------------+
| Tables_in_testdb |
+------------------+
| ticket           |
+------------------+
1 row in set (0.01 sec)

mysql> select * ticket;


ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'ticket' at line 1
mysql> select * from ticket
    -> ;
+-----------+------+---------+-------------+-----------+
| ticket_id | cost | name    | ticket_from | ticket_to |
+-----------+------+---------+-------------+-----------+
|         1 |  450 | ankamma | hyderabad   | ongole    |
|         2 |  450 | ankamma | hyderabad   | nellore   |
+-----------+------+---------+-------------+-----------+
2 rows in set (0.01 sec)

LAPTOPTK568D /cygdrive/c/microservice-tutorial/class-1/mybus-domain-service

$ minikube ip

192.168.99.105

