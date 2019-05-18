package tran.example.basicwebapp.repositories.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tran.example.basicwebapp.domain.Account;
import tran.example.basicwebapp.domain.Blog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
    public Blog createBlog(Long accountId, Blog data) {
        return null;
    }
}
