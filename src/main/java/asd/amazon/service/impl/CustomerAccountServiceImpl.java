package asd.amazon.service.impl;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.Validate;

import java.util.Optional;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Override
    @Transactional(readOnly = false)
    public CustomerAccountDTO create(CustomerAccountDTO accountDTO){

        Validate.notNull(accountDTO);
        Validate.notNull(accountDTO.getUsername());
        Validate.notNull(accountDTO.getPassword());
        Validate.notNull(accountDTO.getName());
        Validate.notNull(accountDTO.getSurname());
        Validate.notNull(accountDTO.getEmail());
        Validate.notNull(accountDTO.getPurchase());

        Validate.matchesPattern(accountDTO.getUsername(), "\\w\\d{3,25}");
        Validate.matchesPattern(accountDTO.getPassword(), "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Validate.matchesPattern(accountDTO.getName(), "\\w{5,20}");
        Validate.matchesPattern(accountDTO.getSurname(), "\\w{5,20}");
        Validate.matchesPattern(accountDTO.getEmail(), "/^(S+)@(\\\\S+)$");

        CustomerAccount account = mapAccount(accountDTO);
        //TODO: check all the fields
        customerAccountRepository.save(account);
        return mapAccount(account);
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
    public CustomerAccountDTO getCustomerAccountById(Long id) {
        Optional<CustomerAccount> customerAccount = customerAccountRepository.findById(id);
        if(customerAccount.isPresent())
            return mapAccount(customerAccount.get());
        else    //TODO: throw an exception
            return null;
    }

    //TODO: purchaseList maybe not mapped
    private CustomerAccount mapAccount(CustomerAccountDTO dto){
        CustomerAccount c = new CustomerAccount();
        c.setUsername(dto.getUsername());
        c.setPassword(dto.getPassword());
        c.setName(dto.getName());
        c.setSurname(dto.getSurname());
        c.setEmail(dto.getEmail());

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

        return dto;
    }
}
