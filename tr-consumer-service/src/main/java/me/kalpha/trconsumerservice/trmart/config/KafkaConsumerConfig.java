package me.kalpha.trconsumerservice.trmart.config;

import me.kalpha.trconsumerservice.trmart.entity.Eqp1Tr;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConsumerConfig {

    private final KafkaProperties properties;

    public KafkaConsumerConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean("kafkaListenerContainerFactory")
    KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, Eqp1Tr> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Eqp1Tr>();
        factory.setConcurrency(2);
        factory.setConsumerFactory(consumerFactory);
//        factory.setErrorHandler(new KafkaErrorHandler());
        return factory;
    }

    @Bean
    public Map<String, Object> eqp1TrConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, properties.getConsumer().getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getConsumer().getValueDeserializer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getConsumer().getGroupId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, properties.getConsumer().getAutoOffsetReset());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
        return props;
    }

    @Bean
    public ConsumerFactory<String, Eqp1Tr> eqp1TrConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(eqp1TrConsumerConfigs(),  new StringDeserializer(),
                new JsonDeserializer<>(Eqp1Tr.class,false));
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
