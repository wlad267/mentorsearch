package com.bluementors.training;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Calendar entity will just hold a time frame.
 * There can me multiple calendars on the same time frame but it should be belonging only by one mentor.
 * These entries will be defined by mentors as their availability for mentoring and will be refereed
 * by maximum une training.
 */
@Entity
@SequenceGenerator(name = "calendar_seq", initialValue = 10, allocationSize = 1000000)
public class Calendar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendar_seq")
    private Long id;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
