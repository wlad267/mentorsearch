package com.bluementors.trainings;

import com.bluementors.training.Training;
import com.bluementors.training.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingResource {

    @Autowired
    private TrainingService trainingService;

    @GetMapping("/api/{skillId}")
    public List<Training> fetchAvilabaleTrainings(@PathVariable("skillId") Long skillId) {
        return trainingService.findTrainigsForSkill(skillId);
    }
}
