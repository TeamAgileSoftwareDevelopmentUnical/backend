package asd.amazon.service;

import asd.amazon.dto.CustomerAccountDTO;

public interface CustomerAccountService {

    public CustomerAccountDTO create(CustomerAccountDTO accountDTO);

    public CustomerAccountDTO login(final String username, final String password);

    public void delete(CustomerAccountDTO accountDTO);

}
