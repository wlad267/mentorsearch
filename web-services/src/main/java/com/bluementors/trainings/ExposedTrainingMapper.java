package com.bluementors.trainings;

import com.bluementors.training.Training;
import com.bluementors.training.TrainingStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExposedTrainingMapper {

    public List<ExposedTrainig> convert(List<Training> trainings) {
        return trainings.stream().map(this::convert).collect(Collectors.toList());
    }

    public ExposedTrainig convert(Training training) {
        ExposedTrainig exposedTrainig = new ExposedTrainig();
        exposedTrainig.calendar = training.getCalendar();
        exposedTrainig.mentorName = training.getMentor().getUser().getFirstName().concat(" ").concat(training.getMentor().getUser().getLastName());
        exposedTrainig.status = TrainingStatus.NO_STARTED;
        exposedTrainig.skillName = training.getSkill().getName();
        return exposedTrainig;
    }
}
