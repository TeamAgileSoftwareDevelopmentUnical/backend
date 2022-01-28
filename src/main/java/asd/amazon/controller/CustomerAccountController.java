package asd.amazon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import asd.amazon.dto.AccountDTO;
import asd.amazon.service.CustomerAccountService;

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

    // FIXME: Needs to be generalized!
    @GetMapping(GETACCOUNT+ "{id}")
    public ResponseEntity<AccountDTO> getCustomerAccount(@PathVariable(name = "id", required = true) final Long id) throws Exception {
        return ResponseEntity.ok(customerAccountService.getCustomerAccountById(id));
    }

}
