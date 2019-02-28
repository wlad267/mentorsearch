package com.bluementors.skills;

import com.bluementors.JwtReader;
import com.bluementors.training.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/skills")
public class SkillsResource {

    private static Logger logger = LoggerFactory.getLogger(SkillsResource.class);

    @Autowired
    private SkillServiceFeignClient skillService;

    @Autowired
    private JwtReader jwtReader;

    @GetMapping("all")
    public List<Skill> all(HttpServletRequest httpServletRequest) {
        logger.info("listing fetchAll skills");
        return skillService.fetchAllSkills(jwtReader.parseAuthentication(httpServletRequest));
    }

    @PostMapping("save")
    public Skill save(HttpServletRequest httpServletRequest, @RequestBody Skill skill) {
        logger.info("saving skill: " + skill);
        return skillService.save(jwtReader.parseAuthentication(httpServletRequest), skill);
    }
}
