FROM java:8-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ./deploy/product-soaring-clouds-sequel-1.0-SNAPSHOT.jar /product.jar
RUN sh -c 'touch /product.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/product.jar"]
