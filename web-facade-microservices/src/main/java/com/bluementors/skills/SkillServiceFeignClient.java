package com.bluementors.skills;


import com.bluementors.training.Skill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("mentor-search")
public interface SkillServiceFeignClient {

    @GetMapping(value = "/mentor-search/api/skills/all", produces = "application/json")
    List<Skill> fetchAllSkills(@RequestHeader("X_JWT") String jwtHeader);

    @PostMapping(value = "/mentor-search/api/skills/save", produces = "application/json", consumes = "application/json")
    Skill save(@RequestHeader("X_JWT") String jwtHeader, @RequestBody Skill skill);
}
