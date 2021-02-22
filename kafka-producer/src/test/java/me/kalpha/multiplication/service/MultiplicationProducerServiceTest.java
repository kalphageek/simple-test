package me.kalpha.multiplication.service;

import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultiplicationProducerServiceTest {
    @Autowired
    MultiplicationProducerService multiplicationProducerService;

    @Test
    public void publish() {
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(100L, 33L, true);
        // CountDownLatch를 통해 모든 메시지가 처리된 이후에 애플리케이션을 종료할 수 있도록 하였다
        multiplicationProducerService.sendMessage(event);
    }
}