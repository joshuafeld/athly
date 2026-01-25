package com.joshuafeld.athly.nutrition.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * A nutrient entity.
 */
@Entity
@Table(name = "nutrients")
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food")
    private Food food;

    private NutrientType type;

    private Double value;

    /**
     * Creates an instance of a {@code Nutrient} class.
     */
    public Nutrient() {
    }

    /**
     * Creates an instance of a {@code Nutrient} class.
     *
     * @param food the value for the {@code food} component
     * @param type the value for the {@code type} component
     * @param value the value for the {@code value} component
     */
    public Nutrient(final Food food,
                    final NutrientType type,
                    final Double value) {
        this.food = food;
        this.type = type;
        this.value = value;
    }

    /**
     * Returns the value of the {@code id} component.
     *
     * @return the value of the {@code id} component
     */
    public Long id() {
        return id;
    }

    /**
     * Returns the value of the {@code food} component.
     *
     * @return the value of the {@code food} component
     */
    public Food food() {
        return food;
    }

    /**
     * Sets the value of the {@code food} component.
     *
     * @param food the value for the {@code food} component
     */
    public void food(final Food food) {
        this.food = food;
    }

    /**
     * Returns the value of the {@code type} component.
     *
     * @return the value of the {@code type} component
     */
    public NutrientType type() {
        return type;
    }

    /**
     * Sets the value of the {@code type} component.
     *
     * @param type the value for the {@code type} component
     */
    public void type(final NutrientType type) {
        this.type = type;
    }

    /**
     * Returns the value of the {@code value} component.
     *
     * @return the value of the {@code value} component
     */
    public Double value() {
        return value;
    }

    /**
     * Sets the value of the {@code value} component.
     *
     * @param value the value for the {@code value} component
     */
    public void value(final Double value) {
        this.value = value;
    }
}
