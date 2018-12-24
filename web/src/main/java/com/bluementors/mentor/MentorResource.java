package com.bluementors.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentors")
public class MentorResource {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/all")
    public List<Mentor> all(){
        return mentorService.listMentors();
    }

    @PostMapping("/register")
    public Mentor register(Mentor mentor){
        return this.mentorService.register(mentor);
    }
}
