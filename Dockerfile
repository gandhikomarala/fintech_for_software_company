# Multi-Stage Production Build for FinFlow Enterprise
FROM maven:3.9.6-eclipse-temurin-21-alpine AS backend-build
WORKDIR /app
COPY backend/ ./backend/
RUN cd backend && mvn clean package -DskipTests

FROM node:20-alpine AS frontend-build
WORKDIR /app
COPY frontend/ ./frontend/
RUN cd frontend && npm install && npm run build

FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
COPY --from=backend-build /app/backend/finflow-application/target/*.jar /app/finflow-application.jar
COPY --from=frontend-build /app/frontend/dist /app/static
EXPOSE 8080 5173
ENTRYPOINT ["java", "-jar", "/app/finflow-application.jar"]
