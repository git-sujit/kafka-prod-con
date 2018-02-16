# kafka-prod-con
Kafka producers &amp; consumers

## Docker - Kafka

1. Start Kafka instance using docker image

  `docker run --rm -it -p 2181:2181 -p 3030:3030 -p 8081:8081 -p 8082:8082 -p 8083:8083 -p 9092:9092 -e ADV_HOST=127.0.0.1 landoop/fast-data-dev`

2. Get command line tools : [https://github.com/Landoop/fast-data-dev](https://github.com/Landoop/fast-data-dev)

  `docker run --rm -it --net=host landoop/fast-data-dev bash`

3. Kafka will run at : fast-data-dev environment

  `http://127.0.0.1:3030`

4. Zookeeper will run at

  `http://127.0.0.1:2181`

5. Create topic

  `kafka-topics --zookeeper 127.0.0.1:2181 --create --topic sks_first_topic --partitions 3 --replication-factor 1`

6. List of topics

  `kafka-topics --zookeeper 127.0.0.1:2181 --list`

7. Describe topic

  `kafka-topics --zookeeper 127.0.0.1:2181 --describe --topic sks_first_topic`

8. Delete topic(This is configuration dependent, so command may or may not delete topic)

  `kafka-topics --zookeeper 127.0.0.1:2181 --delete --topic sks_first_topic`
  
  
  
