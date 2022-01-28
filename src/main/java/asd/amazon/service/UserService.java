package asd.amazon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import asd.amazon.entity.Account;
import asd.amazon.repository.AccountRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Logic to get the user form the Database
        Account a = accountRepository.findByUsernameAndActiveTrue(username);
        if(a ==null){
            throw new UsernameNotFoundException("User not found");
        }
        AccountDetails details = new AccountDetails();
        details.accountDetails(a);
        //System.out.println("AUTH = " + a.getRole());
        //System.out.println("DET = " + details.getAuthorities());
        return details;
        //return new User("admin","password",new ArrayList<>());
    }
}
