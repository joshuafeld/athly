package com.joshuafeld.athly.nutrition.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

/**
 * A food entity.
 */
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Nutrient> nutrients;

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Serving> servings;

    private Long creator;

    /**
     * Creates an instance of a {@code Food} class.
     */
    public Food() {
    }

    /**
     * Creates an instance of a {@code Food} class.
     *
     * @param name the value for the {@code name} component
     * @param manufacturer the value for the {@code manufacturer} component
     * @param nutrients the value for the {@code nutrients} component
     * @param servings the value for the {@code servings} component
     * @param creator the value for the {@code creator} component
     */
    public Food(final String name,
                final String manufacturer,
                final List<Nutrient> nutrients,
                final List<Serving> servings,
                final Long creator) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.nutrients = nutrients;
        this.servings = servings;
        this.creator = creator;
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
     * Returns the value of the {@code name} component.
     *
     * @return the value of the {@code name} component
     */
    public String name() {
        return name;
    }

    /**
     * Sets the value of the {@code name} component.
     *
     * @param name the value for the {@code name} component
     */
    public void name(final String name) {
        this.name = name;
    }

    /**
     * Returns the value of the {@code manufacturer} component.
     *
     * @return the value of the {@code manufacturer} component
     */
    public String manufacturer() {
        return name;
    }

    /**
     * Sets the value of the {@code manufacturer} component.
     *
     * @param manufacturer the value for the {@code manufacturer} component
     */
    public void manufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }


    /**
     * Returns the value of the {@code nutrients} component.
     *
     * @return the value of the {@code nutrients} component
     */
    public List<Nutrient> nutrients() {
        return nutrients;
    }

    /**
     * Sets the value of the {@code nutrients} component.
     *
     * @param nutrients the value for the {@code nutrients} component
     */
    public void nutrients(final List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    /**
     * Returns the value of the {@code servings} component.
     *
     * @return the value of the {@code servings} component
     */
    public List<Serving> servings() {
        return servings;
    }

    /**
     * Sets the value of the {@code servings} component.
     *
     * @param servings the value for the {@code servings} component
     */
    public void servings(final List<Serving> servings) {
        this.servings = servings;
    }

    /**
     * Returns the value of the {@code creator} component.
     *
     * @return the value of the {@code creator} component
     */
    public Long creator() {
        return creator;
    }

    /**
     * Sets the value of the {@code creator} component.
     *
     * @param creator the value for the {@code creator} component
     */
    public void creator(final Long creator) {
        this.creator = creator;
    }
}
