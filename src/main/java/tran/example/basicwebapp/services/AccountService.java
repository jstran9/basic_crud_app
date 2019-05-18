package tran.example.basicwebapp.services;


import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.services.util.AccountList;
import tran.example.basicwebapp.services.util.BlogList;

public interface AccountService {
    Account findAccount(Long id);
    Account createAccount(Account data);
    Blog createBlog(Long accountId, Blog data);
    BlogList findBlogsByAccount(Long accountId);
    AccountList findAllAccounts();
    Account findByAccountName(String name);
}
