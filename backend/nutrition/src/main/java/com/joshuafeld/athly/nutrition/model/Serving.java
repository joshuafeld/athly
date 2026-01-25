package com.joshuafeld.athly.nutrition.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * A serving entity.
 */
@Entity
@Table(name = "servings")
public class Serving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food")
    private Food food;

    private ServingType type;

    private Double value;

    private Unit unit;

    /**
     * Creates an instance of a {@code Serving} class.
     */
    public Serving() {
    }

    /**
     * Creates an instance of a {@code Serving} class.
     *
     * @param food the value for the {@code food} component
     * @param type the value for the {@code type} component
     * @param value the value for the {@code value} component
     * @param unit the value for the {@code unit} component
     */
    public Serving(final Food food,
                final ServingType type,
                final Double value,
                final Unit unit) {
        this.food = food;
        this.type = type;
        this.value = value;
        this.unit = unit;
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
    public ServingType type() {
        return type;
    }

    /**
     * Sets the value of the {@code type} component.
     *
     * @param type the value for the {@code type} component
     */
    public void type(final ServingType type) {
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

    /**
     * Returns the value of the {@code unit} component.
     *
     * @return the value of the {@code unit} component
     */
    public Unit unit() {
        return unit;
    }

    /**
     * Sets the value of the {@code unit} component.
     *
     * @param unit the value for the {@code unit} component
     */
    public void unit(final Unit unit) {
        this.unit = unit;
    }
}
