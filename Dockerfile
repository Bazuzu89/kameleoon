FROM eclipse-temurin:18-jre-alpine
COPY target/kameleoon-1.0-SNAPSHOT-jar-with-dependencies.jar kameleoon-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/kameleoon-1.0-SNAPSHOT.jar"]