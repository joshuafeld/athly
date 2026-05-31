package com.joshuafeld.athly.workout.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A workout entity.
 */
@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Long owner;

    @OneToMany(
            mappedBy = "workout",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Segment> segments;

    private String name;

    private String notes;

    /**
     * Creates an instance of a {@code Workout} class.
     *
     * @param owner the value for the {@code owner} component
     * @param segments the value for the {@code segments} component
     * @param name the value for the {@code name} component
     * @param notes the value for the {@code notes} component
     */
    public Workout(
            final Long owner,
            final List<Segment> segments,
            final String name,
            final String notes
    ) {
        this.owner = owner;
        this.segments = segments;
        this.name = name;
        this.notes = notes;
    }
}
