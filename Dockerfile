FROM maven:3.8.4-jdk-11
WORKDIR /spring-boot-login-example
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run

#FROM openjdk:17-jdk
#VOLUME /tmp
#COPY ./*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
