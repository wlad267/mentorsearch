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
public class MentorServiceIT extends IntegrationTest {

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

        Mentor mentor = registerMentor(UserData.JohnMaxwell(), savedSkills);

        entityManager.flush();
        assertThat(mentor).isNotNull();
    }

    @Test
    @Transactional
    public void register_mentor_fetch_users() {
        //there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor mentor = registerMentor(UserData.JohnMaxwell(), savedSkills);

        entityManager.flush();

        List<User> users = userService.fetchAll();
        assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .containsExactly(mentor.getUser());
    }

    @Test
    @Transactional
    public void register_mentor_update_registration() {
        //there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor registeredMentor = registerMentor(UserData.JohnMaxwell(), savedSkills);

        Mentor theSameMentor = mentorService.register(registeredMentor.getUser().getId(),
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

    @Test(expected = BusinessException.class)
    public void test_add_to_calendar_check_mentor_calendar_constrain() {
        this.mentorService.updateCalendar(1L, null);
    }

    @Test(expected = BusinessException.class)
    public void test_add_to_calendar_unknown_mentor() {
        this.mentorService.updateCalendar(99L, Collections.emptyList());
    }

    @Test
    public void test_add_to_calendar_no_calendar_entry() {
        Mentor registeredMentor = registerMentor(UserData.JohnMaxwell(), new ArrayList<>());
        //persist cascade for calendar
        this.mentorService.updateCalendar(registeredMentor.getId(), TrainingData.trainingCalendar());

        entityManager.flush();
    }

    @Test
    public void fetch_mentors_by_skill() {
        // 1. Add skills into the system
        // there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor mentorJohn = this.registerMentor(UserData.JohnMaxwell(), savedSkills);
        Mentor mentorRobin = this.registerMentor(UserData.RobinWilliams(), savedSkills);

        Skill theSkill = savedSkills.get(1);

        List<Mentor> mentorsForTheFirstSkill = mentorService.searchBySkill(theSkill.getId());

        assertThat(mentorsForTheFirstSkill)
                .isNotEmpty()
                .isNotNull()
                .hasSize(2);
    }

    @Test
    public void fetch_mentors_by_skill_none_matching() {
        // 1. Add skills into the system
        // there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor mentorJohn = this.registerMentor(UserData.JohnMaxwell(), savedSkills.subList(0, 1));
        Mentor mentorRobin = this.registerMentor(UserData.RobinWilliams(), savedSkills.subList(3, 4));

        Skill theSkill = savedSkills.get(2);

        List<Mentor> mentorsForTheFirstSkill = mentorService.searchBySkill(theSkill.getId());

        assertThat(mentorsForTheFirstSkill)
                .isNotNull()
                .isEmpty();
    }

    private Mentor registerMentor(User user, List<Skill> mentoringSkills) {
        User savedUser = userService.register(user);

        return mentorService.register(savedUser.getId(),
                mentoringSkills.stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/3453");
    }

}
