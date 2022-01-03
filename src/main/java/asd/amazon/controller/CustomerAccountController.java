package asd.amazon.controller;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.request.CustomerAddressRequest;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(CustomerAccountController.ROOT)
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.PATCH,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class CustomerAccountController {

    public static final String ROOT = "/customer-account";
    public static final String CREATE = "/create";
    public static final String LOGIN = "/login";
    public static final String DELETE = "/delete";
    public static final String GETACCOUNT = "/get-account/";
    public static final String UPDATE = "/update";

    @Autowired
    private CustomerAccountService customerAccountService;

/*    @PostMapping(CREATE)
    public ResponseEntity<CustomerAccountDTO> create(@RequestBody CustomerAccountDTO accountDTO) {
        return ResponseEntity.ok(customerAccountService.create(accountDTO));
    }*/

    @PostMapping(LOGIN)
    public ResponseEntity<CustomerAccountDTO> login(@RequestParam("username") String username,
                                                    @RequestParam("password") String password) {
        System.out.println("ciao controller");
        return ResponseEntity.ok(customerAccountService.login(username, password));
    }

    @DeleteMapping(DELETE + "{id}")
    public HttpStatus delete(@PathVariable(name = "id", required = true) final Long id) throws Exception {
        customerAccountService.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping(UPDATE)
    public HttpStatus update(@Valid @RequestBody(required = true) AccountDTO account) throws Exception{
        customerAccountService.update(account);
        return HttpStatus.OK;
    }

    @GetMapping(GETACCOUNT+ "{id}")
    public ResponseEntity<AccountDTO> getCustomerAccount(@PathVariable(name = "id", required = true) final Long id) throws Exception {
        return ResponseEntity.ok(customerAccountService.getCustomerAccountById(id));
    }

    @PostMapping(value = "/set-shipping-address")
    public ResponseEntity<Boolean> addShippingAddress(@RequestBody CustomerAddressRequest request){
        return ResponseEntity.ok().body(customerAccountService.setShippingAddress(request));
    }
}
