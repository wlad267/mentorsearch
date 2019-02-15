package com.bluementors.training;

import com.bluementors.mentor.Mentor;
import com.bluementors.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRAININGS")
public class Training {
    @Id
    private Long id;

    @NotNull
    @OneToOne
    private User user;
    @NotNull
    @OneToOne
    private Mentor mentor;
    @NotNull
    @OneToOne
    private Skill skill;
    @NotNull
    @OneToOne
    private Calendar calendar;

    @Enumerated(EnumType.STRING)
    private TrainingStatus status;

    private Integer traineeRate;

    @OneToOne
    private Payment donation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public TrainingStatus getStatus() {
        return status;
    }

    public void setStatus(TrainingStatus status) {
        this.status = status;
    }

    public Integer getTraineeRate() {
        return traineeRate;
    }

    public void setTraineeRate(Integer traineeRate) {
        this.traineeRate = traineeRate;
    }

    public Payment getDonation() {
        return donation;
    }

    public void setDonation(Payment donation) {
        this.donation = donation;
    }

    public static class Builder {
        private Training training = new Training();

        public Builder skill(Skill skill) {
            this.training.skill = skill;
            return this;
        }

        public Builder mentor(Mentor mentor) {
            this.training.mentor = mentor;
            return this;
        }

        public Builder calendar(Calendar calendar) {
            this.training.calendar = calendar;
            return this;
        }

        public Training build() {
            return this.training;
        }
    }
}
