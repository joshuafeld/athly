package com.joshuafeld.athly.nutrition.dto;

/**
 * A data transfer object for a food patch request.
 *
 * @param name the value for the {@code name} component
 * @param manufacturer the value for the {@code manufacturer} component
 * @param creator the value for the {@code creator} component
 */
public record FoodPatchDto(String name, String manufacturer, Long creator) { }
