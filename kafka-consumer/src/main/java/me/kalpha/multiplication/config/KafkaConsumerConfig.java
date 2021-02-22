package me.kalpha.multiplication.config;

import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Bean
    KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, MultiplicationSolvedEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, MultiplicationSolvedEvent> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConcurrency(2);
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        kafkaListenerContainerFactory.setErrorHandler(new KafkaErrorHandler());
        return kafkaListenerContainerFactory;
    }
}
