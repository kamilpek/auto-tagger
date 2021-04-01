FROM ubuntu:focal
RUN apt-get update
RUN apt-get install -y ca-certificates wget gnupg2 software-properties-common
RUN echo "Europe/Warsaw" > /etc/timezone
RUN wget -q -O - http://download.sgjp.pl/apt/sgjp.gpg.key | apt-key add -
RUN apt-add-repository http://download.sgjp.pl/apt/ubuntu
RUN apt-get update
RUN apt-get install -y morfeusz2 libjmorfeusz-java
RUN apt-get install -y openjdk-11-jre
COPY ./target/auto-tagger.jar /opt/app/
COPY ./src/main/resources/application.properties /opt/app/
COPY ./logback.xml /opt/app/
WORKDIR /opt/app
ENV LANG pl_PL.UTF-8
ENV LANGUAGE pl_PL.UTF-8
ENV LC_ALL pl_PL.UTF-8
CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "auto-tagger.jar"]