package com.bluementors.mentor;

import com.bluementors.training.Skill;
import com.bluementors.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENTORS")
@SequenceGenerator(name = "mentor_seq", initialValue = 10, allocationSize = 1000000)
public class Mentor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_seq")
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Skill> skills = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime registrationDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    private String linkedInUrl;
    @NotNull
    private Integer yearsOfExperience;
    private Boolean active = Boolean.TRUE;

    @OneToOne
    private User user;

    public Mentor(){}

    public List<Skill> getSkills() {
        return skills;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }


    public Boolean getActive() {
        return active;
    }

    public User getUser() {
        return user;
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

        public Builder yearsOfExperience(int yearsOfExperiance) {
            this.mentor.yearsOfExperience = yearsOfExperiance;
            return this;
        }

        public Builder active(Boolean active){
            this.mentor.active= active;
            return this;
        }

        public Builder user(User user){
            //BeanUtils.copyProperties(user, this.mentor);
            //user.setMentor(this.mentor);
            this.mentor.user = user;
            user.setMentor(this.mentor);
            return this;
        }

        public Builder linkedInUrl(String linkedInUrl) {
            this.mentor.linkedInUrl = linkedInUrl;
            return this;
        }

        public Mentor build(){
            return this.mentor;
        }

    }
}
