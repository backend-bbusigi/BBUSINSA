# base-image
FROM openjdk:17-jdk-slim
# 변수 설정 (빌드파일의 경로)
ARG JAR_FILE=*.jar
# 빌드 파일을 컨테이너로 복사
COPY ${JAR_FILE} app.jar
# jar 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]


ADD src dest
ADD hom* /mydir/
