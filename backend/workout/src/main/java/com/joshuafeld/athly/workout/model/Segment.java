package com.joshuafeld.athly.workout.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

/**
 * A segment entity.
 */
@Entity
@Table(name = "segments")
public class Segment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout")
    private Workout workout;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercise")
    private Exercise exercise;

    @OneToMany(
            mappedBy = "segment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Set> sets;

    private Integer rest;

    /**
     * Creates an instance of a {@code Segment} class.
     */
    public Segment() { }

    /**
     * Creates an instance of a {@code Segment} class.
     *
     * @param workout the value for the {@code workout} component
     * @param exercise the value for the {@code exercise} component
     * @param sets the value for the {@code sets} component
     * @param rest the value for the {@code rest} component
     */
    public Segment(final Workout workout,
                   final Exercise exercise,
                   final List<Set> sets,
                   final Integer rest) {
        this.workout = workout;
        this.exercise = exercise;
        this.sets = sets;
        this.rest = rest;
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
     * Returns the value of the {@code workout} component.
     *
     * @return the value of the {@code workout} component
     */
    public Workout workout() {
        return workout;
    }

    /**
     * Sets the value of the {@code workout} component.
     *
     * @param workout the value for the {@code workout} component
     */
    public void workout(final Workout workout) {
        this.workout = workout;
    }

    /**
     * Returns the value of the {@code exercise} component.
     *
     * @return the value of the {@code exercise} component
     */
    public Exercise exercise() {
        return exercise;
    }

    /**
     * Sets the value of the {@code exercise} component.
     *
     * @param exercise the value for the {@code exercise} component
     */
    public void exercise(final Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * Returns the value of the {@code sets} component.
     *
     * @return the value of the {@code sets} component
     */
    public List<Set> sets() {
        return sets;
    }

    /**
     * Sets the value of the {@code sets} component.
     *
     * @param sets the value for the {@code sets} component
     */
    public void sets(final List<Set> sets) {
        this.sets = sets;
    }

    /**
     * Returns the value of the {@code rest} component.
     *
     * @return the value of the {@code rest} component
     */
    public Integer rest() {
        return rest;
    }

    /**
     * Sets the value of the {@code rest} component.
     *
     * @param rest the value for the {@code rest} component
     */
    public void rest(final Integer rest) {
        this.rest = rest;
    }
}
