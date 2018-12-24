package com.bluementors.mentor;

import com.bluementors.User;
import com.bluementors.training.Skill;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENTORS")
public class Mentor extends User implements Serializable {

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Skill> skills = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime registrationDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    private String linkedUrl;
    @NotNull
    private Integer yearsOfExperince;
    private Boolean active = Boolean.TRUE;

    public Mentor(){}

    public List<Skill> getSkills() {
        return skills;
    }

    @Override
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getLinkedUrl() {
        return linkedUrl;
    }

    public Integer getYearsOfExperince() {
        return yearsOfExperince;
    }

    @Override
    public Boolean getActive() {
        return active;
    }

    public static class Builder {
        private Mentor mentor;
        public Builder(){
            this.mentor = new Mentor();
        }

        public Builder skill(Skill skill){
            this.mentor.skills.add(skill);
            return this;
        }

        public Builder skills(List<Skill> skills){
            this.mentor.skills.addAll(skills);
            return this;
        }

        public Builder yearsOfExperience(int yearsOfExperiance){
            this.mentor.yearsOfExperince = yearsOfExperiance;
            return this;
        }

        public Builder active(Boolean active){
            this.mentor.active= active;
            return this;
        }

        public Builder user(User user){
            BeanUtils.copyProperties(user, this.mentor);
            return this;
        }

        public Mentor build(){
            return this.mentor;
        }

    }
}
