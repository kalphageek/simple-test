package me.kalpha.multiplication.service;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import me.kalpha.multiplication.repository.MuliplicationRepository;
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
    private final MuliplicationRepository muliplicationRepository;
    private KafkaConsumer<String, MultiplicationSolvedEvent> consumer;

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
    }

    @KafkaListener(topics = "${app.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload MultiplicationSolvedEvent multiplicationSolvedEvent) {
        log.info(String.format("$$$$ => Consumed message : %s", multiplicationSolvedEvent));
        saveMultiplication(multiplicationSolvedEvent);
        consumer.commitSync();
    }

    private void saveMultiplication(MultiplicationSolvedEvent multiplicationSolvedEvent) {
        try {
            muliplicationRepository.save(multiplicationSolvedEvent);
        } catch (Exception e) {
            log.info(String.format("vvvv => Not saved message : %s, %s", multiplicationSolvedEvent, e.getMessage()));
        }
    }

}
