package com.bluementors.trainings;

import com.bluementors.BaseTest;
import com.bluementors.data.TrainingData;
import com.bluementors.data.UserData;
import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillRepository;
import com.bluementors.training.Training;
import com.bluementors.training.TrainingService;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainingServiceIT extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TrainingService trainingService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void fetch_available_training_for_skill() {
        User user = UserData.validUser();
        User savedUser = userService.register(user);

        //there is no cascade persist on the skills because they are added by the administrator
        List<Skill> savedSkills = skillRepository.saveAll(TrainingData.trainingSkills());

        Mentor registeredMentor = mentorService.register(savedUser.getId(),
                savedSkills.stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/3453");

        //persist cascade for calendar
        this.mentorService.updateCalendar(registeredMentor.getId(), TrainingData.trainingCalendar());

        entityManager.flush();

        Skill skill = skillRepository.findAll().get(0);
        List<Training> trainings = trainingService.findTrainigsForSkill(skill.getId());

        assertThat(trainings)
                .isNotNull()
                .isNotEmpty();

        entityManager.flush();
    }
}
