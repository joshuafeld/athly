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
 * A nutrient entity.
 */
@Entity
@Table(name = "nutrients")
@Getter
@Setter
@NoArgsConstructor
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food")
    private Food food;

    private NutrientType type;

    private Double value;

    /**
     * Creates an instance of a {@code Nutrient} class.
     *
     * @param food the value for the {@code food} component
     * @param type the value for the {@code type} component
     * @param value the value for the {@code value} component
     */
    public Nutrient(final Food food,
                    final NutrientType type,
                    final Double value) {
        this.food = food;
        this.type = type;
        this.value = value;
    }
}
