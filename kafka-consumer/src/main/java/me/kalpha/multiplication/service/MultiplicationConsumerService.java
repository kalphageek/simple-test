package me.kalpha.multiplication.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import me.kalpha.multiplication.repository.MuliplicationRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class MultiplicationConsumerService {
    private final ConsumerFactory consumerFactory;
    private final MuliplicationRepository muliplicationRepository;
    private KafkaConsumer<String, MultiplicationSolvedEvent> consumer;
    private Random random;

    /**
     * application.yml에 의해 설정된 properties에 의해 자동생성된 ConsumerFactory를 주입받아
     * KafkaConsumer를 singleton으로 생성. Manually commitSync 수행
     * @param consumerFactory
     */
    @Autowired
    public MultiplicationConsumerService(ConsumerFactory consumerFactory, MuliplicationRepository muliplicationRepository) {
        this.consumerFactory = consumerFactory;
        this.muliplicationRepository = muliplicationRepository;
        consumer = new KafkaConsumer<String, MultiplicationSolvedEvent>(consumerFactory.getConfigurationProperties());
        random =  new Random(System.currentTimeMillis());
    }

//    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
//    public void consume(@Payload MultiplicationSolvedEvent multiplicationSolvedEvent) {
//        log.info(String.format("$$$$ => Consumed message : %s", multiplicationSolvedEvent));
//        saveMultiplication(multiplicationSolvedEvent);
//        consumer.commitSync();
//    }

    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "batchKafkaListenerContainerFactory")
    public Integer onMessage(ConsumerRecords<String, MultiplicationSolvedEvent> consumerRecords) {
        StreamSupport.stream(consumerRecords.spliterator(), false)
                .map(ConsumerRecord::value)
                .filter(Objects::nonNull)
                .forEach(muliplicationRepository::save);
        log.info("{}_consumed:{}", random.nextInt(10), consumerRecords.count());
        return consumerRecords.count();
    }

    private void saveMultiplication(MultiplicationSolvedEvent multiplicationSolvedEvent) {
        try {
            muliplicationRepository.save(multiplicationSolvedEvent);
        } catch (Exception e) {
            log.info(String.format("vvvv => Not saved message : %s, %s", multiplicationSolvedEvent, e.getMessage()));
        }
    }

}
