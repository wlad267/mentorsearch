package com.bluementors;

import com.bluementors.data.UserData;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
