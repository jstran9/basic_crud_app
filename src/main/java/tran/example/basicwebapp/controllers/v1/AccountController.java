package tran.example.basicwebapp.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tran.example.basicwebapp.api.v1.asm.AccountListResourceAsm;
import tran.example.basicwebapp.api.v1.asm.AccountResourceAsm;
import tran.example.basicwebapp.api.v1.asm.BlogListResourceAsm;
import tran.example.basicwebapp.api.v1.asm.BlogResourceAsm;
import tran.example.basicwebapp.api.v1.model.AccountListResource;
import tran.example.basicwebapp.api.v1.model.AccountResource;
import tran.example.basicwebapp.api.v1.model.BlogListResource;
import tran.example.basicwebapp.api.v1.model.BlogResource;
import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;
import tran.example.basicwebapp.exceptions.BadRequestException;
import tran.example.basicwebapp.exceptions.ConflictException;
import tran.example.basicwebapp.exceptions.ForbiddenException;
import tran.example.basicwebapp.exceptions.NotFoundException;
import tran.example.basicwebapp.services.AccountService;
import tran.example.basicwebapp.services.exceptions.AccountDoesNotExistException;
import tran.example.basicwebapp.services.exceptions.AccountExistsException;
import tran.example.basicwebapp.services.exceptions.BlogExistsException;
import tran.example.basicwebapp.services.util.AccountList;
import tran.example.basicwebapp.services.util.BlogList;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount
    ) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @GetMapping(value="/{accountId}")
    @PreAuthorize("permitAll")
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
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource res)
    {
        // we can also do the below using Annotations.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Account loggedIn = accountService.findByAccountName(userDetails.getUsername());
            if(loggedIn.getId().equals(accountId)) {
                try {
                    Blog createdBlog = accountService.createBlog(accountId, res.toBlog());
                    BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
                    return new ResponseEntity<>(createdBlogRes, headers, HttpStatus.CREATED);
                } catch(AccountDoesNotExistException exception) {
                    throw new BadRequestException(exception);
                } catch (BlogExistsException exception) {
                    throw new ConflictException(exception);
                }
            } else {
                throw new ForbiddenException();
            }
        } else {
            throw new ForbiddenException();
        }
    }

    @GetMapping
    @PreAuthorize("permitAll")
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name", required = false) String name) {
        AccountList list = null;
        if(name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountName(name);
            if(account == null) {
                list = new AccountList(new ArrayList<Account>());
            } else {
                list = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(value="/{accountId}/blogs")
    @PreAuthorize("permitAll")
    public ResponseEntity<BlogListResource> findAllBlogs(
            @PathVariable Long accountId) {
        try {
            BlogList blogList = accountService.findBlogsByAccount(accountId);
            BlogListResource blogListRes = new BlogListResourceAsm().toResource(blogList);
            return new ResponseEntity<>(blogListRes, HttpStatus.OK);
        } catch(AccountDoesNotExistException exception)
        {
            throw new NotFoundException(exception);
        }
    }



}
