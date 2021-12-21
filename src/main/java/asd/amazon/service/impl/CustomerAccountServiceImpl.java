package asd.amazon.service.impl;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.repository.AccountRepository;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = false)
    public Boolean create(CustomerAccountDTO accountDTO){

        accountDTO.setActive(true);
        if(accountRepository.findByEmailAndActiveTrue(accountDTO.getEmail())!=null){return false;}
        if(accountRepository.findByUsernameAndActiveTrue(accountDTO.getUsername())!=null){return false;}

        Validate.notNull(accountDTO);
        Validate.notNull(accountDTO.getUsername());
        Validate.notNull(accountDTO.getPassword());
        Validate.notNull(accountDTO.getName());
        Validate.notNull(accountDTO.getSurname());
        Validate.notNull(accountDTO.getEmail());

        //Validate.matchesPattern(accountDTO.getUsername(), "\\w\\d{3,25}");
        //Validate.matchesPattern(accountDTO.getPassword(), "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        //Validate.matchesPattern(accountDTO.getName(), "\\w{5,20}");
        //Validate.matchesPattern(accountDTO.getSurname(), "\\w{5,20}");
        //Validate.matchesPattern(accountDTO.getEmail(), "/^(S+)@(\\\\S+)$");

        System.out.println("acc = " + accountDTO);
        CustomerAccount account = mapAccount(accountDTO);
        System.out.println("acc = " + accountDTO);
        //TODO: check all the fields
        customerAccountRepository.save(account);
        System.out.println("acc = " + account);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerAccountDTO login(final String username, final String password){
        System.out.println("login");
        CustomerAccount account = customerAccountRepository.findByUsername(username);
        if(account == null){
            //TODO: EXCEPTION USERNAME NOT FOUND
            return null;
        }
        //TODO: check password
        customerAccountRepository.checkPassword(password, username);
        return mapAccount(account);
    }

    @Override
    public AccountDTO authenticate(String username, String password) {
        System.out.println("service");
        System.out.println("acc= " + customerAccountRepository.findByUsernameAndPassword(username, password));
        Account account = customerAccountRepository.findByUsernameAndPassword(username, password);
        if(account == null){
            //TODO: EXCEPTION USERNAME NOT FOUND
            return null;
        }

        //TODO: check password
        AccountDTO a = new AccountDTO();
        a.setId(account.getId());
        a.setUsername(account.getUsername());
        a.setPassword(account.getPassword());
        return a;
    }

    @Override
    public AccountDTO getCustomerAccountById(Long id) throws Exception {
        Account account = accountRepository.findById(id).orElseThrow(() -> new Exception("Account not found."));
        AccountDTO dto = new CustomerAccountDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setName(account.getName());
        dto.setSurname(account.getSurname());
        dto.setEmail(account.getEmail());
        dto.setActive(account.getActive());
        return dto;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws Exception {
        accountRepository.findById(id).orElseThrow(() -> new Exception("Account not found."));
        accountRepository.deleteById(id);
    }

    @Override
    public void update(AccountDTO accountDTO) throws Exception {
        Account a = accountRepository.findById(accountDTO.getId()).orElseThrow(() -> new Exception("Account not found."));
            a.setName(accountDTO.getName());
            a.setSurname(accountDTO.getSurname());
            a.setEmail(accountDTO.getEmail());
            accountRepository.save(a);
    }

    //TODO: purchaseList maybe not mapped
    private CustomerAccount mapAccount(CustomerAccountDTO dto){
        CustomerAccount c = new CustomerAccount();
        c.setUsername(dto.getUsername());
        c.setPassword(dto.getPassword());
        c.setName(dto.getName());
        c.setSurname(dto.getSurname());
        c.setEmail(dto.getEmail());
        c.setActive(dto.getActive());

        return c;
    }

    //TODO: purchaseList maybe not mapped
    private CustomerAccountDTO mapAccount(CustomerAccount account){
        CustomerAccountDTO dto = new CustomerAccountDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setName(account.getName());
        dto.setSurname(account.getSurname());
        dto.setEmail(account.getEmail());
        dto.setActive(account.getActive());

        return dto;
    }
}
