FROM java:8-jre
MAINTAINER Alex Trunin <jorki02@gmail.com>
ADD ./target/smlr.jar /app/
CMD ["java", "-jar", "/app/smlr.jar"]
EXPOSE 8080
