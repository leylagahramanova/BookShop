FROM openjdk:21
WORKDIR /src/main/java
COPY . /src/main/java
CMD java MainController.java