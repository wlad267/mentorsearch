package com.bluementors;

import com.bluementors.data.UserData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;



public class UserServiceIT extends BaseTest {

    Logger logger = LoggerFactory.getLogger(UserServiceIT.class);

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test(expected = ConstraintViolationException.class)
    public void test_add_invalid_user(){
        User user = UserData.invalidUser;

        User savedUuser = userService.register(user);

        entityManager.flush();

    }

    @Test
    public void test_add_valid_user(){
        User user = UserData.validUser;

        User savedUuser = userService.register(user);

        entityManager.flush();
    }
}
