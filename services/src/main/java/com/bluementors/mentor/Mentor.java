package com.bluementors.mentor;

import com.bluementors.User;
import com.bluementors.training.Skill;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MENTORS")
public class Mentor implements Serializable {

    @Id
    private Long id;
    @NotNull
    @OneToOne
    private User user;
    @OneToMany
    private List<Skill> skills;
    private LocalDateTime registrationDate;
    private String linkedUrl;
    private Integer yearsOfExperince;
    private Boolean active;

}
