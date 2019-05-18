package tran.example.basicwebapp.repositories.jpa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tran.example.basicwebapp.domain.Account;

import org.springframework.transaction.annotation.Transactional;
import tran.example.basicwebapp.repositories.AccountRepository;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    private Account account;

    @Before
    @Transactional
    /**
     * note that if we don't have this @Rollback set to True then the objects we just persisted
     * will be rolled back and we will not be able to access them in the scope of our test.
     */
    @Rollback(false)
    public void setUp() {
        account = new Account();
        account.setName("name");
        account.setPassword("password");
        repository.createAccount(account);
    }


    @Test
    @Transactional
    public void testFind() {
        /**
         * we must use the @Transactional annotation because even though this method/test is not changing our database
         * that functions within a transaction or else an EntityManager won't be injectable.
         * the @transactional annotation ensures that the EntityManagers have the same persistence context.
         * this ensures that the entities will become attached during the transaction.
         */
        assertNotNull(repository.findAccount(account.getId()));
    }
}
