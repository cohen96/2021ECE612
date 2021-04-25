FROM java:8

LABEL version="1.0"
LABEL user="HaoXu"

RUN  mkdir -p  /home/work/data/www/queue-system
WORKDIR /home/work/data/www/queue-system
COPY ./target/*.jar ./app.jar

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=dev"]

EXPOSE 8080