package me.kalpha.multiplication.config;

import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Bean("kafkaListenerContainerFactory")
    KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, MultiplicationSolvedEvent> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MultiplicationSolvedEvent>();
        factory.setConcurrency(2);
        factory.setConsumerFactory(consumerFactory);
        factory.setErrorHandler(new KafkaErrorHandler());
        return factory;
    }

    @Bean("batchKafkaListenerContainerFactory")
    KafkaListenerContainerFactory<?> batchKafkaListenerContainerFactory (ConsumerFactory<String, MultiplicationSolvedEvent> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MultiplicationSolvedEvent>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);

        var containerProperties = factory.getContainerProperties();
        containerProperties.setAckMode(ContainerProperties.AckMode.BATCH);
        containerProperties.setIdleBetweenPolls(5000);

        return factory;
    }
}
