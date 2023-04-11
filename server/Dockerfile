FROM gradle:8.0.2-jdk19 AS BUILDER
WORKDIR /app

COPY *.gradle .
COPY core core
COPY server server


RUN gradle --no-daemon :server:shadowJar --stacktrace

FROM openjdk:19-jdk-slim
WORKDIR /app

ENV ARTIFACT_NAME=server-all.jar

COPY --from=BUILDER /app/server/build/libs/$ARTIFACT_NAME $ARTIFACT_NAME
#COPY --from=BUILDER /app/build/resources/main/ src/main/resources/
ENV PORT 8000
EXPOSE 8000
CMD ["java", "-jar", "server-all.jar"]

