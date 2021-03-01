package me.kalpha.testapp.service;

import me.kalpha.commonconfig.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonConfigService {
    @Autowired
    CommonConfig commonConfig;

    public void printMessage() {
        System.out.println("====================");
        System.out.println(commonConfig.getMessage());
        System.out.println("====================");
    }
    public void printChickenSize() {
        System.out.println("====================");
        System.out.println(commonConfig.getChickenSize().toMegabytes());
        System.out.println(commonConfig.getChickenSize());
        System.out.println("====================");
    }
}
