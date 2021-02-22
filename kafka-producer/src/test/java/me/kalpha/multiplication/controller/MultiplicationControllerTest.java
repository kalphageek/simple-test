package me.kalpha.multiplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import me.kalpha.multiplication.service.MultiplicationProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MultiplicationControllerTest {
    @Autowired
    MultiplicationProducerService multiplicationProducerService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void publish() throws Exception {
        MultiplicationSolvedEvent event = new MultiplicationSolvedEvent(201L, 33L, true);

        mockMvc.perform(post("/multiplication")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
        ;
    }
}