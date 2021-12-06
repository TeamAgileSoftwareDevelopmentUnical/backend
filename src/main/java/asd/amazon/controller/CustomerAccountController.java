package asd.amazon.controller;

import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerAccountController {

    public static final String ROOT = "/customer-account";
    public static final String CREATE = "/create";

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping(CREATE)
    public ResponseEntity<CustomerAccountDTO> create(@RequestBody CustomerAccountDTO accountDTO) {
        return ResponseEntity.ok(customerAccountService.create(accountDTO));
    }
}
