package me.kalpha.securityconfigdemo.runner;

import me.kalpha.securityconfigdemo.accout.Account;
import me.kalpha.securityconfigdemo.accout.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccoutRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = accountService.createAccount("jjd", "pwd");
        System.out.println(account.getUsername() + " password : " + account.getPassword());
    }
}
