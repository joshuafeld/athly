package com.joshuafeld.athly.workout.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.workout.dto.SegmentDto;
import com.joshuafeld.athly.workout.model.Segment;
import com.joshuafeld.athly.workout.model.Set;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for segments.
 */
@Component
public class SegmentMapper implements Mapper<Segment, SegmentDto> {

    /**
     * Converts a segment entity to a data transfer object.
     *
     * @param segment the segment entity
     * @return the data transfer object
     */
    @Override
    public SegmentDto toDto(final Segment segment) {
        return new SegmentDto(
                segment.id(),
                segment.workout().id(),
                segment.exercise().id(),
                segment.sets().stream().map(Set::id).toList(),
                segment.rest()
        );
    }
}
