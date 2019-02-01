package com.bluementors.mentor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MentorRegistrationRequest {
    @JsonProperty
    public Long userId;
    @JsonProperty
    public List<Long> skills;
    @JsonProperty
    public Integer yearsOfExperience;
    @JsonProperty
    public String linkedInUrl;

}
