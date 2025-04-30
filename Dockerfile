# Stage 1: Build
FROM gradle:8.13.0-jdk17 as build

WORKDIR /app

# Copy gradle wrapper files
COPY gradle/ gradle/

COPY gradlew.bat gradlew build.gradle settings.gradle ./


# Copy source code
COPY src /app/src/

# Make gradlew executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew clean shadowJar --no-daemon

# ----------------------------------------

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-jammy as runtime

# Environment variables
ENV APP_HOME=/opt/inmemorydb
ENV COMMAND_SERVER_PORT=6379

# Set working directory
WORKDIR $APP_HOME

# Copy built JAR
COPY --from=build /app/build/libs/db-1.0.jar app.jar
COPY newrelic/newrelic /opt/newrelic/

RUN chmod +x /opt/newrelic/newrelic.jar
RUN mkdir -p /var/log/app/inmemory/newrelic
RUN mkdir -p /var/log/app/inmemory
COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

EXPOSE $COMMAND_SERVER_PORT 8081

ENTRYPOINT ["/opt/inmemorydb/entrypoint.sh"]
