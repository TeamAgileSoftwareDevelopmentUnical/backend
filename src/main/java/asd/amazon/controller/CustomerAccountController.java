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

}
