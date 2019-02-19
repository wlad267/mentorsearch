package com.bluementors.mentor;

import com.bluementors.security.AppRoles;
import com.bluementors.training.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/mentors")
public class MentorResource {

    @Autowired
    private MentorService mentorService;

    @GetMapping("fetchAll")
    public List<Mentor> all(){
        return mentorService.listMentors();
    }

    @GetMapping("byUserId/{userId}")
    @RolesAllowed({AppRoles.Names.USER})
    public Mentor fetchMentorByuserId(@PathVariable("userId") Long userId) {
        return mentorService.findByUserId(userId);
    }

    @PostMapping("register")
    public Mentor register(@RequestBody MentorRegistrationRequest request) {
        return this.mentorService.register(request.userId,
                request.skills,
                request.yearsOfExperience,
                request.linkedInUrl);
    }

    @GetMapping("cancel/{uid}")
    public ResponseEntity cancelMentoring(@PathVariable("uid") Long uid) {
        return ResponseEntity.ok(mentorService.cancelMentoring(uid));
    }

    @PostMapping("{mentorId}/calendar/save")
    public ResponseEntity saveCalendar(@PathVariable("mentorId") Long mentorId, @RequestBody List<Calendar> calendar) {
        return ResponseEntity.ok(mentorService.updateCalendar(mentorId, calendar));
    }
}
