package com.bluementors.trainings;

import com.bluementors.IntegrationTest;
import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import com.bluementors.training.Calendar;
import com.bluementors.training.Skill;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TrainingsIntegrationTest extends IntegrationTest {

    @Autowired
    protected UserService userService;

    @Autowired
    protected MentorService mentorService;

    protected Mentor register(User user, List<Skill> skills, List<Calendar> calendar) {
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
