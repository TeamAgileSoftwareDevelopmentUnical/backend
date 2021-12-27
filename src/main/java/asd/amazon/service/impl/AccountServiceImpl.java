package asd.amazon.service.impl;

import asd.amazon.entity.Account;
import asd.amazon.repository.AccountRepository;
import asd.amazon.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean accountDeactivate(Long user_id) {
        // first get user by id
        Account accountInfo = accountRepository.getById(user_id);
        // check the user exist or not
        if (accountInfo.getId()>0){
            accountInfo.setActive(false);
            accountRepository.save(accountInfo);
            return true;
        }

        return false;
    }
}
