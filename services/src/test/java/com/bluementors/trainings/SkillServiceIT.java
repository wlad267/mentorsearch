package com.bluementors.trainings;

import com.bluementors.BaseTest;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SkillServiceIT extends BaseTest {

    @Autowired
    private SkillService skillService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save_skill_test() {
        Skill skill = new Skill();
        skill.setName("java");
        skill.setDescription("a must have skill of 201x years");

        skillService.save(skill);
        entityManager.flush();

        Skill saveSkill = skillService.fetchAllSkills().get(0);

        assertThat(saveSkill).isNotNull();

        saveSkill.setActive(false);

        skillService.save(saveSkill);
        entityManager.flush();

        List<Skill> skills = skillService.fetchAllSkills();
        assertThat(skills).containsExactly(saveSkill);
    }
}
