package me.kalpha.multiplication.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.domain.MultiplicationSolvedEvent;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MultiplicationConsumerService {
    private final ConsumerFactory consumerFactory;
    private KafkaConsumer<String, MultiplicationSolvedEvent> consumer;

    @Autowired
    public MultiplicationConsumerService(ConsumerFactory consumerFactory) {
        this.consumerFactory = consumerFactory;
        consumer = new KafkaConsumer<String, MultiplicationSolvedEvent>(consumerFactory.getConfigurationProperties());
    }

    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload MultiplicationSolvedEvent multiplicationSolvedEvent) {
        log.info(String.format("$$$$ => Consumed message : %s", multiplicationSolvedEvent));
        consumer.commitSync();
    }
}
