package me.kalpha.multiplication.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.domain.MultiplicationSolvedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MultiplicationConsumerService {
    @KafkaListener(topics = "${app.topic.name}", groupId = "${app.group-id}")
    public void consume(MultiplicationSolvedEvent multiplicationSolvedEvent) {
        log.info(String.format("$$$$ => Consumed message : %s", multiplicationSolvedEvent));
    }
}
