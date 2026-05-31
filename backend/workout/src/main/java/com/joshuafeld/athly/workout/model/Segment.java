package com.joshuafeld.athly.workout.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A segment entity.
 */
@Entity
@Table(name = "segments")
@Getter
@Setter
@NoArgsConstructor
public class Segment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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

    private String notes;

    /**
     * Creates an instance of a {@code Segment} class.
     *
     * @param workout the value for the {@code workout} component
     * @param exercise the value for the {@code exercise} component
     * @param sets the value for the {@code sets} component
     * @param rest the value for the {@code rest} component
     * @param notes the value for the {@code notes} component
     */
    public Segment(
            final Workout workout,
            final Exercise exercise,
            final List<Set> sets,
            final Integer rest,
            final String notes
    ) {
        this.workout = workout;
        this.exercise = exercise;
        this.sets = sets;
        this.rest = rest;
        this.notes = notes;
    }
}
