
# Stage 1: Build ứng dụng bằng Maven và JDK 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Sao chép cấu hình Maven
COPY pom.xml .

# Tải dependencies
RUN mvn dependency:go-offline -B

# Sao chép mã nguồn
COPY src ./src

# Build WAR tương thích với Java 21
RUN mvn clean package -DskipTests \
    -Dmaven.compiler.source=21 \
    -Dmaven.compiler.target=21

# Stage 2: Chạy ứng dụng bằng Tomcat 11
FROM tomcat:9.0.12-jre8

# Xóa ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Sao chép WAR vào đường dẫn gốc
COPY --from=build /app/target/demo-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Mở cổng HTTP
EXPOSE 8080

# Khởi chạy Tomcat
CMD ["catalina.sh", "run"]
