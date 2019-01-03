package com.bluementors.training;

import com.bluementors.user.User;
import com.bluementors.mentor.Mentor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @Enumerated(EnumType.STRING)
    private TrainingStatus status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer traineeRate;

    @OneToOne
    private Payment payment;

}
