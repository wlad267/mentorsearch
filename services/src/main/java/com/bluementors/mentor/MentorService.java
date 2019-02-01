package com.bluementors.mentor;

import com.bluementors.exception.BusinessException;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillService;
import com.bluementors.user.User;
import com.bluementors.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillService skillService;

    List<Mentor> listMentors(){
        return mentorRepository.findAll();
    }

    @Transactional
    public Mentor register(Long userId, List<Long> skillIds, int yearsOfExperience, String linkedInUrl) {

        List<Skill> skills = skillService.fetchByIds(skillIds);

        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found"));

        Mentor mentor = new Mentor.Builder()
                .user(user)
                .skills(skills)
                .yearsOfExperience(yearsOfExperience)
                .linkedInUrl(linkedInUrl)
                .build();

        user.setMentor(mentor);

        mentorRepository.save(mentor);

        return mentor;
    }


    public Mentor cancelMentoring(Long uid) {
        return mentorRepository.cancelMentorig(uid);
    }

}
