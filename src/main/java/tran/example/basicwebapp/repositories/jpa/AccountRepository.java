package tran.example.basicwebapp.repositories.jpa;

import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;

public interface AccountRepository {
    Account findAccount(Long id);
    Account createAccount(Account data);
    Blog createBlog(Long accountId, Blog data);
}
