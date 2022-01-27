package asd.amazon.controller;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.dto.SellerAccountDTO;
import asd.amazon.service.SellerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SellerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SellerAccountController {

    public static final String ROOT = "/seller-account";
    public static final String CREATE = "/create-seller";
    public static final String LOGIN = "/login";
    public static final String GETSELLERS = "/get-sellers";

    @Autowired
    private SellerAccountService sellerAccountService;

    /*@PostMapping(CREATE)
    public ResponseEntity<SellerAccountDTO> create(@RequestBody SellerAccountDTO sellerAccountDTO){
        return ResponseEntity.ok(sellerAccountService.create(sellerAccountDTO));
    }*/
    @PostMapping(LOGIN)
    public ResponseEntity<CustomerAccountDTO> login(@RequestParam("username") String username,
                                                    @RequestParam("password") String password) {
        //return ResponseEntity.ok(sellerAccountService.login(username, password));
        return null;//TODO: put in the service login method
    }

    @GetMapping(GETSELLERS)
    public ResponseEntity<List<AccountDTO>> getSellers() throws Exception {
        return ResponseEntity.ok(sellerAccountService.getAllSellers());
    }
}
