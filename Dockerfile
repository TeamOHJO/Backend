FROM openjdk:17-jdk-slim-buster
COPY build/libs/yanolja-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# 작업 디렉토리 생성
WORKDIR /app

# 소스 코드 및 .env 파일 복사
COPY . .

# .env 파일을 WORKDIR로 복사
COPY .env .env

ENTRYPOINT ["java", "-jar", "/app.jar"]