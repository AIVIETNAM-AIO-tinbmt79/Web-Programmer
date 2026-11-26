# Stage 1: Build bằng Maven & JDK 24
FROM maven:3.9-eclipse-temurin-24 AS build

WORKDIR /app

# Tải trước dependencies để tận dụng Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy toàn bộ mã nguồn
COPY src ./src

# Build file WAR (tắt log progress để màn hình build gọn gàng)
RUN mvn clean package -DskipTests --no-transfer-progress

# Stage 2: Runtime với Tomcat 9 tương thích JDK 21+
FROM tomcat:9.0.12-jre8
# Xóa các app mặc định của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy đúng tên file WAR dựa theo project Cart của bạn thành ROOT.war
COPY --from=build /app/target/Cart*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]