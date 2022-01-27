package asd.amazon.service;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.SellerAccountDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SellerAccountService {

    public ResponseEntity create(SellerAccountDTO sellerAccountDTO);

    public void delete(SellerAccountDTO sellerAccountDTO);

    public List<AccountDTO> getAllSellers() throws Exception;
}
