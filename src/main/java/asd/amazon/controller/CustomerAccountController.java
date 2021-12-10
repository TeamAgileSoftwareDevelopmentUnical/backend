package asd.amazon.controller;

import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.service.CustomerAccountService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerAccountController {

    public static final String ROOT = "/customer-account";
    public static final String CREATE = "/create";
    public static final String LOGIN = "/login";
    public static final String DELETE = "/delete";

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping(CREATE)
    public ResponseEntity<CustomerAccountDTO> create(@RequestBody CustomerAccountDTO accountDTO) {
        return ResponseEntity.ok(customerAccountService.create(accountDTO));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<CustomerAccountDTO> login(@RequestParam("username") String username,
                                                    @RequestParam("password") String password) {
        return ResponseEntity.ok(customerAccountService.login(username, password));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity delete(@RequestBody CustomerAccountDTO accountDTO)  {
        customerAccountService.delete(accountDTO);
        return ResponseEntity.ok().build();
    }
}
