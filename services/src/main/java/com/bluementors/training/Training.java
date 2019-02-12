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
    private Payment payment;

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
