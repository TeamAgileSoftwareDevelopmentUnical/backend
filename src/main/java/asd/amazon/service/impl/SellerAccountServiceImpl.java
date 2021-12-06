package asd.amazon.service.impl;

import asd.amazon.dto.SellerAccountDTO;
import asd.amazon.entity.SellerAccount;
import asd.amazon.repository.SellerAccountRepository;
import asd.amazon.service.SellerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerAccountServiceImpl implements SellerAccountService {

    @Autowired
    private SellerAccountRepository sellerAccountRepository;

    @Override
    @Transactional(readOnly = false)
    public SellerAccountDTO create(SellerAccountDTO sellerAccountDTO) {
        //TO DO: check

        return mapSellerAccountToDTO(sellerAccountRepository.save(mapSellerAccountToEntity(sellerAccountDTO)));
    }

    private SellerAccount mapSellerAccountToEntity(SellerAccountDTO sellerAccountDTO){

        SellerAccount sellerAccount = new SellerAccount();
        sellerAccount.setId(sellerAccountDTO.getId());
        sellerAccount.setUsername(sellerAccountDTO.getUsername());
        sellerAccount.setPassword(sellerAccountDTO.getPassword());
        sellerAccount.setName(sellerAccountDTO.getName());
        sellerAccount.setSurname(sellerAccountDTO.getSurname());
        sellerAccount.setEmail(sellerAccountDTO.getEmail());

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

        return dto;
    }
}
