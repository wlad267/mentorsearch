package com.bluementors.mentor;

import com.bluementors.training.Calendar;
import com.bluementors.training.Skill;
import com.bluementors.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MENTORS")
@SequenceGenerator(name = "mentor_seq", initialValue = 10, allocationSize = 1000000)
public class Mentor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mentor_seq")
    private Long id;

    @ManyToMany(cascade = {CascadeType.REFRESH})
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Calendar> calendar = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime registrationDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    private String linkedInUrl;
    @NotNull
    private Integer yearsOfExperience;
    private Boolean active = Boolean.TRUE;

    @OneToOne
    @JsonIgnore
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

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Calendar> getCalendar() {
        return calendar;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = new ArrayList<>();
        this.skills.addAll(skills);
    }

    public void setCalendar(@NotNull @Size(min = 1) List<Calendar> calendar) {
        this.calendar = new ArrayList<>();
        this.calendar.addAll(calendar);
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return Objects.equals(id, mentor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
