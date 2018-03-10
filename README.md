#### 1. Install postgress:

```sh
$ sudo docker run \
         --rm \
         --env POSTGRES_DB=postgress \
         --env POSTGRES_USER=postgres \
         --env POSTGRES_PASSWORD=postgres \
         -p 5432:5432 \
         postgres:alpine
```

```sh
$ java -jar target/gs-rest-service-0.1.0.jar
```