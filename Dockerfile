FROM maven:3-openjdk-11 AS build
COPY src /home/app/synchrony/src
COPY pom.xml /home/app/synchrony
RUN mvn clean package -f  /home/app/synchrony/pom.xml

FROM openjdk:11.0.16-jre
COPY --from=build /home/app/synchrony/target/*.jar /usr/local/synchrony.jar
RUN ["mkdir" ,"/usr/local/upload"]
ENTRYPOINT ["java","-jar","/usr/local/synchrony.jar"]
