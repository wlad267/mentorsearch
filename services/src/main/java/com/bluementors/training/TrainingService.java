package com.bluementors.training;

import com.bluementors.mentor.Mentor;
import com.bluementors.mentor.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingService {
    @Autowired
    private MentorService mentorService;

    @Autowired
    private SkillService skillService;

    public List<Training> findTrainigsForSkill(Long skillId) {

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

}
