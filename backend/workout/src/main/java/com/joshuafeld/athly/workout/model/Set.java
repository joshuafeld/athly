package com.joshuafeld.athly.workout.model;

import com.joshuafeld.athly.workout.model.SetType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * A set entity.
 */
@Entity
@Table(name = "sets")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "segment")
    private Segment segment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SetType type;

    private Double weight;

    private Integer reps;

    private Boolean done;

    /**
     * Creates an instance of a {@code Set} class.
     */
    public Set() { }

    /**
     * Creates an instance of a {@code Set} class.
     *
     * @param segment the value for the {@code segment} component
     * @param type the value for the {@code type} component
     * @param weight the value for the {@code weight} component
     * @param reps the value for the {@code reps} component
     * @param done the value for the {@code done} component
     */
    public Set(final Segment segment,
               final SetType type,
               final Double weight,
               final Integer reps,
               final Boolean done) {
        this.segment = segment;
        this.type = type;
        this.weight = weight;
        this.reps = reps;
        this.done = done;
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
     * Returns the value of the {@code segment} component.
     *
     * @return the value of the {@code segment} component
     */
    public Segment segment() {
        return segment;
    }

    /**
     * Sets the value of the {@code segment} component.
     *
     * @param segment the value for the {@code segment} component
     */
    public void segment(final Segment segment) {
        this.segment = segment;
    }

    /**
     * Returns the value of the {@code type} component.
     *
     * @return the value of the {@code type} component
     */
    public SetType type() {
        return type;
    }

    /**
     * Sets the value of the {@code type} component.
     *
     * @param type the value for the {@code type} component
     */
    public void type(final SetType type) {
        this.type = type;
    }

    /**
     * Returns the value of the {@code weight} component.
     *
     * @return the value of the {@code weight} component
     */
    public Double weight() {
        return weight;
    }

    /**
     * Sets the value of the {@code weight} component.
     *
     * @param weight the value for the {@code weight} component
     */
    public void weight(final Double weight) {
        this.weight = weight;
    }

    /**
     * Returns the value of the {@code reps} component.
     *
     * @return the value of the {@code reps} component
     */
    public Integer reps() {
        return reps;
    }

    /**
     * Sets the value of the {@code reps} component.
     *
     * @param reps the value for the {@code reps} component
     */
    public void reps(final Integer reps) {
        this.reps = reps;
    }

    /**
     * Returns the value of the {@code done} component.
     *
     * @return the value of the {@code done} component
     */
    public Boolean done() {
        return done;
    }

    /**
     * Sets the value of the {@code segment} component.
     *
     * @param done the value for the {@code done} component
     */
    public void done(final Boolean done) {
        this.done = done;
    }
}
