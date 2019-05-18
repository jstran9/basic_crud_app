package tran.example.basicwebapp.repositories;

import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;

import java.util.List;

public interface AccountRepository {
    List<Account> findAllAccounts();
    Account findAccount(Long id);
    Account createAccount(Account data);
    Account findAccountByName(String name);
}
