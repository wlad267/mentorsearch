package com.bluementors.data;

import com.bluementors.training.Calendar;
import com.bluementors.training.Skill;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TrainingData {

    public static List<Calendar> trainingCalendar() {
        Calendar calendar1 = new Calendar();
        calendar1.setStartTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));
        calendar1.setEndTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));
        Calendar calendar2 = new Calendar();
        calendar2.setStartTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));
        calendar2.setEndTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));

        return Arrays.asList(calendar1, calendar2);
    }

    public static Calendar summerCampCoding() {
        Calendar calendar = new Calendar();
        calendar.setStartTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));
        calendar.setEndTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));

        return calendar;
    }

    public static Calendar midYearTrainingSection() {
        Calendar calendar = new Calendar();
        calendar.setStartTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));
        calendar.setEndTime(LocalDateTime.parse("2014-04-28T16:00:49.455"));

        return calendar;
    }



    public static List<Skill> trainingSkills() {
        return Arrays.asList(theFirstSkill(), theSkillOfDay(), theSkillOfTheYear(), theSkillOfTheFuture());
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