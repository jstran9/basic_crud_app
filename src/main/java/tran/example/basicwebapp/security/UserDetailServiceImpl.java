package tran.example.basicwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.services.AccountService;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService service; // this helps retrieve the account object.

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = service.findByAccountName(name);
        if(account == null) {
            throw new UsernameNotFoundException("no user found with " + name);
        }
        // create the AccountUserDetails to adapt our account object to the Spring Security object.
        return new AccountUserDetails(account);
    }
}
