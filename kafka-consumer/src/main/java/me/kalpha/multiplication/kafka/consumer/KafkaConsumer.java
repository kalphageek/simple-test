package me.kalpha.multiplication.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.multiplication.kafka.event.MultiplicationSolvedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
//    @Autowired
//    private final TimelineRepository timelineRepository;
//    @Autowired
//    private final PostService postService;

    @KafkaListener(topics = "${message.topic.name}", groupId = "${kafka.groupId}")
    public void eventListener(MultiplicationSolvedEvent event) {
        log.info("Kafka Consumer Listening");
        System.out.println("Received message : " + event.toString());
//        UUID stringId = postingMessageModel.getPostId();
//        List<String> followers = postingMessageModel.getFollowerId();
//        for (String follower : followers) {
//            postService.insertToTimelineTable(stringId, follower);
//        }
    }
}
