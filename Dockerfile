# Maven bilan build bosqichi
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
# Loyihani konteynerga nusxalash
COPY . .
# Maven yordamida Spring Boot ilovasini build qilish
RUN mvn clean package -DskipTests

# Final bosqich: Faqat ishga tushirish uchun zarur bo'lgan narsalarni qo'shish
FROM openjdk:21-jdk-slim
WORKDIR /app
# Build jarayonidan olingan JAR faylni nusxalash
COPY --from=builder /app/target/*.jar app.jar
# Spring Boot ilovasining portini ochish
EXPOSE 8005
# Spring Boot ilovasini ishga tushirish
ENTRYPOINT ["java", "-jar", "app.jar"]
