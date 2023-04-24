# First stage: complete build environment
FROM maven:3.8.3-openjdk-17 AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
FROM openjdk:17-oracle

# copy jar from the first stage
COPY --from=builder target/dynatraceinternship-1.0-SNAPSHOT.jar dynatraceinternship-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "dynatraceinternship-1.0-SNAPSHOT.jar"]
