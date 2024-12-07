package edu.ntnu.idi.idatt.utils;

import java.util.List;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;

/**
 * Represents search/filter criteria for filtering recipes in a Cookbook.
 * This class allows users to specify various criteria for searching recipes based on diet category,
 *  difficulty level, maximum preparation time, and desired ingredients.
 * It is used as a helper object passed into the `filterRecipes`
 *  method to return a list of recipes matching the criteria.
 * 
 * Design choices:
 * - "DietCategory" and "Difficulty" are enums 
 * this to restrict possible values and make the code more robust.
 * - "Integer" is used instead of "int" for "maxPreparationTime" because it allows a "null" value,
 *  indicating "no criteria specified" (i.e., the user has not selected a maximum time).
 *   Without Integer, it would not be possible to differentiate between 0 and "null"
 *  (as a value type).
 * - "List<String>" is used for ingredients to provide flexibility
 * ^supports multiple ingredients in the search.
 * 
 * Example usage:
 * 
 * FilterCriteria criteria = new FilterCriteria();
 * criteria.setDietCategory(DietCategory.VEGAN);
 * criteria.setMaxPreparationTime(30);
 * criteria.setIngredients(Arrays.asList("tomato", "basil"));
 * 
 * List<Recipe> filteredRecipes = cookbook.filterRecipes(criteria);
 * 
 * 
 * Getters and setters are included for easy access to the fields.
 */

public class FilterCriteria {
    private DietCategory dietCategory;  // Diet category, enum
    private Difficulty difficulty;      // Difficulty level, enum
    private Integer maxPreparationTime; // Maximum preparation time in minutes
    private List<String> ingredients;   // List of required ingredients
    private String category;            // Recipe category (e.g., main course, dessert)
    private String cuisine;             // Recipe cuisine (e.g., Indian, Norwegian)

    /**
     * Gets the diet category selected for the search criteria.
     * @return DietCategory selected category, or null if none is set.
     */
    public DietCategory getDietCategory() {
        return dietCategory;
    }

    /**
     * Sets the diet category for the search.
     * @param dietCategory DietCategory representing the desired category.
     */
    public void setDietCategory(DietCategory dietCategory) {
        this.dietCategory = dietCategory;
    }

    /**
     * Gets the difficulty level selected for the search criteria.
     * @return Difficulty selected difficulty level, or null if none is set.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level for the search.
     * @param difficulty Difficulty representing the desired difficulty level.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the maximum preparation time in minutes for the search criteria.
     * @return Integer maximum time, or null if none is set.
     */
    public Integer getMaxPreparationTime() {
        return maxPreparationTime;
    }

    /**
     * Sets the maximum preparation time in minutes for the search.
     * @param maxPreparationTime Integer representing the maximum preparation time.
     */
    public void setMaxPreparationTime(Integer maxPreparationTime) {
        this.maxPreparationTime = maxPreparationTime;
    }

    /**
     * Gets the list of ingredients selected for the search criteria.
     * @return List of ingredients, or null if none is set.
     */
    public List<String> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the list of ingredients for the search.
     * @param ingredients List of ingredients.
     */
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Gets the category selected for the search criteria.
     * @return String category, or null if none is set.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category for the search.
     * @param category String representing the category (e.g., appetizer, main course, dessert).
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the cuisine selected for the search criteria.
     * @return String cuisine, or null if none is set.
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * Sets the cuisine for the search.
     * @param cuisine String representing the cuisine (e.g., Indian, Norwegian, etc.).
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}