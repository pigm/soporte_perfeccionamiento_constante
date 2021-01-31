FROM frolvlad/alpine-java:jdk8-slim
RUN apk add -U tzdata
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN date
VOLUME /opt/proyectos/nfsshared/came
RUN mkdir -p /came/lib/
RUN mkdir -p /came/config/
RUN mkdir -p /came/logs/
ADD apoyo-mejora-continua.jar /came/lib/app.jar
ADD bootstrap.yml /came/config
ADD logback.xml /came/config
ADD application-apoyo-mejora-continua-messages.properties /came/config
WORKDIR /came
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lib/app.jar"]
EXPOSE 9000
