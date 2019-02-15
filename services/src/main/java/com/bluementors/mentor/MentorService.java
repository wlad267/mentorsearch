package com.bluementors.mentor;

import com.bluementors.exception.BusinessException;
import com.bluementors.training.Calendar;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillService;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;

    public List<Mentor> listMentors() {
        return mentorRepository.findAll();
    }

    public Mentor fetchByUserId(Long userId) {
        User user = userService.findById(userId);

        return user.getMentor();
    }

    @Transactional
    public Mentor register(Long userId, List<Long> skillIds, int yearsOfExperience, String linkedInUrl) {
        List<Skill> skills = skillService.fetchByIds(skillIds);

        User user = userService.findById(userId);

        // check if the user is already a mentor and take appropriate steps
        if (user.isMentor()) {
            Mentor mentor = user.getMentor();
            mentor.setSkills(skills);
            mentor.setLinkedInUrl(linkedInUrl);
            mentor.setYearsOfExperience(yearsOfExperience);
        } else {
            Mentor mentor = new Mentor.Builder()
                    .user(user)
                    .skills(skills)
                    .yearsOfExperience(yearsOfExperience)
                    .linkedInUrl(linkedInUrl)
                    .build();

            user.setMentor(mentor);
        }

        // there is no need to manually save the entity since we are in a transactional method
        // let's keep it for the clarity of this coding exercise
        mentorRepository.save(user.getMentor());

        return user.getMentor();
    }


    public Mentor cancelMentoring(Long uid) {
        return mentorRepository.cancelMentorig(uid);
    }

    @Transactional
    public Mentor updateCalendar(@NotNull Long mentorId, @NotEmpty List<Calendar> calendar) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(() -> new BusinessException("Mentor not found"));

        mentor.setCalendar(calendar);

        return mentorRepository.save(mentor);
    }

    public List<Mentor> searchBySkill(Long skillId) {
        return mentorRepository.findBySkillsId(skillId);
    }
}
