package com.bluementors;

import com.bluementors.data.TrainingData;
import com.bluementors.data.UserData;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
        User savedUuser = userService.register(user);
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());
        Mentor registeredMentor = mentorService.register(savedUuser.getId(),
                savedSkills.stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/3453");

        Mentor theSameMentor = mentorService.register(savedUuser.getId(),
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

}
