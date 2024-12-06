package edu.ntnu.idi.idatt.enums;

/**
 * Represents various diet categories that can be associated with recipes or meal plans.
 * Using enums ensures scalability and prevents invalid values compared to plain strings.
 */
public enum DietCategory {

    /**
     * No specific diet category.
     */
    NONE("Ingen kategori"),

    /**
     * Vegetarian diet, excludes meat but may include dairy and eggs.
     */
    VEGETARIAN("Vegetar"),

    /**
     * Vegan diet, excludes all animal products.
     */
    VEGAN("Vegansk"),

    /**
     * Pescetarian diet, includes fish but excludes other types of meat.
     */
    PESCETARIAN("Pescetarian (inneholder fisk)"),

    /**
     * Diets that include meat.
     */
    MEAT("Inneholder kjøtt"),

    /**
     * Other types of diets that do not fit into predefined categories.
     */
    OTHER("Annet");

    /**
     * A descriptive string for the diet category.
     * This provides a user-friendly representation of the enum value.
     */
    private final String description;

    /**
     * Constructor for initializing the diet category with its description.
     *
     * @param description a user-friendly description of the diet category
     */
    DietCategory(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of the diet category.
     * This can be used to display a readable text instead of the enum name.
     *
     * @return the description of the diet category
     */
    public String getDescription() {
        return description;
    }
}
