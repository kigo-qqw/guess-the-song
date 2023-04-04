FROM gradle:8.0.2-jdk19 AS BUILD_STAGE
COPY --chown=gradle:gradle . /home/gradle
RUN gradle :server:build || return 1


FROM openjdk:19-jdk-slim
ENV ARTIFACT_NAME=server.jar
ENV APP_HOME=/guess-the-song
COPY --from=BUILD_STAGE /home/gradle/build/libs/$ARTIFACT_NAME $APP_HOME/
WORKDIR $APP_HOME
#RUN groupadd -r -g 1000 user && useradd -r -g user -u 1000 user
#RUN chown -R user:user /app
#USER user
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}

