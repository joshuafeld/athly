package com.joshuafeld.athly.workout.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.workout.dto.ExerciseDto;
import com.joshuafeld.athly.workout.model.Exercise;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for exercises.
 */
@Component
public class ExerciseMapper implements Mapper<Exercise, ExerciseDto> {

    /**
     * Converts an exercise entity to a data transfer object.
     *
     * @param exercise the exercise entity
     * @return the data transfer object
     */
    @Override
    public ExerciseDto toDto(final Exercise exercise) {
        return new ExerciseDto(
                exercise.id(),
                exercise.name(),
                exercise.equipment(),
                exercise.muscle(),
                exercise.creator()
        );
    }
}
