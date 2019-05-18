package tran.example.basicwebapp.repositories.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.repositories.AccountRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class JpaAccountRepository implements AccountRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     * exception translation: any kinds of exceptions that get thrown (such as JPA Exceptions) can get caught in our service
     * layer.
     */

    @Override
    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Account> findAllAccounts() {
        // gets all the account entities in our db.
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }

    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("SELECT a FROM Account a WHERE a.name=?1");
        query.setParameter(1, name);
        List<Account> accounts = query.getResultList();
        if(accounts.size() == 0) {
            return null;
        } else {
            return accounts.get(0);
        }
    }
}
