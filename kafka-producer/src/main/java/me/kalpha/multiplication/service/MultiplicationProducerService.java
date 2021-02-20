package me.kalpha.multiplication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.domain.MultiplicationSolvedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
@Service
public class MultiplicationProducerService {
    private final KafkaTemplate<String, MultiplicationSolvedEvent> kafkaTemplate;

    @Value("${app.topic.name}")
    private String TOPIC;

    public void sendMessage(MultiplicationSolvedEvent multiplicationSolvedEvent) {
        sendMessage(TOPIC, multiplicationSolvedEvent);
    }

    public void sendMessage(String topic, MultiplicationSolvedEvent multiplicationSolvedEvent) {
        ListenableFuture<SendResult<String, MultiplicationSolvedEvent>> future = kafkaTemplate.send(topic, multiplicationSolvedEvent);
        future.addCallback(new ListenableFutureCallback<SendResult<String, MultiplicationSolvedEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[{}] due to : [{}]", multiplicationSolvedEvent, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MultiplicationSolvedEvent> result) {
                log.info("Sent message=[{}] with offset=[{}]", multiplicationSolvedEvent, result.getRecordMetadata().offset());
            }
        });
    }
}
