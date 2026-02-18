FROM openjdk:22-jdk
ADD target/product.jar product.jar
ENTRYPOINT ["java", "-jar", "product.jar"]