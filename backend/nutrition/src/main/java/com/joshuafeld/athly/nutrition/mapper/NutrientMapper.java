package com.joshuafeld.athly.nutrition.mapper;

import com.joshuafeld.athly.common.mapper.Mapper;
import com.joshuafeld.athly.nutrition.dto.NutrientDto;
import com.joshuafeld.athly.nutrition.model.Nutrient;
import org.springframework.stereotype.Component;

/**
 * A data transfer object mapper for nutrients.
 */
@Component
public class NutrientMapper implements Mapper<Nutrient, NutrientDto> {

    /**
     * Converts a nutrient entity to a data transfer object.
     *
     * @param nutrient the nutrient entity
     * @return the data transfer object
     */
    @Override
    public NutrientDto toDto(final Nutrient nutrient) {
        return new NutrientDto(
                nutrient.id(),
                nutrient.food().id(),
                nutrient.type(),
                nutrient.value()
        );
    }
}
