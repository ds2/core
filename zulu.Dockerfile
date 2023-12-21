FROM azul/zulu-openjdk:21.0.1
WORKDIR /core
COPY . .
RUN ./gradlew clean build --info --stacktrace
