package com.bluementors;

import com.bluementors.data.UserData;
import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import com.bluementors.training.Skill;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;


public class MentorServiceIT extends BaseTest {

    Logger logger = LoggerFactory.getLogger(MentorServiceIT.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void test_register_mentor(){
        User user = UserData.validUser;

        User savedUuser = userService.register(user);

        Mentor mentor = mentorService.register(savedUuser.getId(), Arrays.asList(new Skill("java"), new Skill("clouding")), 2);

        entityManager.flush();
    }
}
