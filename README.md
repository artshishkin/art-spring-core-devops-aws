[![CircleCI](https://circleci.com/gh/artshishkin/art-spring-core-devops-aws.svg?style=svg)](https://circleci.com/gh/artshishkin/art-spring-core-devops-aws)
![Java CI with Maven](https://github.com/artshishkin/art-spring-core-devops-aws/workflows/Java%20CI%20with%20Maven/badge.svg)
![Spring Boot version][springver]
![Project licence][licence]

# Spring Framework DevOps on AWS

## Tutorial from SpringFrameworkGuru (Udemy)

#### Section 4: Using a MySQL Datasource

-  start mysql

```shell script
mysql -u root
```
Through MySQLShell (tested on Windows)
```shell script
\connect --mysql root@localhost
```
-  create database
```shell script
\sql CREATE DATABASE art_aws_study;
```
```mysql
CREATE DATABASE art_aws_study;
```
or
```mysql
CREATE SCHEMA 'art_aws_qa2_study' DEFAULT CHARACTER SET utf8;
```

#####  30. Encrypting Properties

1.  to encode `username` and `password` use `utils/jasypt-1.9.2/bin`
    -  `encrypt input=art_user password=jasypt_password`
    -  `encrypt input=art_pass password=jasypt_password`
2.  or use online Jasypt encoder
    -  [jasypt-online-encryption](https://www.devglan.com/online-tools/jasypt-online-encryption-decryption) 

#### Section 5: Continuous Integration

#####  36. Provisioning a Server on AWS

-  create a new key pair `dev_ops_course`
-  `dev_ops_course.pem` will be created
-  add security groups
    -  EC2 -> Security Groups -> something like that `sg-0c084e9a21817650e - launch-wizard-2` -> Edit inbound rules
    - add HTTP
    - add Custom TCP : 8080

#####  37. Installing Jenkins

-  `ssh -i C:\Users\Admin\Downloads\dev_ops_course.pem ec2-user@ec2-13-49-229-141.eu-north-1.compute.amazonaws.com`
-  `which wget`
-  `sudo su`
-  `yum install wget`
-  `mkdir install`
-  `cd install/`
-  [install java](https://tecadmin.net/install-java-8-on-centos-rhel-and-fedora/#)
-  [another install](https://www.digitalocean.com/community/tutorials/how-to-install-java-on-centos-and-fedora)
-  you need to go to the Oracle website, login and copy download link with licence info (`61ae65e088624f5aaa0b1d2d801acb16` currently)
-  (can't do this) `wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/otn/java/jdk/8u271-b09/61ae65e088624f5aaa0b1d2d801acb16/jdk-8u271-linux-x64.tar.gz"`
-  (can't do this) `wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/otn/java/jdk/8u271-b09/61ae65e088624f5aaa0b1d2d801acb16/jdk-8u271-linux-x64.rpm"`
-  `scp -i C:\Users\Admin\Downloads\dev_ops_course.pem C:\Users\Admin\Downloads\jdk-8u271-linux-x64.tar.gz    ec2-user@ec2-13-49-229-141.eu-north-1.compute.amazonaws.com:/home/ec2-user`
-  `tar xzf jdk-8u271-linux-x64.tar.gz` from install folder (before this copy archive to )
-  `ls -ltr`
-  `cp -r ./jdk1.8.0_271/ /opt/` - copy everything to `opt`
-  `alternatives --install /usr/bin/java java /opt/jdk1.8.0_271/bin/java 2`
-  `alternatives --config java`
-  `alternatives --install /usr/bin/jar jar /opt/jdk1.8.0_271/bin/jar 2`
-  `alternatives --install /usr/bin/javac javac /opt/jdk1.8.0_271/bin/javac 2`
-  `alternatives --set jar /opt/jdk1.8.0_271/bin/jar`
-  `alternatives --set javac /opt/jdk1.8.0_271/bin/javac`
-  `java -version`
-  [install Jenkins](https://www.jenkins.io/doc/book/installing/linux/#red-hat-centos)
-  `cd /home/ec2-user/install/`
-  `sudo wget -O /etc/yum.repos.d/jenkins.repo \
        https://pkg.jenkins.io/redhat-stable/jenkins.repo`
-  `sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key`
-  `yum install jenkins`
-  `service jenkins start` 
-  `ps -ef | grep jenkins`
-  in browser go to `http://ec2-13-49-229-141.eu-north-1.compute.amazonaws.com:8080`
-  or
-  go to `http://13.49.229.141:8080`
-  `vi /var/lib/jenkins/secrets/initialAdminPassword`
-  copy password
-  `Esc :q!` - exit from Vim
-  paste password into `Administrator password field`
-  Install suggested plugins
-  Create First Admin User
    -  Username:  admin
    -  Password:  admin
    -  Full Name:  Admin
    -  E-mail address:  Admin@example.com
-  Save and Flush
-  http://ec2-13-49-229-141.eu-north-1.compute.amazonaws.com:8080/

#####  38. How DNS Works

-  `nslookup cam-video-server.herokuapp.com` - to use default dns server
-  `nslookup cam-video-server.herokuapp.com 8.8.8.8` - to use dns.google server

#####  39. Using Route 53

-  registered new domain name `shyshkin.net`
-  created record (set) `jenkins.shyshkin.net`
-  go to `jenkins.shyshkin.net:8080`

#####  40. Setting up Apache with Jenkins

-  `ssh -i C:\Users\Admin\Downloads\dev_ops_course.pem ec2-user@jenkins.shyshkin.net`
-  `sudo su`
-  `yum install httpd`
-  `service httpd start`
-  `cd /etc/httpd/conf`
-  `vi httpd.conf`
-  [How to Install Jenkins Automation Server with Apache](https://www.howtoforge.com/tutorial/ubuntu-jenkins-automation-server/?__cf_chl_captcha_tk__=deab980ff85ac8f2804ebfd86af275c98635a0ce-1604502968-0-AY5COBD6-PLLrU6l9be5SXKOK3gf8mRj6C1dTz0oaeDcVT9o4BPSIbIcOsUhuoxyolRW7IZ73D7sPyTCOZV0rjgdRLNVOL1HXGEUZOfpLkXkU7Bjm_kAg_ad3d22ERAHCZ_PYzBcaduklXJT0Ijqqu7JTR7LlvN45eeIH9pIglhq43M7jH50F7SVWb9OGHJ2hHd4UzF-mVMOL55L5LB5NuT5yG50EnUNhgfXRXOutAK3OU2-pHdYV9B4lNiJxqr4FhRZoza9yr4PHG_umCCjh21nxU20hBedz-bTCVwUlIm_y8j16tzI5202juOBqmDfaJ5BV9BzjsLXICNUSWDu6O3R8vYznMDQI5cZRngN8T6obtibse-k0W1dPEVNqtgNIonn25nDObkNjLu7WPmK7vCz2jsp7WIKHiOXRYjLrOw_PptIJOOHLo4s-grsu0CGyPfHwbyBjQFIeDAu2gcFQ4aNRU8EQoVDPxwkrzEk3LSwYOh3rVNA0wVQFwevWIbda0IBq6lYs9wf5wPtoCJl05BR_UoAYd4fkpNXYiKT7dgX)
-  add config
```
<Virtualhost *:80>
    ServerName        jenkins.shyshkin.net
    ProxyRequests     Off
    ProxyPreserveHost On
    AllowEncodedSlashes NoDecode
 
    <Proxy http://localhost:8080/>
      Order deny,allow
      Allow from all
    </Proxy>
 
    ProxyPass         /  http://localhost:8080/ nocanon
    ProxyPassReverse  /  http://localhost:8080/
    ProxyPassReverse  /  http://jenkins.shyshkin.net/
</Virtualhost>
```
-  Esc `:wq` - write and quit from vi
-  `service httpd restart`
-  now if you go to `http://jenkins.shyshkin.net/` you will see `Service Unavailable`
-  security does not allow
-  `setsebool -P httpd_can_network_connect true` - allows
-  now we need to block 8080
-  go to `/etc/sysconfig`
-  `ls -ltr`
-  `vi jenkins`
-  `i` - insert mode
-  change `JENKINS_LISTEN_ADDRESS` to `127.0.0.1`
-  Esc `:wq`
-  `service jenkins restart`

#####  42. Creating SSH Keys

-  `whoami` - root))
-  `ps -ef | grep jenkins` - look Jenkins creates user `jenkins` with certain privileges
-  `su -s /bin/bash jenkins`
-  `whoami`  - jenkins;)
-  `pwd` - /etc/sysconfig
-  `cd` without params - to the user's default dir - `pwd` - `/var/lib/jenkins`
-  `mkdir .ssh`
-  `cd .ssh/`
-  generate key pair
-  `ssh-keygen -t rsa -C 'jenkins@example.com'`
-  `Enter file in which to save the key (/var/lib/jenkins/.ssh/id_rsa):` - Enter
-  `Enter passphrase (empty for no passphrase):` - Enter (leave blank)
-  `ls -ltr`
    -  `id_rsa` - private key
    -  `id_rsa.pub` - public key
-  `vi id_rsa.pub` 

#####  44. Configuring GitHub with SSH Keys

-  copy content of public key into 
-  GitHub -> repo-> Settings -> Deploy Keys -> Add deploy key

#####  45. Installing Git on Jenkins Server

-  `sudo yum install git`

#####  46. Configuring Jenkins Credentials

-  go to `jenkins.shyshkin.net` -> 
    -  login -> 
    -  Manage Jenkins -> 
    -  Manage Credentials -> 
    -  Jenkins -> 
    -  Global credentials ->
    -  Add credentials ->
        -  Kind:  SSH Username with private key
        -  Private key:  From the Jenkins master ~/.ssh (old version, now absent)
        -  Description: Jenkins SSH Keys
        -  ID: <empty>
        -  Passphrase: <empty>
        
#####  47. Configuring Maven on Jenkins

-  go to `jenkins.shyshkin.net` -> 
    -  login ->
    -  Manage Jenkins ->
    -  Global Tool Configuration ->
    -  Maven: set name `Maven 3.6.3`
    -  Apply -> Save
    
#####  48. Configuring Jenkins Maven Build

-  new Item (new Job)
-  Spring DevOps Project
-  FreeStyle Project ->
    -  GitHub project
        -  paste URL
    -  Source Code Management
        -  Git
            -  ~~Clone -> SSH copy~~ (does not work) 
            -  ~~`git@github.com:artshishkin/art-spring-core-devops-aws.git`~~ (does not work)
            -  `https://github.com/artshishkin/art-spring-core-devops-aws`
    -  Build Triggers
        -  GitHub hook trigger for GITScm polling
    -  Build
        -  Invoke top-level Maven targets
            -  Maven Version:  Maven 3.6.3
            -  Goals:  clean install
            
#####  49. Triggering a Jenkins Build

-  Poll SCM
    -  Schedule  `H/5 * * * *` - every 5 min            
        
#####  50. GitHub WebHooks

-  github -> Settings -> Webhooks -> Add webhook -> Payload url:
-  `http://jenkins.shyshkin.net/github-webhook/`


####  Section 6: Setting up Artifactory

#####  52. Introduction to Artifactory

#####  53. Assignment: Create Artifactory Server

#####  56. Installing Docker

-  `uname -r`
-  `yum update`
-  [Installing Docker on Amazon Linux 2](https://docs.aws.amazon.com/AmazonECS/latest/developerguide/docker-basics.html)
-  `sudo amazon-linux-extras install docker`
-  `service docker start`
-  `usermod -a -G docker ec2-user` - Add the ec2-user to the docker group so you can execute Docker commands without using sudo.
-  logout -> login
-  `ps -ef | grep docker` - check docker is running                                     
-  `docker info`
-  `docker run --rm hello-world` - run and remove image after - just test everything is fine

#####  57. Running Artifactory Image

-  [Docker Installation](https://www.jfrog.com/confluence/display/JFROG/Installing+Artifactory#InstallingArtifactory-DockerInstallation)
-  create jfrog home directory
    -  `mkdir /var/opt/jfrog/`
-  `export JFROG_HOME=/var/opt/jfrog`

-  Create your Artifactory home directory and an empty system.yaml file. (Note: the user creating the folder should be the user running docker run.)
    -  `mkdir -p $JFROG_HOME/artifactory/var/etc/` - (~~Windows: `mkdir $Env:JFROG_HOME/artifactory/var/etc/`~~ Does not work for me on Windows)
    -  `cd $JFROG_HOME/artifactory/var/etc/`
    -  `touch ./system.yaml`
    -  `chown -R 1030:1030 $JFROG_HOME/artifactory/var`
    -  `chmod -R 777 $JFROG_HOME/artifactory/var`
-  Start the Artifactory container
    -  `docker run --name artifactory -v $JFROG_HOME/artifactory/var/:/var/opt/jfrog/artifactory -d -p 8081:8081 -p 8082:8082 docker.bintray.io/jfrog/artifactory-oss:latest`
    -  (SFG implementation:)
    -  `sudo docker run -d --name jfrog_container -p 8081:8081 \
        -v /var/opt/jfrog/artifactory/data:/var/opt/jfrog/artifactory/data \
        -v /var/opt/jfrog/artifactory/logs:/var/opt/jfrog/artifactory/logs \
        -v /var/opt/jfrog/artifactory/etc:/var/opt/jfrog/artifactory/etc \
        docker.bintray.io/jfrog/artifactory-oss:latest`
-  **OR**
-  [Artifactory quick setup](https://jfrog.com/artifactory/install/)
    -  `docker volume create artifactory-data`
    -  `docker pull docker.bintray.io/jfrog/artifactory-oss:latest`
    -  `docker run -d --name artifactory -p 8082:8082 -p 8081:8081 -v artifactory-data:/var/opt/jfrog/artifactory docker.bintray.io/jfrog/artifactory-oss:latest`
    -  works with version `6.11.3` and above
-  versions `6.9.0`, `6.11.3` to `6.21.0` have ability to preconfigure Maven repositories and `settings.xml`
-  Need to update AWS Instance type of Jenkins from `t3.micro` to `t3.small`
-  Need to update AWS Instance type of Artifactory `t3.micro` to `t3.small`
 
#####  58. Assignment - Configure Virtual Host for Artifactory

-  `sudo su`
-  `yum install httpd`
-  `service httpd start`
-  `cd /etc/httpd/conf`
-  `vi httpd.conf`
-  add config
```
<Virtualhost *:80>
    ServerName        jenkins.shyshkin.net
    ProxyRequests     Off
    ProxyPreserveHost On
    AllowEncodedSlashes NoDecode
 
    <Proxy http://localhost:8082/>
      Order deny,allow
      Allow from all
    </Proxy>
 
    ProxyPass         /  http://localhost:8082/ nocanon
    ProxyPassReverse  /  http://localhost:8082/
    ProxyPassReverse  /  http://artifactory.shyshkin.net/
</Virtualhost>
```
-  Esc `:wq` - write and quit from vi
-  `service httpd restart`

#####  My implementation of Artifactory container

-  [Artifactory quick setup](https://jfrog.com/artifactory/install/)
    -  `docker volume create artifactory-data`    
    -  `docker run -d --name artifactory -p 8081:8081 -v artifactory-data:/var/opt/jfrog/artifactory docker.bintray.io/jfrog/artifactory-oss:6.21.0`
    
#####  60. Resolving Artifacts through Artifactory

1.  Start version 6.21.0 (has ability to preconfigure Maven repositories)
    -  set up Maven repositories
2.  Generate `settings.xml`
    -  Artifacts -> Set me Up -> Generate Settings
    -  Copy `settings.xml` content
    -  Go to project's `pom.xml` -> right mouse button -> create `settings.xml` (in .m2 repo)
    -  Paste settings' content
    -  Go to `Welcome, 'username'`
        -  Edit profile
        -  insert password -> Unlock
        -  Encrypted Password -> Copy it
        -  insert into `settings.xml`
3.  Create different profiles for:
    -  `local` - `artifactory_local`
    -  `art` - remote 192.168.1.41 - `artifactory_art`
    -  `aws` - remote on AWS server - `artifactory_aws`
4.  Resolve artifacts through Artifactory with profiles
    -  `mvn clean install -P artifactory_art`

#####  61. Deploying to Artifactory

1.  Add `distributionManagement` block to `pom.xml`
    -  go to `http://localhost:8081/artifactory/webapp/#/artifacts/browse/tree/General/libs-release-local` ->
    -  Set Me Up ->
    -  Copy `distributionManagement` for release versions
    -  insert into `pom.xml`
    -  do the same for `libs-release-local`
2.  Test with release version
    -  in `pom.xml` set version to 0.0.1
    -  `mvn deploy`
    
#####  62. Configuring Jenkins    
     
1.  create `settings.xml` in Jenkins
    -  `su -s /bin/bash jenkins` - login as user `jenkins`
        -  `cd` - go to jenkins_home
        -  `ls -a` or `ls -al` - see hidden files and directories
        -  `cd .m2`
        -  `vi settings.xml` -> paste content

    -  **OR**
    -  if you run docker jenkins image
        -  `docker run -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts`
        -  then
        -  `docker exec -it 8b835b099048 bash` (`docker exec -it vigorous_solomon bash`)
        -  `cd` - go to jenkins_home
        -  `ls -a` or `ls -al` - see hidden files and directories
        -  `cd .m2`
        -  `vi settings.xml` -> paste content
            -  if `vim` absent
            -  `exit`
            -  `docker exec -u root -it vigorous_solomon bash`
            -  `apt-get update`
            -  `apt-get install vim`
2.  Modify Jenkins settings
    -  login to Jenkins
    -  Spring DevOps Project ->
    -  Configure
    -  `clean deploy  -Partifactory_art`
    -  to have faster deploys disable tests
        -  Properties `skipTests=true`
    -  Build Now

#####  Test Artifactory and Jenkins on AWS

1.  Changes Instance Type of Artifactory to `t3.medium` (4GB memory)
2.  Increased max number of opened files
    -  `docker run -d --ulimit nofile=90000:90000 --name artifactory -p 8081:8081 -v artifactory-data:/var/opt/jfrog/artifactory docker.bintray.io/jfrog/artifactory-oss:6.21.0`
    -  was error
        ```
        ERROR: Max number of open files 1024, is too low. Cannot run Artifactory!
        ```
3.  Modified `httpd` settings
    -  `ProxyPass         /  http://localhost:8081/ nocanon`
    -  `ProxyPassReverse  /  http://localhost:8081/`
    -  `service httpd start`
4.  Change IP of `artifactory.shyshkin.net` in Route 53
5.  Copied Encrypted Password and inserted into `settings.xml`
6.  Copied `distributionManagement` section from Artifactory to `pom.xml`
7.  Start Jenkins EC2 instance
8.  Change `jenkins.shyshkin.net` IP in Route 53
9.  SSH to Jenkins EC2
    -  `service jenkins start`
    -  `service httpd start`
    -  `su -s /bin/bash jenkins`
        -  `cd` - go to jenkins_home
        -  `ls -a` or `ls -al` - see hidden files and directories
        -  `cd .m2`
        -  `vi settings.xml` -> paste content
10.  Login to Jenkins
11.  Change goal to `clean deploy -Partifactory_aws` ()

####  Section 7: Virtualized Cloud Deployment

#####  65. Assignment - Provision Database Server

- provision server (`t3.micro`)
- add CNAME for subdomain - `proddb.shyshkin.net`
- open firewall for port 3306 (`MYSQL/Aurora`)
- install docker
- start docker service
- start database

```shell script
docker run -d --name my-container-name /
-p 3306:3306
-v /var/lib/mysql:/var/lib/mysql /
-e MYSQL_ROOT_PASSWORD=spring_guru_root_pwd  /
mysql/mysql-server:tag
```
-  my implementation
```shell script
docker run -d --name proddb -p 3306:3306 -v /var/lib/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=artarkatesoft_root_pwd  mysql/mysql-server
```

#####  66. Configuring the Production Data Source

1.  check running images
    -  `sudo docker ps`
2.  connect to container
    -  `sudo docker exec -it proddb bash`
3.  connect mySQL
    -  `mysql -p`
4.  create database
    ```mysql
        CREATE DATABASE art_aws_study;
    ```
5.  create service user account (not worked for me)

```mysql
grant all privileges on art_aws_study.* to 'art_owner'@'localhost' identified by 'ArtPassword';
```
My Implementation
```mysql
create user 'art_owner'@'localhost' identified by 'ArtPassword';
grant all privileges on art_aws_study.* to 'art_owner'@'localhost';
```

#####  68. Assignment - Provision Application Server

#####  69. Installing Java

1.  `sudo yum ipdate`
2.  `sudo yum install java-1.8.0-openjdk`
3.  `java -version`
4.  `which java`


#####  Configure Instances to Start Automatically

-  Was trying but `httpd` does not work properly  
```shell script
#!/bin/bash
systemctl start httpd
systemctl enable httpd
systemctl start jenkins
```
-  made SSH to Jenkins EC2 instance and typed
    -  `sudo chkconfig httpd on`
    -  `sudo chkconfig jenkins on`
    
-  made SSH to MySQL EC2 instance and typed
    -  `sudo chkconfig docker on`    
    -  `docker update --restart unless-stopped proddb`
-  this command will ensure all currently running containers will be restarted unless stopped.
    -  `docker update --restart unless-stopped $(docker ps -q)`

-  made SSH to Artifactory EC2 instance and typed
    -  `sudo chkconfig httpd on`
    -  `sudo chkconfig docker on`
    -  `sudo service docker start`
    -  `docker start artifactory`
    -  `docker update --restart unless-stopped artifactory`
    -  `exit`

##### Association of Elastic IP with an Artifactory EC2 Instance 

To allocate an Elastic IP and associate it with an Amazon Web Services (AWS) instance, do the following:

1.  Open the AWS Management Console, click the EC2 link, and display the page associated with your region.
2.  Click the Elastic IPs link in the EC2 Dashboard.
3.  Click Allocate New Address and choose VPC or EC2 from the drop-down list, depending whether you're going to associate this IP with an instance in Amazon EC2-Virtual Private Cloud (VPC) or Amazon EC2-Classic, respectively. Click Yes, Allocate to confirm your choice.
4.  Right-click the newly created Elastic IP and choose Associate Address.
5.  Choose your desired EC2 instance from the drop-down list of running instances and click Associate.

#####  70. Installing Spring Boot Application

-  go to `http://artifactory.shyshkin.net/`
-  copy `full file path` of desired release (where Name of `jar` is) 
-  ssh to spring boot EC2
-  `which wget` (if absent `sudo yum install wget`)
-  `wget --user=admin --password=<EncryptedPasswordFromSettingsXML>  http://artifactory.shyshkin.net/artifactory/libs-release-local/com/artarkatesoft/awsstudy/art-spring-core-devops-aws/0.0.3/art-spring-core-devops-aws-0.0.3.jar`

#####  71. Running a Spring Boot Applicaiton

-  if you pass parameters to spring boot app you will see these parameters by typing
    -  `ps -ef`
    -  this is not secure
-  pass parameters through environment variables
```shell script
    export SPRING_DATASOURCE_URL='jdbc:mysql://proddb.shyshkin.net:3306/art_aws_study?serverTimezone=UTC&useSSL=false'
    export SPRING_DATASOURCE_USERNAME=art_owner
    export SPRING_DATASOURCE_PASSWORD=ArtPassword
```
-  these are  env variables of my shell. if I exit they will be gone.
    -  `env`
    -  `env | grep`
-  start app
    -  `java -jar ./art-spring-core-devops-aws-0.0.3.jar`
    -  **and we got an error**
```
java.sql.SQLException: null,  message from server: "Host '13.48.31.59' is not allowed to connect to this MySQL server"
```
-  modified `art_owner` privileges 
```mysql
DROP USER 'art_owner'@'localhost';
CREATE USER 'art_owner'@'%' identified by 'ArtPassword';
grant all privileges on art_aws_study.* to 'art_owner'@'%';
FLUSH PRIVILEGES;
```
-  started jar and got an error
```
2020-11-08 15:57:35.273  WARN 3360 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : SQL Error: 1146, SQLState: 42S02
2020-11-08 15:57:35.273 ERROR 3360 --- [           main] o.h.engine.jdbc.spi.SqlExceptionHelper   : Table 'art_aws_study.author' doesn't exist
```
-  modified start command to
    -  `java -jar ./art-spring-core-devops-aws-0.0.3.jar --spring.jpa.hibernate.ddl-auto=update`
-  go to 8080))

#####  72. Assignment Encrypt DB Password

```shell script
    export SPRING_DATASOURCE_URL='jdbc:mysql://proddb.shyshkin.net:3306/art_aws_study?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true'
    export SPRING_DATASOURCE_USERNAME=art_owner
    
    export SPRING_DATASOURCE_PASSWORD=ENC(N/cThOsjTyhFJAQaboS1pLyWwu/x18NB)
    export JASYPT_ENCRYPTOR_PASSWORD=jasypt_password

    java -jar ./art-spring-core-devops-aws-0.0.3.jar
```

#####  73. Deploying Spring Boot as a Linux Service

1.  create `application.properties` file in jar location
    -  copy content from `utils`
2.  use SFG instruction from `utils/springbootservice.txt`
3.  modify jar name
    -  `ExecStart=/bin/java -jar art-spring-core-devops-aws-0.0.3.jar`

####  Section 8: Amazon RDS

#####  76. Provision MySQL RDS Database on AWS

-  create database
-  MySQL
-  Template `Dev/Test`
-  Settings:
    -  DB instance identifier: `mysql-sfg-study`
    -  Master username: `art_owner_aws`
    -  Master password: `art_password`
-  DB instance size
    -  Burstable classes (includes t classes)
    -  choose `db.t3.micro`
-  Connectivity
    -  Public access: `Yes`
    -  VPC security group -> Create New
        -  New VPC security group name: `mysql-vpc-security-group`
-  Additional configuration:
    -  Database options
        -  Initial database name: `art_aws_study`
    -  Encryption
        -  Enable encryption: `false`
    -  Monitoring
        -  Enable Enhanced monitoring: `false`
-  Create Database

#####  77. Create RDS Profile for Spring Boot

#####  78. Assignment Deploy Spring Boot Application using RDS Database

1.  made release of a project - `0.0.4`
2.  used encrypted credentials - `added jasypt password into command line`
3.  used mysql-rds profile - added `-Dspring.profiles.active=mysql-rds` into command line
4.  configured IP address of security group to be able to connect to mysql 
5.  follow route git -> github -> jenkins -> arfifactory -> wget jar -> our service on ec2   

#####  Making Route 53 automatically change IP after EC2 instance restart (Amazon EC2 instance)

1.  Follow the instructions:
    -  [Amazon Route 53: How to automatically update IP addresses without using Elastic IPs](https://dev.to/aws/amazon-route-53-how-to-automatically-update-ip-addresses-without-using-elastic-ips-h7o)
2.  Add instance tags:
    -  instance -> Tags -> Manage Tags
    -  Add tag -> `AUTO_DNS_NAME` : `proddb.shyshkin.net`
    -  Add tag -> `AUTO_DNS_ZONE` : `Z06172933BKXLFPWKVBAG` 
        -  from Route 53 -> 
        -  Hosted zones -> 
        -  shyshkin.net -> 
        -  Hosted zone details
        -  Hosted zone ID
    -  Save
3.  Create shell script to update Route 53 IP
    -  connect to proddb EC2 instance though SSH
    -  `sudo su`
    -  `cd /var/lib/cloud/scripts/per-boot/`
    -  create shell with any name: `vi auto_dns.sh`
        ```shell script
        #!/bin/bash
        # Extract information about the Instance
        INSTANCE_ID=$(curl -s http://169.254.169.254/latest/meta-data/instance-id/)
        AZ=$(curl -s http://169.254.169.254/latest/meta-data/placement/availability-zone/)
        MY_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4/)
        
        # Extract tags associated with instance
        ZONE_TAG=$(aws ec2 describe-tags --region ${AZ::-1} --filters "Name=resource-id,Values=${INSTANCE_ID}" --query 'Tags[?Key==`AUTO_DNS_ZONE`].Value' --output text)
        NAME_TAG=$(aws ec2 describe-tags --region ${AZ::-1} --filters "Name=resource-id,Values=${INSTANCE_ID}" --query 'Tags[?Key==`AUTO_DNS_NAME`].Value' --output text)
        
        # Update Route 53 Record Set based on the Name tag to the current Public IP address of the Instance
        aws route53 change-resource-record-sets --hosted-zone-id $ZONE_TAG --change-batch '{"Changes":[{"Action":"UPSERT","ResourceRecordSet":{"Name":"'$NAME_TAG'","Type":"A","TTL":300,"ResourceRecords":[{"Value":"'$MY_IP'"}]}}]}'
        ```
    -  make script executable: `chmod +x auto_dns.sh`
    -  useful commands to debug:
        -  see logs: `cat /var/log/cloud-init.log`   
        -  with filter: `cat /var/log/cloud-init.log | grep auto_dns`
4.  Configure security
    -  Sevices ->
    -  Security, Identity, & Compliance ->
    -  IAM ->
    -  Policies -> create policy:
        -  JSON:
        ```
       {
           "Version": "2012-10-17",
           "Statement": [
               {
                   "Effect": "Allow",
                   "Action": "ec2:DescribeTags",
                   "Resource": "*"
               },
               {
                   "Effect": "Allow",
                   "Action": "route53:ChangeResourceRecordSets",
                   "Resource": "arn:aws:route53:::hostedzone/<INSERT-HOSTED-ZONE-ID>"
               }
           ]
       }
        ```
        -  Review policy
            -  Name: `art_dns_auto`
            -  create policy
    -  Roles -> create role:
        -  Select type of trusted entity: `AWS service`
        -  Choose a use case: `EC2`
        -  Next: Permissions ->
        -  enable `art_dns_auto`
        -  Next: Tags ->
        -  Next: Review ->
        -  Role name: `ART_AUTO_DNS_ROLE`
    -  Instances -> jenkins
        -  Actions ->
        -  Security ->
        -  Modify IAM Role ->
        -  Choose IAM Role: `ART_AUTO_DNS_ROLE`
        -  Save
5.  Restart instance
    -  stop instance
    -  start instance    
 
#####  Making Route 53 automatically change IP after EC2 instance restart (Red Hat EC2 instance)

1.  Steps almost the same
2.  Difference:
    -  need to install AWS CLI
        -  [Install, Update, and Uninstall the AWS CLI version 1 on Linux](https://docs.aws.amazon.com/cli/latest/userguide/install-linux.html)
        -  **or Version 2**
    -  first of all it needs to
        -  [install python](https://tecadmin.net/install-python-2-7-on-centos-rhel/)
        -  `ln -s /usr/local/bin/python2.7 /usr/bin/python`
        -  install make
    -  `ln -s /usr/local/bin/aws /usr/bin/aws`
    -  `aws --version` 
    
[springver]: https://img.shields.io/badge/dynamic/xml?label=Spring%20Boot&query=%2F%2A%5Blocal-name%28%29%3D%27project%27%5D%2F%2A%5Blocal-name%28%29%3D%27parent%27%5D%2F%2A%5Blocal-name%28%29%3D%27version%27%5D&url=https%3A%2F%2Fraw.githubusercontent.com%2Fartshishkin%2Fart-spring-core-devops-aws%2Fmaster%2Fpom.xml&logo=Spring&labelColor=white&color=grey
[licence]: https://img.shields.io/github/license/artshishkin/art-spring-core-devops-aws.svg
