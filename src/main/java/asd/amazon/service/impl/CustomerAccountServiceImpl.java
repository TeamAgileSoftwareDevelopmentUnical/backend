package asd.amazon.service.impl;

import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
        //TODO: check all the fields
        customerAccountRepository.save(account);
        return mapAccount(account);

    }

    @Override
    @Transactional(readOnly = true)
    public CustomerAccountDTO login(final String username, final String password){
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
    @Transactional
    public void delete(CustomerAccountDTO accountDTO)   {
        customerAccountRepository.deactivateUser(mapAccount(accountDTO).getUsername());
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
