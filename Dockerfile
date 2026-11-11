# ---------- 1. Compilar el WAR ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Primero el pom para aprovechar la caché de dependencias
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

# Luego el código
COPY src ./src
RUN mvn -q -B clean package -DskipTests

# ---------- 2. Servir con Tomcat ----------
FROM tomcat:10.1-jdk17-temurin

# Quitamos las aplicaciones de ejemplo de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/basicfood.war /usr/local/tomcat/webapps/ROOT.war

COPY docker/entrypoint.sh /usr/local/bin/basicfood-entrypoint.sh
RUN sed -i 's/\r$//' /usr/local/bin/basicfood-entrypoint.sh \
    && chmod +x /usr/local/bin/basicfood-entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/basicfood-entrypoint.sh"]
