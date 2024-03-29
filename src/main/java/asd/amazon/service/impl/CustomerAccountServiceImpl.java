package asd.amazon.service.impl;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.entity.SellerAccount;
import asd.amazon.repository.AccountRepository;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.repository.SellerAccountRepository;
import asd.amazon.request.CustomerAddressRequest;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.Validate;
import org.webjars.NotFoundException;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SellerAccountRepository sellerAccountRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity create(CustomerAccountDTO accountDTO){

        accountDTO.setActive(true);
        accountDTO.setRole("CUSTOMER");
        if(accountRepository.findByEmailAndActiveTrue(accountDTO.getEmail())!=null){
            return new ResponseEntity<>("Email",HttpStatus.FORBIDDEN);
        }
        if(accountRepository.findByUsername(accountDTO.getUsername())!=null){
            return new ResponseEntity<>("Username",HttpStatus.FORBIDDEN);
        }

        Validate.notNull(accountDTO);
        Validate.notNull(accountDTO.getUsername());
        Validate.notNull(accountDTO.getPassword());
        Validate.notNull(accountDTO.getName());
        Validate.notNull(accountDTO.getSurname());
        Validate.notNull(accountDTO.getEmail());
        if(accountDTO.getPassword().length()<6){
            return new ResponseEntity<>("Password",HttpStatus.FORBIDDEN);
        }

        CustomerAccount account = mapAccount(accountDTO);

        customerAccountRepository.save(account);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerAccountDTO login(final String username, final String password){
        CustomerAccount account = customerAccountRepository.findByUsername(username);
        if(account == null){
            return null;
        }
        customerAccountRepository.checkPassword(password, username);
        return mapAccount(account);
    }

    @Override
    public AccountDTO authenticate(String username, String password) {
        //System.out.println("service");
        //System.out.println("acc= " + customerAccountRepository.findByUsernameAndPassword(username, password));
        Account account = customerAccountRepository.findByUsernameAndPassword(username, password);
        if(account == null || !account.getActive()){
            return null;
        }

        AccountDTO a = new AccountDTO();
        a.setId(account.getId());
        a.setUsername(account.getUsername());
        a.setPassword(account.getPassword());
        a.setEmail(account.getEmail());
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
        CustomerAccount customerAccount = customerAccountRepository.findCustomerAccountsById(account.getId());
        if(customerAccount != null)
            dto.setAddress(customerAccount.getShippingAddress());
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
        Validate.notNull(accountDTO);
        Validate.notNull(accountDTO.getUsername());
        Validate.notNull(accountDTO.getPassword());
        Validate.notNull(accountDTO.getName());
        Validate.notNull(accountDTO.getSurname());
        Validate.notNull(accountDTO.getEmail());
        if(accountDTO.getPassword().length()<6){
            throw new Exception("Password must be more than 6 characters.");
        }
        a.setName(accountDTO.getName());
        a.setSurname(accountDTO.getSurname());
        a.setEmail(accountDTO.getEmail());

        accountRepository.save(a);
        if(a.getRole().equals("CUSTOMER") && accountDTO.getAddress()!=null){
            CustomerAccount c = customerAccountRepository.findById(a.getId()).orElseThrow(()-> new Exception("Account not found"));
            c.setShippingAddress(accountDTO.getAddress());
            customerAccountRepository.save(c);
        }else if(a.getRole().equals("SELLER") && accountDTO.getAddress()!=null){
            SellerAccount s = sellerAccountRepository.findById(a.getId()).orElseThrow(()-> new Exception("Account not found"));
            s.setPaymentAddress(accountDTO.getAddress());
            sellerAccountRepository.save(s);
        }

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
        c.setRole(dto.getRole());

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
        dto.setRole(account.getRole());
        return dto;
    }
}
