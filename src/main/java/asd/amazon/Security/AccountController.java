package asd.amazon.Security;

import asd.amazon.entity.Account;
import asd.amazon.entity.CustomerAccount;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @RequestMapping("/login")
    public boolean login(@RequestBody CustomerAccount user) {

        if(customerAccountService.authenticate(user.getUsername(), user.getPassword()) != null)
            return true;

        return false;
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

}
