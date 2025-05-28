FROM --platform=$BUILDPLATFORM maven:3.9.9-eclipse-temurin-17-alpine AS builder
WORKDIR /workdir
COPY pom.xml /workdir/pom.xml
COPY src /workdir/src
RUN --mount=type=cache,id=cache_m2,target=/root/.m2 mvn -f /workdir/pom.xml clean package

FROM eclipse-temurin:17.0.14_7-jre-alpine-3.20
RUN addgroup -S grupoexe && adduser -S -G grupoexe user
USER user
WORKDIR /app
COPY --from=builder workdir/target/*.jar /app/api.jar
EXPOSE 8080
CMD [ "java", "-jar", "api.jar" ]
