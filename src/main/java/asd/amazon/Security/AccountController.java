package asd.amazon.Security;

import asd.amazon.Security.response.LoginResponse;
import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @RequestMapping("/login")
    public LoginResponse login(@RequestBody Account user) {
        System.out.println("loggah " + user);
        System.out.println(customerAccountService.authenticate(user.getUsername(), user.getPassword()));
        AccountDTO account = customerAccountService.authenticate(user.getUsername(), user.getPassword());
        if(account != null)
            return new LoginResponse(jwtConfiguration.generateToken(user), account.getUsername(), account.getId());

        return null;
    }


    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        System.out.println("userrrr");
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

    @GetMapping("/profile/")
    public ResponseEntity<CustomerAccountDTO> getCustomerAccount(@RequestHeader(value = "Authorization") HttpHeaders header, @RequestParam("id") Long id){
        System.out.println("headerrr"+header);
        //if (jwtConfiguration.validateToken(header.getToken()))
        //    return ResponseEntity.ok(customerAccountService.getCustomerAccountById(id));
        return null;
    }

}
