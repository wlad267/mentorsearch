package com.bluementors.training;

import com.bluementors.exception.BusinessException;
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

    public List<Skill> fetchByIds(List<Long> ids) {
        return skillRepository.fetchByIds(ids);
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill fetchSkill(Long skillId) {
        return skillRepository.findById(skillId).orElseThrow(() -> new BusinessException("desired skill not found"));
    }
}
