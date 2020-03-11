FROM openjdk:8u181-jdk-alpine

WORKDIR /home/apps/
ADD target/dcs-1.0.0.war .
ADD target/lib ./lib
ADD start.sh .

ENTRYPOINT ["sh", "/home/apps/start.sh"]