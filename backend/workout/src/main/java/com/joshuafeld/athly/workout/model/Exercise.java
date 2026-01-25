package com.joshuafeld.athly.workout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * An exercise entity.
 */
@Entity
@Table(name = "exercises")
public final class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Muscle muscle;

    private Long creator;

    /**
     * Creates an instance of an {@code Exercise} class.
     */
    public Exercise() {
    }

    /**
     * Creates an instance of an {@code Exercise} class.
     *
     * @param name the value for the {@code name} component
     * @param equipment the value for the {@code equipment} component
     * @param muscle the value for the {@code muscle} component
     * @param creator the value for the {@code creator} component
     */
    public Exercise(final String name,
                    final Equipment equipment,
                    final Muscle muscle,
                    final Long creator) {
        this.name = name;
        this.equipment = equipment;
        this.muscle = muscle;
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
     * Returns the value of the {@code equipment} component.
     *
     * @return the value of the {@code equipment} component
     */
    public Equipment equipment() {
        return equipment;
    }

    /**
     * Sets the value of the {@code equipment} component.
     *
     * @param equipment the value for the {@code equipment} component
     */
    public void equipment(final Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * Returns the value of the {@code muscle} component.
     *
     * @return the value of the {@code muscle} component
     */
    public Muscle muscle() {
        return muscle;
    }

    /**
     * Sets the value of the {@code muscle} component.
     *
     * @param muscle the value for the {@code muscle} component
     */
    public void muscle(final Muscle muscle) {
        this.muscle = muscle;
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
