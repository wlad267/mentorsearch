package com.bluementors.trainings;

import com.bluementors.training.Calendar;
import com.bluementors.training.TrainingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Facade layer DTO.
 */
public class ExposedTrainig {
    @JsonProperty
    public Calendar calendar;

    @JsonProperty
    public String mentorName;

    @JsonProperty
    public String skillName;

    @JsonProperty
    public TrainingStatus status;

}
