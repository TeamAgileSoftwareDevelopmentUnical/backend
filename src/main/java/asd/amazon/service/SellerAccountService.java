package asd.amazon.service;

import asd.amazon.dto.SellerAccountDTO;

public interface SellerAccountService {

    public Boolean create(SellerAccountDTO sellerAccountDTO);

    public void delete(SellerAccountDTO sellerAccountDTO);
}
