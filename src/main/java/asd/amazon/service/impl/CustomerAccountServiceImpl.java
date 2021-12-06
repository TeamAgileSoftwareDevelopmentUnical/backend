package asd.amazon.service.impl;

import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Override
    @Transactional(readOnly = false)
    public CustomerAccountDTO create(CustomerAccountDTO accountDTO){
        CustomerAccount account = mapAccount(accountDTO);
        customerAccountRepository.save(account);
        return mapAccount(account);

    }

    private CustomerAccount mapAccount(CustomerAccountDTO dto){
        CustomerAccount c = new CustomerAccount();
        c.setUsername(dto.getUsername());
        c.setPassword(dto.getPassword());
        c.setName(dto.getName());
        c.setSurname(dto.getSurname());
        c.setEmail(dto.getEmail());

        return c;
    }

    private CustomerAccountDTO mapAccount(CustomerAccount account){
        CustomerAccountDTO dto = new CustomerAccountDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setName(account.getName());
        dto.setSurname(account.getUsername());
        dto.setEmail(account.getEmail());

        return dto;
    }
}
