package com.joshuafeld.athly.nutrition.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

import java.util.Set;

/**
 * A food entity.
 */
@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
    private Set<Nutrient> nutrients;

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Serving> servings;

    private Long creator;

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
                final Set<Nutrient> nutrients,
                final Set<Serving> servings,
                final Long creator) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.nutrients = nutrients;
        this.servings = servings;
        this.creator = creator;
    }
}
