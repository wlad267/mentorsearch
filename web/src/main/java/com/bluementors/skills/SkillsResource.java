package com.bluementors.skills;

import com.bluementors.security.AppRoles;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsResource {
    @Autowired
    private SkillService skillService;

    @GetMapping("/all")
    public List<Skill> all() {
        return skillService.fetchAllSkills();
    }

    @PostMapping("save")
    @RolesAllowed(AppRoles.Names.ADMIN)
    public Skill save(@RequestBody Skill skill) {
        return skillService.save(skill);
    }
}
