package tran.example.basicwebapp.controllers.v1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tran.example.basicwebapp.api.v1.asm.AccountResourceAsm;
import tran.example.basicwebapp.api.v1.asm.BlogResourceAsm;
import tran.example.basicwebapp.api.v1.model.AccountResource;
import tran.example.basicwebapp.api.v1.model.BlogResource;
import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.exceptions.BadRequestException;
import tran.example.basicwebapp.exceptions.ConflictException;
import tran.example.basicwebapp.services.AccountService;
import tran.example.basicwebapp.services.exceptions.AccountDoesNotExistException;
import tran.example.basicwebapp.services.exceptions.AccountExistsException;
import tran.example.basicwebapp.services.exceptions.BlogExistsException;


import java.net.URI;

@Controller
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount
    ) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @GetMapping(value="/{accountId}")
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId) {
        Account account = accountService.findAccount(accountId);
        if(account != null)
        {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/{accountId}/blogs")
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource res)
    {
        try {
            Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
            BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
        } catch(AccountDoesNotExistException exception) {
            throw new BadRequestException(exception);
        } catch (BlogExistsException exception) {
            throw new ConflictException(exception);
        }
    }


}
