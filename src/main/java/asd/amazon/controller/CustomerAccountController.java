package asd.amazon.controller;

import asd.amazon.Security.JwtConfiguration;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.service.CustomerAccountService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(CustomerAccountController.ROOT)
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerAccountController {

    public static final String ROOT = "/customer-account";
    public static final String CREATE = "/create";
    public static final String LOGIN = "/login";
    public static final String DELETE = "/delete";
    public static final String GETACCOUNT = "/get-account";
    public static final String UPDATE = "/update";

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
        return ResponseEntity.ok(customerAccountService.login(username, password));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity delete(@RequestBody CustomerAccountDTO accountDTO)  {
        customerAccountService.delete(accountDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(UPDATE)
    public ResponseEntity<CustomerAccountDTO> update(@RequestBody Map<String,Object> payload ){
        Long id = (Long) payload.get("id");
        String name = (String) payload.get("name");
        String surname = (String) payload.get("surname");
        String mail = (String) payload.get("mail");
        //TODO: maybe is better to return an HttpStatus?
        return ResponseEntity.ok(customerAccountService.update(id, name, surname, mail));
    }

    @GetMapping(GETACCOUNT)
    public ResponseEntity<CustomerAccountDTO> getCustomerAccount(@RequestHeader(value = "Authorization") String token, @RequestParam("id") Long id){
        System.out.println(token);
        //if (jwtConfiguration.validateToken(header.get("Authorization")))
            return ResponseEntity.ok(customerAccountService.getCustomerAccountById(id));
    }
}
