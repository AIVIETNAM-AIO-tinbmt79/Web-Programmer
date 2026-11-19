# Bước 1: Build file WAR bằng Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM tomcat:9.0-jdk17-temurin
WORKDIR /usr/local/tomcat

# Xóa các ứng dụng mặc định của Tomcat
RUN rm -rf webapps/*

COPY --from=build /app/target/*.war webapps/ROOT.war

# Mở cổng 8080
EXPOSE 8080

# Khởi động Tomcat
CMD ["catalina.sh", "run"]