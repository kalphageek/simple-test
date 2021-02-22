Properties에 의해 Kafka configuration 설정하고, event.multiplication 토픽의 메세지를 MultiplicationSolvedEvent에 담아서 consume하기 위한 KafkaListener Service를 가지고 있다.
Consume된 객체는 JPA에 의해 DB에 저장된다.<br>
이 때 consume할 POJO객체는 producer에서 publish한 객체와 동일한 객체이어야 한다. (package 경로, 이름이 동일해야 함)<br>
Kafka Producer는 kafka-producer Project에서 참조바랍니다.

### Kafka Tool
* https://www.kafkatool.com/download.html