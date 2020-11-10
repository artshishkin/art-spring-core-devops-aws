FROM openjdk

VOLUME /tmp

ADD maven/art-spring-core-devops-aws-0.0.5-SNAPSHOT.jar myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]
