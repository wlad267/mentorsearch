package com.bluementors.skills;

import com.bluementors.security.AppRoles;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsResource {

    private static Logger logger = LoggerFactory.getLogger(SkillsResource.class);

    @Autowired
    private SkillService skillService;

    @GetMapping("/all")
    public List<Skill> all() {
        logger.info("listing fetchAll skills");
        return skillService.fetchAllSkills();
    }

    @PostMapping("save")
    @RolesAllowed(AppRoles.Names.ADMIN)
    public Skill save(@RequestBody Skill skill) {
        logger.info("saving skill: " + skill);
        return skillService.save(skill);
    }
}
