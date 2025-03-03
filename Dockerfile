# 빌드 환경 (gradle을 사용하여 jar 생성)
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# 최종 실행 환경 (jar만 복사하여 실행)
FROM openjdk:17-jdk-slim

# Set the timezone to Asia/Seoul
ENV TZ=Asia/Seoul
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Set the working directory
WORKDIR /app

# 빌드된 jar 파일만 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
