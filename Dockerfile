FROM gradle:jdk11-slim as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build -x test
FROM openjdk:11.0.1-jre-slim
EXPOSE 8090
ENV ARTIFACT_NAME=product-manager-0.0.1-SNAPSHOT.jar
COPY --from=builder /home/gradle/src/build/libs/*.jar /$ARTIFACT_NAME
CMD java -jar $ARTIFACT_NAME
