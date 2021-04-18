package me.kalpha.trapi.trmart.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.trapi.trmart.entity.Eqp1Tr;
import me.kalpha.trapi.trmart.repository.Eqp1Tr_DetRepository;
import me.kalpha.trapi.trmart.repository.Eqp1Tr_Repository;
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
    private final Eqp1Tr_Repository eqp1TrRepository;
    private final Eqp1Tr_DetRepository eqp1TrDetRepository;
    private KafkaConsumer<String, Eqp1Tr> consumer;
    private Random random;

    /**
     * application.yml에 의해 설정된 properties에 의해 자동생성된 ConsumerFactory를 주입받아
     * KafkaConsumer를 singleton으로 생성. Manually commitSync 수행
     * @param consumerFactory
     */
    @Autowired
    public Eqp1TrConsumerService(ConsumerFactory consumerFactory, Eqp1Tr_Repository eqp1TrRepository, Eqp1Tr_DetRepository eqp1TrDetRepository) {
        this.consumerFactory = consumerFactory;
        this.eqp1TrRepository = eqp1TrRepository;
        this.eqp1TrDetRepository = eqp1TrDetRepository;
        consumer = new KafkaConsumer<String, Eqp1Tr>(consumerFactory.getConfigurationProperties());
        random = new Random(System.currentTimeMillis());
    }

    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "batchKafkaListenerContainerFactory")
    public Integer onMessage(ConsumerRecords<String, Eqp1Tr> consumerRecords) {
        StreamSupport.stream(consumerRecords.spliterator(), false)
                .map(ConsumerRecord::value)
                .filter(Objects::nonNull)
                .forEach(eqp1TrRepository::save);
        log.info("consumed count: {}", consumerRecords.count());
        return consumerRecords.count();
    }
}