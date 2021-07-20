FROM azul/zulu-openjdk:8
WORKDIR /core
ADD * .
RUN ./gradlew clean build --info --stacktrace
