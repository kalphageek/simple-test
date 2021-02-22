package me.kalpha.multiplication.controller;

import me.kalpha.multiplication.entity.MultiplicationSolvedEvent;
import me.kalpha.multiplication.service.MultiplicationProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiplication")
public class MultiplicationController {
    private final MultiplicationProducerService multiplicationProducerService;

    @Autowired
    public MultiplicationController(MultiplicationProducerService multiplicationProducerService) {
        this.multiplicationProducerService = multiplicationProducerService;
    }

    @PostMapping
    public void publish(@RequestBody MultiplicationSolvedEvent multiplicationSolvedEvent) {
        multiplicationProducerService.sendMessage(multiplicationSolvedEvent);
    }
}
