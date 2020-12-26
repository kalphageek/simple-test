package me.kalpha.securityconfigdemo.accout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        return accountRepository.save(account);
    }
}
