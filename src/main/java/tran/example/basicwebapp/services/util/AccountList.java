package tran.example.basicwebapp.services.util;


import tran.example.basicwebapp.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountList {

    private List<Account> accounts = new ArrayList<Account>();

    public AccountList(List<Account> list) {
        this.accounts = list;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
