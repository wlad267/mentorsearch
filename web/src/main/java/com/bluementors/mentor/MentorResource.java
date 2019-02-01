package com.bluementors.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
public class MentorResource {

    @Autowired
    private MentorService mentorService;

    @GetMapping("/fetchAll")
    public List<Mentor> all(){
        return mentorService.listMentors();
    }

    @PostMapping("/register")
    public Mentor register(@RequestBody MentorRegistrationRequest request) {
        return this.mentorService.register(request.userId,
                request.skills,
                request.yearsOfExperience,
                request.linkedInUrl);
    }

    @GetMapping("/cancel/{uid}")
    public ResponseEntity cancelMentoring(@PathVariable("uid") Long uid) {
        return ResponseEntity.ok(mentorService.cancelMentoring(uid));
    }
}
