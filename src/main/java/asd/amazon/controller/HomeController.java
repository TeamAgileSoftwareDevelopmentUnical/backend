package asd.amazon.controller;

import asd.amazon.dto.AccountDTO;
import asd.amazon.dto.CustomerAccountDTO;
import asd.amazon.dto.SellerAccountDTO;
import asd.amazon.entity.Account;
import asd.amazon.entity.SellerAccount;
import asd.amazon.repository.CustomerAccountRepository;
import asd.amazon.repository.SellerAccountRepository;
import asd.amazon.request.JwtRequest;
import asd.amazon.responses.JwtResponse;
import asd.amazon.service.AccountService;
import asd.amazon.service.CustomerAccountService;
import asd.amazon.service.SellerAccountService;
import asd.amazon.service.UserService;
import asd.amazon.utils.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private SellerAccountService sellerAccountService;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    SellerAccountRepository sellerAccountRepository;

    @Autowired
    AccountService accountService;

    @PostMapping("/create-customer")
    public ResponseEntity<Boolean> create(@RequestBody CustomerAccountDTO accountDTO) {
        return ResponseEntity.ok(customerAccountService.create(accountDTO));
    }

    @PostMapping("/create-seller")
    public ResponseEntity<Boolean> create(@RequestBody SellerAccountDTO accountDTO) {
        return ResponseEntity.ok(sellerAccountService.create(accountDTO));
    }

    @GetMapping(value = "/remove-account")
    public ResponseEntity<Boolean> accountDeactivated(@RequestParam(name = "user_id")Long id){
        return ResponseEntity.ok().body(accountService.accountDeactivate(id));
    }


    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        System.out.println(jwtRequest.getUsername());
        System.out.println(jwtRequest.getPassword());
        AccountDTO a = customerAccountService.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        if(a != null){
            final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

            final String token =
                    jwtUtility.generateToken(userDetails);
            // check the user is customer
            SellerAccount account = sellerAccountRepository.findSellerAccountById(a.getId());
            if (account != null) {
                return new JwtResponse(token, account.getId(), "Seller");
            }else {
                return new JwtResponse(token, a.getId(), "Customer");
            }
        } else {
            System.out.println("Account non esistente!");
        }
        return null;

        /*try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponse(token); */
    }
}
