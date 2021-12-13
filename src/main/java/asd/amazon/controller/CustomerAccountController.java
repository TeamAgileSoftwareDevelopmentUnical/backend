package asd.amazon.controller;

import asd.amazon.Security.JwtConfiguration;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerAccountController {

    public static final String ROOT = "/customer-account";
    public static final String CREATE = "/create";
    public static final String LOGIN = "/login";
    public static final String GETACCOUNT = "/get-account";

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping(CREATE)
    public ResponseEntity<CustomerAccountDTO> create(@RequestBody CustomerAccountDTO accountDTO) {
        return ResponseEntity.ok(customerAccountService.create(accountDTO));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<CustomerAccountDTO> login(@RequestParam("username") String username,
                                                    @RequestParam("password") String password) {
        System.out.println("ciao controller");
        return ResponseEntity.ok(customerAccountService.login(username, password));
    }

    @GetMapping(GETACCOUNT)
    public ResponseEntity<CustomerAccountDTO> getCustomerAccount(@RequestHeader(value = "Authorization") HttpHeaders header, @RequestParam("id") Long id){
        System.out.println(header);
        //if (jwtConfiguration.validateToken(header.getToken()))
        //    return ResponseEntity.ok(customerAccountService.getCustomerAccountById(id));
        return null;
    }

}
