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








[springver]: https://img.shields.io/badge/dynamic/xml?label=Spring%20Boot&query=%2F%2A%5Blocal-name%28%29%3D%27project%27%5D%2F%2A%5Blocal-name%28%29%3D%27parent%27%5D%2F%2A%5Blocal-name%28%29%3D%27version%27%5D&url=https%3A%2F%2Fraw.githubusercontent.com%2Fartshishkin%2Fart-spring-core-devops-aws%2Fmaster%2Fpom.xml&logo=Spring&labelColor=white&color=grey
[licence]: https://img.shields.io/github/license/artshishkin/art-spring-core-devops-aws.svg
