package com.joshuafeld.athly.workout.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.workout.dto.SetDto;
import com.joshuafeld.athly.workout.model.Set;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for sets.
 */
@Component
public class SetMapper implements Mapper<Set, SetDto> {

    /**
     * Converts a set entity to a data transfer object.
     *
     * @param set the set entity
     * @return the data transfer object
     */
    @Override
    public SetDto toDto(final Set set) {
        return new SetDto(
                set.id(),
                set.segment().id(),
                set.type(),
                set.weight(),
                set.reps(),
                set.done()
        );
    }
}
