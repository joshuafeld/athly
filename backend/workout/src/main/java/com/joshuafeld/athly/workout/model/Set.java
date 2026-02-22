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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A set entity.
 */
@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
}
