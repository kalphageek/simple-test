package me.kalpha.multiplication.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.kafka.event.MultiplicationSolvedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaProducer {
    @Value("${message.topic.name}")
    private String topicName;

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void sendMessage(MultiplicationSolvedEvent event) {
        ListenableFuture<SendResult<String, MultiplicationSolvedEvent>> future = kafkaTemplate.send(topicName, event);

        future.addCallback(new ListenableFutureCallback<SendResult<String, MultiplicationSolvedEvent>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Message 전달 오류 [{}] : {}", event.toString(), ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MultiplicationSolvedEvent> result) {
                log.info("Sent message [{}] with offset = [{}]", event.toString(), result.getRecordMetadata().offset());
            }
        });
    }
}
