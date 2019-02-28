package com.bluementors.trainings;

import com.bluementors.data.TrainingData;
import com.bluementors.data.UserData;
import com.bluementors.mentor.Mentor;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillRepository;
import com.bluementors.training.Training;
import com.bluementors.training.TrainingService;
import com.bluementors.user.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainingServiceIT extends TrainingsIntegrationTest {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private TrainingService trainingService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void fetch_available_training_for_skill() {
        // 1. Register skills
        List<Skill> skills = skillRepository.saveAll(TrainingData.trainingSkills());

        // 2. select different skills for mentoring
        Skill theFirstSkill = skills.get(0);
        Skill theSecondSkill = skills.get(1);
        Skill theThirdSkill = skills.get(2);

        //Register 2 mentors on the same skill.
        Mentor mentorJohn = this.register(UserData.JohnMaxwell(),
                Arrays.asList(theFirstSkill, theSecondSkill),
                Arrays.asList(TrainingData.midYearTrainingSection(), TrainingData.summerCampCoding()));

        Mentor mentorRobin = this.register(UserData.RobinWilliams(),
                Arrays.asList(theFirstSkill, theThirdSkill),
                TrainingData.trainingCalendar());


        List<Training> firstSkillTrainings = trainingService.findTrainingsForSkill(theFirstSkill.getId());

        assertThat(firstSkillTrainings)
                .isNotNull()
                .isNotEmpty()
                .hasSize(4); // 2 mentors * 2 calendars each

        assertThat(
                firstSkillTrainings.stream().map(Training::getMentor).collect(Collectors.toList()))
                .isNotNull()
                .isNotEmpty()
                .containsAll(Arrays.asList(mentorJohn, mentorRobin));

    }


    @Test
    public void fetch_available_training_for_skill__no_training() {
        // 1. Register skills
        List<Skill> skills = skillRepository.saveAll(TrainingData.trainingSkills());

        // 2. select different skills for mentoring
        Skill theFirstSkill = skills.get(0);
        Skill theSecondSkill = skills.get(1);
        Skill theThirdSkill = skills.get(2);

        //Register 2 mentors on the same skill.
        Mentor mentorJohn = this.register(UserData.JohnMaxwell(),
                Arrays.asList(theFirstSkill),
                Arrays.asList(TrainingData.midYearTrainingSection(), TrainingData.summerCampCoding()));

        Mentor mentorRobin = this.register(UserData.RobinWilliams(),
                Arrays.asList(theFirstSkill, theThirdSkill),
                TrainingData.trainingCalendar());


        List<Training> firstSkillTrainings = trainingService.findTrainingsForSkill(theSecondSkill.getId());

        assertThat(firstSkillTrainings)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void register_training() {
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

        assertThat(registeredTraining)
                .isNotNull();

    }




}
