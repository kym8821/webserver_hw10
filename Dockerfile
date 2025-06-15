FROM openjdk:17-jdk-slim AS builder
LABEL authors="kym8821"
WORKDIR /app
# copy from root module
COPY gradlew /app/
COPY gradle /app/gradle/
COPY build.gradle /app/
COPY settings.gradle /app/
# copy code
COPY src /app/src
# gradlew 라인 문제 해결
RUN apt-get update && apt-get install -y dos2unix tree && \
    dos2unix /app/gradlew && chmod +x /app/gradlew
# ensure execute gradlew
RUN chmod +x /app/gradlew
# build project
RUN ./gradlew clean build
# copy spring boot jar on root dir
RUN cp /app/build/libs/*.jar /app.jar

# Runtime Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder ./app.jar app.jar
ENTRYPOINT ["java", "-XX:-UseContainerSupport", "-jar", "app.jar"]