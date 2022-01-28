package asd.amazon.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.SellerAccountDTO;

public interface SellerAccountService {

    public ResponseEntity create(SellerAccountDTO sellerAccountDTO);

    public void delete(SellerAccountDTO sellerAccountDTO);

    public List<AccountDTO> getAllSellers() throws Exception;
    
    public AccountDTO getSellerAccountById(Long id) throws Exception;
}
