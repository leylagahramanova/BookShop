FROM openjdk:21
WORKDIR /src/java
COPY . /src/java
CMD java MainController.java