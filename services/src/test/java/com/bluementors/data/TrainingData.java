package com.bluementors.data;

import com.bluementors.training.Skill;

import java.util.Arrays;
import java.util.List;

public class TrainingData {

    public static List<Skill> trainingSkills() {
        return Arrays.asList(new Skill("java", "most used OOP language"),
                new Skill("clouding", "the future of the current fog environment"));
    }

    public static Skill theFirstSkill() {
        Skill skill = new Skill();
        skill.setName("java");
        skill.setDescription("a must have skill of 201x years");
        return skill;
    }

    public static Skill theSkillOfDay() {
        Skill skill = new Skill();
        skill.setName("angular2-x");
        skill.setDescription("complete angular jurney - from novice to pro");
        return skill;
    }

    public static Skill theSkillOfTheYear() {
        Skill skill = new Skill();
        skill.setName("cloud");
        skill.setDescription("clouding is the here - make no mistake and learn clouding");
        return skill;
    }

    public static Skill theSkillOfTheFuture() {
        Skill skill = new Skill();
        skill.setName("nodeJS");
        skill.setDescription("the future might be javascript everywhere - invest in future");
        return skill;
    }

}