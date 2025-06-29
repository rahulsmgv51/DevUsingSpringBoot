1. Kafka

**INSTALLATION COMMANDS**

zookeeper-server-start.bat ..\..\config\zookeeper.properties

kafka-server-start.bat ..\..\config\server.properties

kafka-topics.bat --create --topic device --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3

kafka-console-producer.bat --broker-list localhost:9092 --topic device
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic device --from-beginning


**SENDING MESSAGES COMMANDS**

zookeeper-server-start.bat ..\..\config\zookeeper.properties

kafka-server-start.bat ..\..\config\server.properties

kafka-topics.bat --create --topic foods --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4

kafka-console-producer.bat --broker-list localhost:9092 --topic foods --property "key.separator=-" --property "parse.key=true"

kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic foods --from-beginning -property "key.separator=-" --property "print.key=false"


kafka-topics.bat --bootstrap-server=localhost:9092 --list
kafka-consumer-groups.bat  --bootstrap-server=localhost:9092 --list
kafka-topics.bat --describe --topic rahul-topic --bootstrap-server=localhost:9092


kafka-topics.bat --describe --topic device --bootstrap-server localhost:9092

C:\kafka_2.13-3.9.1\bin\windows




