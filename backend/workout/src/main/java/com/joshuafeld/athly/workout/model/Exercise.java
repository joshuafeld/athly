package com.joshuafeld.athly.workout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An exercise entity.
 */
@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Muscle muscle;

    private Long owner;

    /**
     * Creates an instance of an {@code Exercise} class.
     *
     * @param name the value for the {@code name} component
     * @param equipment the value for the {@code equipment} component
     * @param muscle the value for the {@code muscle} component
     * @param owner the value for the {@code owner} component
     */
    public Exercise(
            final String name,
            final Equipment equipment,
            final Muscle muscle,
            final Long owner
    ) {
        this.name = name;
        this.equipment = equipment;
        this.muscle = muscle;
        this.owner = owner;
    }
}
