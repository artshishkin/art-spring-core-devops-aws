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


[springver]: https://img.shields.io/badge/dynamic/xml?label=Spring%20Boot&query=%2F%2A%5Blocal-name%28%29%3D%27project%27%5D%2F%2A%5Blocal-name%28%29%3D%27parent%27%5D%2F%2A%5Blocal-name%28%29%3D%27version%27%5D&url=https%3A%2F%2Fraw.githubusercontent.com%2Fartshishkin%2Fart-spring-core-devops-aws%2Fmaster%2Fpom.xml&logo=Spring&labelColor=white&color=grey
[licence]: https://img.shields.io/github/license/artshishkin/art-spring-core-devops-aws.svg
