package asd.amazon.service;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;

public interface CustomerAccountService {

    public CustomerAccountDTO create(CustomerAccountDTO accountDTO);

    public CustomerAccountDTO login(final String username, final String password);

    public void delete(CustomerAccountDTO accountDTO);

    public CustomerAccountDTO getCustomerAccountById(Long id);

    public CustomerAccountDTO update(Long id, final String name, final String surname, final String mail);
  
    public AccountDTO authenticate(final String username, final String password);

}
