package com.bluementors.training;

import com.bluementors.training.specifications.TrainingSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindTraining {

    @Autowired
    private TrainingRepository trainingRepository;

    public List<Training> findPlannedTrainigs(Long userId) {
        return trainingRepository.findAll(new TrainingSpecifications().trainingsForUser(userId));
    }

    public List<Training> findTrainigs(Long trainingId) {
        return trainingRepository.findAll(new TrainingSpecifications().trainingsForUser(trainingId));
    }

}
