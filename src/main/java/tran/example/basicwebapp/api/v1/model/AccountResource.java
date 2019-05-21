package tran.example.basicwebapp.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import tran.example.basicwebapp.domain.Account;

/**
 * Created by Chris on 6/28/14.
 */
public class AccountResource extends ResourceSupport {
    private Long rid;

    private String name;

    private String password;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * when getting our object JSON will ignore the password field.
     */
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /*
     * tells Jackson that password is still a property and we only want to ignore the serialization of the object when
     * sending data back to the client
     */
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        return account;
    }
}
