package com.joshuafeld.athly.nutrition.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.nutrition.dto.ServingDto;
import com.joshuafeld.athly.nutrition.model.Serving;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for servings.
 */
@Component
public class ServingMapper implements Mapper<Serving, ServingDto> {

    /**
     * Converts a serving entity to a data transfer object.
     *
     * @param serving the serving entity
     * @return the data transfer object
     */
    @Override
    public ServingDto toDto(final Serving serving) {
        return new ServingDto(
                serving.id(),
                serving.food().id(),
                serving.type(),
                serving.value(),
                serving.unit()
        );
    }
}
