package asd.amazon.service;

import org.springframework.http.ResponseEntity;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;

public interface CustomerAccountService {

    public ResponseEntity create(CustomerAccountDTO accountDTO);

    public CustomerAccountDTO login(final String username, final String password);

    public void delete(final Long id) throws Exception;

    public AccountDTO getCustomerAccountById(Long id) throws Exception;

    public void update(AccountDTO accountDTO) throws Exception;
  
    public AccountDTO authenticate(final String username, final String password);


}
