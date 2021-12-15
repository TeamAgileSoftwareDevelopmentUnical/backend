package asd.amazon.Security;

import asd.amazon.Security.response.LoginResponse;
import asd.amazon.entity.Account;
import asd.amazon.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private JwtConfiguration jwtConfiguration;

    @RequestMapping("/login")
    public LoginResponse login(@RequestBody Account user) {

        if(customerAccountService.authenticate(user.getUsername(), user.getPassword()) != null)
            return new LoginResponse(jwtConfiguration.generateToken(user), user.getUsername());

        return null;
    }


    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

}
