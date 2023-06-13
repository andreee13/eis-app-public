FROM maven:3.8.6-openjdk-11-slim AS build
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /target/*jar-with-dependencies.jar ./app.jar
RUN mkdir data
ENTRYPOINT ["java", "-jar", "app.jar"]