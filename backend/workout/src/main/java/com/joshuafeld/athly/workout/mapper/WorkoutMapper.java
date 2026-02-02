package com.joshuafeld.athly.workout.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.workout.dto.WorkoutDto;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Workout;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for workouts.
 */
@Component
public class WorkoutMapper implements Mapper<Workout, WorkoutDto> {

    /**
     * Converts a workout entity to a data transfer object.
     *
     * @param workout the workout entity
     * @return the data transfer object
     */
    @Override
    public WorkoutDto toDto(final Workout workout) {
        return new WorkoutDto(
                workout.id(),
                workout.creator(),
                workout.segments().stream().map(Segment::id).toList()
        );
    }
}
