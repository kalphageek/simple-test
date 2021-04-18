package me.kalpha.trapi.trmart.config;

import me.kalpha.trapi.common.KafkaErrorHandler;
import me.kalpha.trapi.trmart.entity.Eqp1Tr;
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
    KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, Eqp1Tr> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Eqp1Tr>();
        factory.setConcurrency(2);
        factory.setConsumerFactory(consumerFactory);
        factory.setErrorHandler(new KafkaErrorHandler());
        return factory;
    }

    @Bean("batchKafkaListenerContainerFactory")
    KafkaListenerContainerFactory<?> batchKafkaListenerContainerFactory (ConsumerFactory<String, Eqp1Tr> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Eqp1Tr>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);

        var containerProperties = factory.getContainerProperties();
        containerProperties.setAckMode(ContainerProperties.AckMode.BATCH);
        containerProperties.setIdleBetweenPolls(5000);

        return factory;
    }
}
