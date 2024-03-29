package asd.amazon.service.impl;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.dto.SellerAccountDTO;
import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.entity.SellerAccount;
import asd.amazon.repository.AccountRepository;
import asd.amazon.repository.SellerAccountRepository;
import asd.amazon.service.SellerAccountService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerAccountServiceImpl implements SellerAccountService {

    @Autowired
    private SellerAccountRepository sellerAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity create(SellerAccountDTO accountDTO) {
        //TO DO: check
        accountDTO.setActive(true);
        accountDTO.setRole("SELLER");
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

        //Validate.matchesPattern(accountDTO.getUsername(), "\\w\\d{3,25}");
        //Validate.matchesPattern(accountDTO.getPassword(), "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        //Validate.matchesPattern(accountDTO.getName(), "\\w{5,20}");
        //Validate.matchesPattern(accountDTO.getSurname(), "\\w{5,20}");
        //Validate.matchesPattern(accountDTO.getEmail(), "/^(S+)@(\\\\S+)$");

        SellerAccount account = mapSellerAccountToEntity(accountDTO);
        sellerAccountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public void delete(SellerAccountDTO accountDTO)   {
        sellerAccountRepository.deactivateUser(mapSellerAccountToEntity(accountDTO).getUsername());
    }

    private SellerAccount mapSellerAccountToEntity(SellerAccountDTO sellerAccountDTO){

        SellerAccount sellerAccount = new SellerAccount();
        sellerAccount.setId(sellerAccountDTO.getId());
        sellerAccount.setUsername(sellerAccountDTO.getUsername());
        sellerAccount.setPassword(sellerAccountDTO.getPassword());
        sellerAccount.setName(sellerAccountDTO.getName());
        sellerAccount.setSurname(sellerAccountDTO.getSurname());
        sellerAccount.setEmail(sellerAccountDTO.getEmail());
        sellerAccount.setActive(sellerAccountDTO.getActive());
        sellerAccount.setRole(sellerAccountDTO.getRole());

        return sellerAccount;
    }

    private SellerAccountDTO mapSellerAccountToDTO(SellerAccount account){
        SellerAccountDTO dto = new SellerAccountDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setName(account.getName());
        dto.setSurname(account.getUsername());
        dto.setEmail(account.getEmail());
        dto.setActive(account.getActive());
        dto.setRole(account.getRole());

        return dto;
    }

    @Override
    public List<AccountDTO> getAllSellers() throws Exception {
        List<SellerAccount> sellers  = sellerAccountRepository.findAll();

        List<AccountDTO> sellersDTO = new ArrayList<AccountDTO>();

        for(int i=0; i<sellers.size(); i++) {
            if(sellers.get(i).getActive()) {
                AccountDTO dto = new SellerAccountDTO();

                dto.setId(sellers.get(i).getId());
                dto.setUsername(sellers.get(i).getUsername());
                dto.setPassword(sellers.get(i).getPassword());
                dto.setName(sellers.get(i).getName());
                dto.setSurname(sellers.get(i).getSurname());
                dto.setEmail(sellers.get(i).getEmail());
                dto.setActive(sellers.get(i).getActive());

                sellersDTO.add(dto);
            }
        }

        return sellersDTO;
    }
}
