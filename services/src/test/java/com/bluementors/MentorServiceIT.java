package com.bluementors;

import com.bluementors.data.TrainingData;
import com.bluementors.data.UserData;
import com.bluementors.exception.BusinessException;
import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillRepository;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
public class MentorServiceIT extends BaseTest {

    Logger logger = LoggerFactory.getLogger(MentorServiceIT.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillRepository skillRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @Transactional
    public void register_mentor() {
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());
        User user = UserData.validUser();
        User savedUuser = userService.register(user);

        Mentor mentor = mentorService.register(savedUuser.getId(),
                savedSkills.stream().map(Skill::getId).collect(Collectors.toList()), 2, "");

        entityManager.flush();
        assertThat(mentor).isNotNull();
    }

    @Test
    @Transactional
    public void register_mentor_fetch_users() {
        User user = UserData.validUser();
        User savedUuser = userService.register(user);

        //there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor mentor = mentorService.register(savedUuser.getId(),
                savedSkills.stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/3453");

        entityManager.flush();

        List<User> users = userService.fetchAll();
        assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .containsExactly(savedUuser);
    }

    @Test
    @Transactional
    public void register_mentor_update_registration() {
        User user = UserData.validUser();
        User savedUser = userService.register(user);

        //there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor registeredMentor = mentorService.register(savedUser.getId(),
                savedSkills.stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/3453");

        Mentor theSameMentor = mentorService.register(savedUser.getId(),
                TrainingData.trainingSkills().stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/3453");

        entityManager.flush();

        List<User> users = userService.fetchAll();
        assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .containsExactly(registeredMentor.getUser());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_add_to_calendar_check_mentor_constrain() {
        this.mentorService.updateCalendar(null, Collections.emptyList());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void test_add_to_calendar_check_mentor_calendar_constrain() {
        this.mentorService.updateCalendar(1L, null);
    }

    @Test(expected = BusinessException.class)
    public void test_add_to_calendar_unknown_mentor() {
        this.mentorService.updateCalendar(99L, Collections.emptyList());
    }

    @Test
    public void test_add_to_calendar_no_calendar_entry() {
        User user = UserData.validUser();
        User savedUser = userService.register(user);

        Mentor registeredMentor = mentorService.register(savedUser.getId(),
                new ArrayList<>(),
                2,
                "http://www.linkedin.com/3453");

        //persist cascade for calendar
        this.mentorService.updateCalendar(registeredMentor.getId(), TrainingData.trainingCalendar());

        entityManager.flush();
    }
}
