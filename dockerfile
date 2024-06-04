FROM openjdk:11
WORKDIR /src/java
COPY . /src/java
CMD java MainController.java