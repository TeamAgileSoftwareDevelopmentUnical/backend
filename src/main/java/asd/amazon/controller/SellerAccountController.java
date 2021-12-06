package asd.amazon.controller;

import asd.amazon.dto.SellerAccountDTO;
import asd.amazon.service.SellerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SellerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SellerAccountController {

    public static final String ROOT = "/seller-account";
    public static final String CREATE = "/create-seller";

    @Autowired
    private SellerAccountService sellerAccountService;

    @PostMapping(CREATE)
    public ResponseEntity<SellerAccountDTO> create(@RequestBody SellerAccountDTO sellerAccountDTO){
        return ResponseEntity.ok(sellerAccountService.create(sellerAccountDTO));
    }
}
