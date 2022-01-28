package asd.amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.SellerAccountDTO;
import asd.amazon.service.SellerAccountService;

@RestController
@RequestMapping(SellerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SellerAccountController {

    public static final String ROOT = "/seller-account";
    public static final String CREATE = "/create-seller";
    public static final String LOGIN = "/login";
    public static final String GETSELLERS = "/get-sellers";
    public static final String GETACCOUNT = "/get-account/";


    @Autowired
    private SellerAccountService sellerAccountService;

    /*@PostMapping(CREATE)
    public ResponseEntity<SellerAccountDTO> create(@RequestBody SellerAccountDTO sellerAccountDTO){
        return ResponseEntity.ok(sellerAccountService.create(sellerAccountDTO));
    }*/
    // FIXME:
    @PostMapping(LOGIN)
    public ResponseEntity<SellerAccountDTO> login(@RequestParam("username") String username,
                                                    @RequestParam("password") String password) {
        //return ResponseEntity.ok(sellerAccountService.login(username, password));
        return null;//TODO: put in the service login method
    }

    @GetMapping(GETSELLERS)
    public ResponseEntity<List<AccountDTO>> getSellers() throws Exception {
        return ResponseEntity.ok(sellerAccountService.getAllSellers());
    }

    // FIXME: Needs to be generalized!
    @GetMapping(GETACCOUNT+ "{id}")
    public ResponseEntity<AccountDTO> getCustomerAccount(@PathVariable(name = "id", required = true) final Long id) throws Exception {
        return ResponseEntity.ok(sellerAccountService.getSellerAccountById(id));
    }
}
