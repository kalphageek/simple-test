package me.kalpha.multiplication.application.runner;

import me.kalpha.multiplication.domain.MultiplicationSolvedEvent;
import me.kalpha.multiplication.service.MultiplicationProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProducerRunner implements ApplicationRunner {
    @Autowired
    MultiplicationProducerService producer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int latchCount = 2;

        for (int i=0; i<latchCount; i++) {
            MultiplicationSolvedEvent event = new MultiplicationSolvedEvent((long) i, 33L, true);
            // CountDownLatch를 통해 모든 메시지가 처리된 이후에 애플리케이션을 종료할 수 있도록 하였다
            producer.sendMessage(event);
        }
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        int latchCount = 10;
//        CountDownLatch latch = new CountDownLatch(latchCount);
//
//        for (int i=0; i<latchCount; i++) {
//            MultiplicationSolvedEvent event = new MultiplicationSolvedEvent((long) i, 33L, true);
//            // CountDownLatch를 통해 모든 메시지가 처리된 이후에 애플리케이션을 종료할 수 있도록 하였다
//            producer.sendMessage(event);
//            latch.countDown();
//        }
//        latch.await();
//    }
}
