package com.bluementors.training;

import com.bluementors.exception.BusinessException;
import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> findTrainingsForSkill(Long skillId) {

        Skill theSkill = skillService.fetchSkill(skillId);

        List<Mentor> mentors = mentorService.searchBySkill(skillId);


        List<Training> trainings = new ArrayList<>();

        mentors.forEach(mentor -> {
            mentor.getCalendar().forEach(calendar -> {
                trainings.add(new Training.Builder()
                        .skill(theSkill)
                        .mentor(mentor)
                        .calendar(calendar)
                        .build());
            });
        });
        return trainings;
    }

    public Training bookTraining(@NotNull Long userId, @NotNull Long skillId, @NotNull Long calendarId) {

        Training training = new Training();

        Mentor mentor = mentorService.searchByCalendarId(calendarId);

        //TODO move to calendar service
        training.setCalendar(mentor.getCalendar()
                .stream()
                .filter(m -> m.getId().equals(calendarId))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Something got fishy -> provided calendar id not found"))
        );

        training.setMentor(mentor);

        training.setSkill(skillService.fetchSkill(skillId));

        training.setUser(userService.findById(userId));

        training.setStatus(TrainingStatus.NO_STARTED);

        return trainingRepository.save(training);
    }

    public List<Training> findPlannedTrainigs(Long userId) {

        return null;
    }
}
