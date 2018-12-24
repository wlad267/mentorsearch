package com.bluementors.mentor;

import com.bluementors.User;
import com.bluementors.UserRepository;
import com.bluementors.exception.BusinessException;
import com.bluementors.training.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private UserRepository userRepository;

    List<Mentor> listMentors(){
        return mentorRepository.findAll();
    }

    public Mentor register(Long userId, List<Skill> skills, int yearsOfExperiance) {

        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found"));

        Mentor mentor = new Mentor.Builder()
                .user(user)
                .skills(skills)
                .yearsOfExperience(yearsOfExperiance)
                .build();
        return mentorRepository.save(mentor);
    }

    public Mentor register(Mentor mentor){
        return this.register(mentor.getId(), mentor.getSkills(), mentor.getYearsOfExperince());
    }

}
