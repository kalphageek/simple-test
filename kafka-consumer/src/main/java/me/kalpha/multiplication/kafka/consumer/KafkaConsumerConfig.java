package me.kalpha.multiplication.kafka.consumer;

import me.kalpha.multiplication.kafka.event.MultiplicationSolvedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${kafka.bootstrap-server}")
    private String bootstrapServer;

    @Bean
    public ConsumerFactory<String, MultiplicationSolvedEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(props);

//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
//        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000);
//        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

//        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
//                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(MultiplicationSolvedEvent.class)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MultiplicationSolvedEvent> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MultiplicationSolvedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
//
//        factory.setBatchListener(false);
//        factory.setConcurrency(2);
//        factory.setAutoStartup(false);
//
//        factory.getContainerProperties().setCommitLogLevel(Level.INFO);
//        factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
//        factory.getContainerProperties().setAckOnError(true);
//        factory.getContainerProperties().setLogContainerConfig(true);
//        factory.getContainerProperties().setConsumerRebalanceListener(new KafkaConsumerAwareRebalanceListener());
//
//        factory.setErrorHandler(new SeekToCurrentErrorHandler());

        return factory;
    }
}
