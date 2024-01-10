FROM docker.io/azul/zulu-openjdk:17
WORKDIR /core
COPY . .
RUN ./gradlew clean build --info --stacktrace
