FROM openjdk:22-jdk
ADD target/shop.jar shop.jar
ENTRYPOINT ["java", "-jar", "shop.jar"]