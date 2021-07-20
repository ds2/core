FROM azul/zulu-openjdk:8
WORKDIR /core
COPY . .
RUN ./gradlew clean build --info --stacktrace
