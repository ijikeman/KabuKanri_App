# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /workspace

# Copy the Gradle wrapper
COPY gradlew .
COPY gradle ./gradle

# Copy the build configuration files
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY modules/web/build.gradle.kts ./modules/web/
COPY modules/stock/build.gradle.kts ./modules/stock/

# Copy the source code
COPY modules/web/src ./modules/web/src
COPY modules/stock/src ./modules/stock/src

# Build the application
RUN chmod +x ./gradlew
RUN ./gradlew :modules:web:bootJar

# Stage 2: Create the final production image
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Create a non-root user
RUN useradd -m -s /bin/bash appuser
RUN mkdir -p /app/data
RUN chown appuser:appuser /app/data

USER appuser

# Copy the executable JAR from the builder stage
COPY --from=builder /workspace/modules/web/build/libs/*.jar ./application.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "./application.jar"]
