package me.kalpha.testapp.service;

import me.kalpha.commonconfig.config.CommonConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.unit.DataSize;

@SpringBootTest
public class ComonConfigServiceTest {
    @Autowired
    CommonConfigService commonConfigService;
    @Autowired
    CommonConfig commonConfig;

    @Test
    public void printMessage() {
        commonConfigService.printMessage();
        Assertions.assertTrue(commonConfig.getMessage().contains("Hello"));
    }

    @Test
    public void printChickenSize() {
        DataSize size = DataSize.ofGigabytes(1);
        System.out.println(size.toMegabytes());
        Assertions.assertTrue(size.toMegabytes() == 1024);

        commonConfigService.printChickenSize();
        Assertions.assertTrue(commonConfig.getChickenSize().toMegabytes() == 10);
    }
}