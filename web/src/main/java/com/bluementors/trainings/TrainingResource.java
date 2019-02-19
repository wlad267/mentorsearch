package com.bluementors.trainings;

import com.bluementors.training.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trainings")
public class TrainingResource {

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private ExposedTrainingMapper exposedTrainingMapper;

    @GetMapping("search/skill/{skillId}")
    public List<ExposedTrainig> fetchAvilabaleTrainings(@PathVariable("skillId") Long skillId) {
        return exposedTrainingMapper.convert(trainingService.findTrainingsForSkill(skillId));
    }

    @PostMapping("book/{skillId}/{userId}/{calendarId}")
    public ExposedTrainig bookTrainig(@PathVariable("skillId") Long skillId,
                                      @PathVariable("userId") Long userId,
                                      @PathVariable("calendarId") Long calendarId) {
        return exposedTrainingMapper.convert(trainingService.bookTraining(userId, skillId, calendarId));
    }
}
