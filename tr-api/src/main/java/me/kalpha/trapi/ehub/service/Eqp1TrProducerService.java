package me.kalpha.trapi.ehub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kalpha.trapi.ehub.entity.Eqp1Tr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class Eqp1TrProducerService {

    private final KafkaTemplate<String, Eqp1Tr> kafkaTemplate;

    @Value("${app.topic.name}")
    private String TOPIC;

    public void sendMessage(Eqp1Tr eqp1Tr) {
        sendMessage(TOPIC, eqp1Tr);
    }

    public void sendMessage(String topic, Eqp1Tr eqp1Tr) {
        ListenableFuture<SendResult<String, Eqp1Tr>> future = kafkaTemplate.send(topic, eqp1Tr);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Eqp1Tr>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[{}] due to : [{}]", eqp1Tr, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Eqp1Tr> result) {
                log.info("Sent message=[{}] with offset=[{}]", eqp1Tr, result.getRecordMetadata().offset());
            }
        });
    }
}

