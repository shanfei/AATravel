FROM amazoncorretto:17
COPY ./build/libs/AATravel-0.0.1-SNAPSHOT.jar AATravel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/AATravel-0.0.1-SNAPSHOT.jar"]