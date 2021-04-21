package me.kalpha.trconsumerservice.trmart.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrDetRepository;
import me.kalpha.trconsumerservice.trmart.repository.Eqp1TrRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class Eqp1TrConsumerService {
    private final ConsumerFactory consumerFactory;
    private final Eqp1TrService eqp1TrService;
    private KafkaConsumer<String, Eqp1Tr> consumer;

    /**
     * application.yml에 의해 설정된 properties에 의해 자동생성된 ConsumerFactory를 주입받아
     * KafkaConsumer를 singleton으로 생성. Manually commitSync 수행
     * @param consumerFactory
     */
    @Autowired
    public Eqp1TrConsumerService(ConsumerFactory consumerFactory, Eqp1TrService eqp1TrService) {
        this.consumerFactory = consumerFactory;
        this.eqp1TrService = eqp1TrService;
        consumer = new KafkaConsumer<String, Eqp1Tr>(consumerFactory.getConfigurationProperties());
    }

    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "batchKafkaListenerContainerFactory")
    public void onMessage(ConsumerRecords<String, Eqp1Tr> consumerRecords) {
        StreamSupport.stream(consumerRecords.spliterator(), false)
                .map(ConsumerRecord::value)
                .filter(Objects::nonNull)
                .forEach(eqp1TrService::createTr);
        log.info("consumed count: {}", consumerRecords.count());
    }
}