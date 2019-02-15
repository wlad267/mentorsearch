package com.bluementors.trainings;

import com.bluementors.IntegrationTest;
import com.bluementors.data.TrainingData;
import com.bluementors.data.UserData;
import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import com.bluementors.training.*;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainingServiceIT extends IntegrationTest {

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


        List<Training> firstSkillTrainings = trainingService.findTrainigsForSkill(theFirstSkill.getId());

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


        List<Training> firstSkillTrainings = trainingService.findTrainigsForSkill(theSecondSkill.getId());

        assertThat(firstSkillTrainings)
                .isNotNull()
                .isEmpty();
    }

    private Mentor register(User user, List<Skill> skills, List<Calendar> calendar) {
        // 1. register a user
        User savedUser = userService.register(user);

        // 3. register the user as mentor
        Mentor registeredMentor = mentorService.register(savedUser.getId(),
                skills.stream().map(Skill::getId).collect(Collectors.toList()),
                2,
                "http://www.linkedin.com/none");

        // 4. update calendar for the mentor
        // (persist cascade for calendar)
        Mentor mentor = this.mentorService.updateCalendar(registeredMentor.getId(), calendar);

        entityManager.flush();

        return mentor;
    }
}
