package tran.example.basicwebapp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.repositories.AccountRepository;
import tran.example.basicwebapp.repositories.BlogRepository;
import tran.example.basicwebapp.services.AccountService;
import tran.example.basicwebapp.services.exceptions.AccountDoesNotExistException;
import tran.example.basicwebapp.services.exceptions.AccountExistsException;
import tran.example.basicwebapp.services.exceptions.BlogExistsException;
import tran.example.basicwebapp.services.util.AccountList;
import tran.example.basicwebapp.services.util.BlogList;

@Service
@Transactional // this is the same as putting the annotation at each method.
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private BlogRepository blogRepo;

    @Override
    public Account findAccount(Long id) {
        return accountRepo.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());
        if(account != null)
        {
            // account can tbe found so we throw an exception.
            throw new AccountExistsException();
        }
        return accountRepo.createAccount(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blogSameTitle = blogRepo.findBlogByTitle(data.getTitle());

        // exception is thrown below if blog or account cannot be found.
        if(blogSameTitle != null)
        {
            throw new BlogExistsException();
        }

        Account account = accountRepo.findAccount(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }

        // sets a relationship between the blog and an account before persisting the blog.
        Blog createdBlog = blogRepo.createBlog(data);

        // when we set an owner and the transaction ends then the data gets persisted to the db.
        createdBlog.setOwner(account);

        return createdBlog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
