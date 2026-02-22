package com.joshuafeld.athly.nutrition.model;

import jakarta.persistence.Entity;
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
 * A serving entity.
 */
@Entity
@Table(name = "servings")
@Getter
@Setter
@NoArgsConstructor
public class Serving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food")
    private Food food;

    private ServingType type;

    private Double value;

    private Unit unit;

    /**
     * Creates an instance of a {@code Serving} class.
     *
     * @param food the value for the {@code food} component
     * @param type the value for the {@code type} component
     * @param value the value for the {@code value} component
     * @param unit the value for the {@code unit} component
     */
    public Serving(final Food food,
                   final ServingType type,
                   final Double value,
                   final Unit unit) {
        this.food = food;
        this.type = type;
        this.value = value;
        this.unit = unit;
    }
}
