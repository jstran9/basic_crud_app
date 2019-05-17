package tran.example.basicwebapp.services;


import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;

public interface AccountService {
    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Blog createBlog(Long accountId, Blog data);
}
