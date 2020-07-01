FROM alpine/git:v2.26.2 AS git-clone
WORKDIR /app
RUN git clone https://github.con/Semantive/cassandra-trainings-bad-model-example.git

FROM maven:3-openjdk-8 AS builder
WORKDIR /app
COPY --from=git-clone /app/cassandra-trainings-bad-model-example /app
RUN mvn assembly:assembly

FROM openjdk:8-alpine
COPY --from=builder /app/target/DataGenerator-0.1-SNAPSHOT-jar-with-dependencies.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]

# need to setup cassandra first. To run invoke: docker run <image-name> --host=<cassandra-host> --keyspace=<cassandra-keyspace>
