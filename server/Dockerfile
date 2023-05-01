FROM gradle:8.0.2-jdk19 AS BUILDER
WORKDIR /app

ENV DATABASE_URL=$DATABASE_URL
ENV DATABASE_USERNAME=$DATABASE_USERNAME
ENV DATABASE_PASSWORD=$DATABASE_PASSWORD


COPY *.gradle .
COPY core core
COPY server server
COPY server/src/main/resources/META-INF/persistence.docker-compose.xml server/src/main/resources/META-INF/persistence.xml


RUN gradle --no-daemon :server:shadowJar --stacktrace

FROM openjdk:20-jdk-slim
WORKDIR /app

ENV ARTIFACT_NAME=server-all.jar

COPY --from=BUILDER /app/server/build/libs/$ARTIFACT_NAME $ARTIFACT_NAME
#COPY --from=BUILDER /app/build/resources/main/ src/main/resources/
ENV PORT 8000
EXPOSE 8000
CMD ["java", "-jar", "server-all.jar"]

