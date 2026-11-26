
# Stage 1: Build bằng Maven và JDK 24
FROM maven:3.9-eclipse-temurin-24 AS build

WORKDIR /app

# Tải dependencies trước để tận dụng Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy mã nguồn
COPY src ./src

# Build file WAR
RUN mvn clean package -DskipTests --no-transfer-progress

# Stage 2: Runtime bằng Tomcat 9 và Java 21
FROM tomcat:9-jdk21-temurin

# Xóa các ứng dụng mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR và triển khai tại context root
COPY --from=build /app/target/ch09_ex1_download-1.0*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
