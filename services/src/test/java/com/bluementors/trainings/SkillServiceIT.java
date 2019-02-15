package com.bluementors.trainings;

import com.bluementors.IntegrationTest;
import com.bluementors.data.TrainingData;
import com.bluementors.training.Skill;
import com.bluementors.training.SkillService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SkillServiceIT extends IntegrationTest {

    @Autowired
    private SkillService skillService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save_skill_test() {
        Skill skill = TrainingData.theFirstSkill();

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

    @Test
    public void test_fetch_all_by_ids() {
        List<Skill> skills = Arrays.asList(TrainingData.theFirstSkill(),
                TrainingData.theSkillOfDay(),
                TrainingData.theSkillOfTheFuture(),
                TrainingData.theSkillOfTheYear());

        List<Skill> savedSkills = skills.stream().map(skillService::save).collect(Collectors.toList());
        entityManager.flush();

        List<Skill> skillsById = skillService.fetchByIds(savedSkills.stream().map(Skill::getId).collect(Collectors.toList()));

        assertThat(skillsById)
                .isNotNull()
                .isNotEmpty()
                .containsAll(savedSkills);

    }


}
