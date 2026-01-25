package com.joshuafeld.athly.workout.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

/**
 * A workout entity.
 */
@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long creator;

    @OneToMany(
            mappedBy = "workout",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Segment> segments;

    /**
     * Creates an instance of a {@code Workout} class.
     */
    public Workout() { }

    /**
     * Creates an instance of a {@code Workout} class.
     *
     * @param creator the value for the {@code creator} component
     * @param segments the value for the {@code segments} component
     */
    public Workout(final Long creator, final List<Segment> segments) {
        this.creator = creator;
        this.segments = segments;
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

    /**
     * Returns the value of the {@code segments} component.
     *
     * @return the value of the {@code segments} component
     */
    public List<Segment> segments() {
        return segments;
    }

    /**
     * Sets the value of the {@code segments} component.
     *
     * @param segments the value for the {@code segments} component
     */
    public void segments(final List<Segment> segments) {
        this.segments = segments;
    }
}
