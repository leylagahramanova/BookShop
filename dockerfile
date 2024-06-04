
FROM openjdk:11
WORKDIR /src/java
COPY . /src/java
RUN javac BookShopApplication.java
CMD ["java", "BookShopApplication"]