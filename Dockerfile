FROM java:8-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ./target/${JAR_FILE} /product.jar
RUN sh -c 'touch /product.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/product.jar"]
