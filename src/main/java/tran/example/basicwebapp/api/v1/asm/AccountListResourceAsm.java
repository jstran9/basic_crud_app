package tran.example.basicwebapp.api.v1.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tran.example.basicwebapp.api.v1.model.AccountListResource;
import tran.example.basicwebapp.api.v1.model.AccountResource;
import tran.example.basicwebapp.controllers.v1.AccountController;
import tran.example.basicwebapp.services.util.AccountList;

import java.util.List;

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}
