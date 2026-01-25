package com.joshuafeld.athly.nutrition.model;

/**
 * An enum for all nutrients.
 */
public enum NutrientType {

    ENERGY(Unit.KCAL, true),
    FAT(Unit.GRAM, true),
    SATURATED(Unit.GRAM, true),
    MONOUNSATURATED(Unit.GRAM, false),
    POLYUNSATURATED(Unit.GRAM, false),
    CARBS(Unit.GRAM, true),
    SUGAR(Unit.GRAM, true),
    FIBER(Unit.GRAM, false),
    PROTEIN(Unit.GRAM, true),
    SALT(Unit.GRAM, true),
    VITAMIN_C(Unit.MILLIGRAM, false),
    VITAMIN_D(Unit.MICROGRAM, false),
    VITAMIN_A(Unit.MICROGRAM, false),
    VITAMIN_B1(Unit.MILLIGRAM, false),
    VITAMIN_B2(Unit.MILLIGRAM, false),
    VITAMIN_B3(Unit.MILLIGRAM, false),
    VITAMIN_B5(Unit.MILLIGRAM, false),
    VITAMIN_B6(Unit.MILLIGRAM, false),
    VITAMIN_B7(Unit.MICROGRAM, false),
    VITAMIN_B11(Unit.MICROGRAM, false),
    VITAMIN_B12(Unit.MICROGRAM, false),
    VITAMIN_E(Unit.MILLIGRAM, false),
    VITAMIN_K(Unit.MICROGRAM, false),
    CALCIUM(Unit.MILLIGRAM, false),
    IRON(Unit.MILLIGRAM, false),
    ARSENIC(Unit.MICROGRAM, false),
    BORON(Unit.MILLIGRAM, false),
    BIOTIN(Unit.MICROGRAM, false),
    CHOLINE(Unit.MILLIGRAM, false),
    CHLORIDE(Unit.MILLIGRAM, false),
    CHROMIUM(Unit.MILLIGRAM, false),
    COBALT(Unit.MICROGRAM, false),
    COPPER(Unit.MILLIGRAM, false),
    FLUORIDE(Unit.MILLIGRAM, false),
    IODINE(Unit.MICROGRAM, false),
    MAGNESIUM(Unit.MILLIGRAM, false),
    MANGANESE(Unit.MILLIGRAM, false),
    MOLYBDENUM(Unit.MICROGRAM, false),
    PHOSPHORUS(Unit.MILLIGRAM, false),
    POTASSIUM(Unit.MILLIGRAM, false),
    RUBIDIUM(Unit.MICROGRAM, false),
    SELENIUM(Unit.MICROGRAM, false),
    SILICON(Unit.MILLIGRAM, false),
    SULFUR(Unit.MILLIGRAM, false),
    TIN(Unit.MILLIGRAM, false),
    VANADIUM(Unit.MICROGRAM, false),
    ZINC(Unit.MILLIGRAM, false),
    WATER(Unit.MILLILITER, false),
    ALCOHOL(Unit.MILLILITER, false);

    private final Unit unit;

    private final boolean required;

    NutrientType(Unit unit, boolean required) {
        this.unit = unit;
        this.required = required;
    }

    public Unit unit() {
        return unit;
    }

    public boolean required() {
        return required;
    }
}
