package com.bluementors.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    public List<Skill> fetchAllSkills() {
        return skillRepository.findAll();
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }
}
