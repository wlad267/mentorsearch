package com.bluementors.trainings;

import com.bluementors.data.TrainingData;
import com.bluementors.data.UserData;
import com.bluementors.mentor.Mentor;
import com.bluementors.training.*;
import com.bluementors.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FindTrainingIT extends TrainingsIntegrationTest {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private FindTraining findTraining;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void test_fetch_trainings() {
        // 1. Register skills
        List<Skill> skills = skillRepository.saveAll(TrainingData.trainingSkills());

        // 2. select different skills for mentoring
        Skill theFirstSkill = skills.get(0);

        //Register 2 mentors on the same skill.
        Mentor mentorJohn = this.register(UserData.JohnMaxwell(),
                Arrays.asList(theFirstSkill),
                Arrays.asList(TrainingData.midYearTrainingSection(), TrainingData.summerCampCoding()));

        User robin = userService.register(UserData.RobinWilliams());

        Training registeredTraining = trainingService.bookTraining(robin.getId(),
                mentorJohn.getSkills().get(0).getId(),
                mentorJohn.getCalendar().get(0).getId());

        List<Training> trainingForRobin = findTraining.findTrainigs(registeredTraining.getId());

        assertThat(trainingForRobin)
                .isNotNull()
                .isNotEmpty()
                .extracting(Training::getSkill)
                .containsOnly(theFirstSkill);
    }
}
