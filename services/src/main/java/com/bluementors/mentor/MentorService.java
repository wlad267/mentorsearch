package com.bluementors.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    List<Mentor> listMentors(){
        return mentorRepository.findAll();
    }
}
