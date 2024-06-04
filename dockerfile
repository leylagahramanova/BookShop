
FROM openjdk:11
WORKDIR /src/java
COPY . /src/java
RUN javac MainController.java
CMD ["java", "MainController"]