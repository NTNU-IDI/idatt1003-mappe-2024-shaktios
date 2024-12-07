package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.utils.FilterCriteria;

/**
 * Represents a cookbook that stores and manages recipes.
 */
public class Cookbook {
  private final List<Recipe> recipes;

  /**
   * Constructs a new Cookbook.
   */
  public Cookbook() {
    this.recipes = new ArrayList<>();
  }

  /**
   * Adds a recipe to the cookbook.
   *
   * @param recipe the recipe to add
   */
  public void addRecipe(Recipe recipe) {
    if (recipe != null && !recipes.contains(recipe)) {
      recipes.add(recipe);
      System.out.println("Oppskrift lagt til: " + recipe.getName());
    } else {
      System.out.println("Oppskriften finnes allerede eller er ugyldig.");
    }
  }

  /**
   * Removes a recipe from the cookbook.
   *
   * @param recipe the recipe to remove
   */
  public void removeRecipe(Recipe recipe) {
    if (recipes.remove(recipe)) {
      System.out.println("Oppskrift fjernet: " + recipe.getName());
    } else {
      System.out.println("Oppskriften ble ikke funnet.");
    }
  }

  /**
   * Gets all recipes in the cookbook.
   *
   * @return a list of all recipes
   */
  public List<Recipe> getAllRecipes() {
    return new ArrayList<>(recipes);
  }

  /**
   * Searches for recipes by name.
   *
   * @param name the name to search for
   * @return a list of recipes matching the name
   * @throws IllegalArgumentException if the name is null or empty
   */
  public List<Recipe> searchByName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Navnet kan ikke være tomt eller null.");
    }
    return recipes.stream()
        .filter(recipe -> recipe.getName().equalsIgnoreCase(name.trim()))
        .collect(Collectors.toList());
  }

  /**
   * Searches for recipes containing a specific ingredient.
   *
   * @param ingredientName the name of the ingredient
   * @return a list of recipes containing the ingredient
   * @throws IllegalArgumentException if the ingredient name is null or empty
   */
  public List<Recipe> searchByIngredient(String ingredientName) {
    if (ingredientName == null || ingredientName.trim().isEmpty()) {
      throw new IllegalArgumentException("Ingrediensnavn kan ikke være tomt.");
    }
    return recipes.stream()
        .filter(recipe -> recipe.getIngredients().stream()
            .anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase(ingredientName)))
        .collect(Collectors.toList());
  }

  /**
   * Suggests recipes based on available ingredients in the fridge.
   *
   * @param fridge the fridge containing available ingredients
   * @param desiredServings the number of servings desired
   * @return a list of suggested recipes sorted by availability of ingredients
   */
  public List<Recipe> suggestRecipes(Fridge fridge, int desiredServings) {
    Map<Recipe, Integer> recipeAvailabilityMap = new HashMap<>();
    for (Recipe recipe : recipes) {
      int availableIngredients = 0;
      double scalingFactor = (double) desiredServings / recipe.getServings();
      boolean canMakeRecipe = true;

      for (Grocery ingredient : recipe.getIngredients()) {
        Grocery fridgeItem = fridge.searchItem(ingredient.getName());
        double adjustedAmountNeeded = ingredient.getAmount() * scalingFactor;

        if (fridgeItem == null || fridgeItem.getAmount() < adjustedAmountNeeded) {
          canMakeRecipe = false;
          break;
        } else {
          availableIngredients++;
        }
      }

      if (canMakeRecipe) {
        recipeAvailabilityMap.put(recipe, availableIngredients);
      }
    }

    return recipeAvailabilityMap.entrySet().stream()
        .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  /**
   * Sorts recipes by preparation time in ascending order.
   *
   * @return a list of recipes sorted by preparation time
   */
  public List<Recipe> sortByPreperationTime() {
    return recipes.stream()
        .sorted(Comparator.comparingInt(Recipe::getPreperationTimeMinutes))
        .collect(Collectors.toList());
  }

  /**
   * Sorts recipes by difficulty in ascending order.
   *
   * @return a list of recipes sorted by difficulty
   */
  public List<Recipe> sortByDifficulty() {
    return recipes.stream()
        .sorted(Comparator.comparing(Recipe::getDifficulty))
        .collect(Collectors.toList());
  }

  /**
   * Filters recipes by diet category.
   *
   * @param dietCategory the diet category to filter by
   * @return a list of recipes matching the diet category
   */
  public List<Recipe> filterByDiet(DietCategory dietCategory) {
    return recipes.stream()
        .filter(recipe -> recipe.getDietCategory() == dietCategory)
        .collect(Collectors.toList());
  }

  /**
   * Filters recipes based on various criteria.
   *
   * @param criteria the filter criteria
   * @return a list of recipes matching the criteria
   */
  public List<Recipe> filterRecipes(FilterCriteria criteria) {
    return recipes.stream()
        .filter(recipe -> {
          if (criteria.getDietCategory() != null &&
              !criteria.getDietCategory().equals(recipe.getDietCategory())) {
            return false;
          }
          if (criteria.getDifficulty() != null &&
              !criteria.getDifficulty().equals(recipe.getDifficulty())) {
            return false;
          }
          if (criteria.getMaxPreparationTime() != null &&
              recipe.getPreperationTimeMinutes() > criteria.getMaxPreparationTime()) {
            return false;
          }
          if (criteria.getIngredients() != null &&
              !criteria.getIngredients().isEmpty() &&
              !criteria.getIngredients().stream()
                  .allMatch(ingredient -> recipe.getIngredients().stream()
                      .anyMatch(grocery -> grocery.getName().equalsIgnoreCase(ingredient)))) {
            return false;
          }
          if (criteria.getCategory() != null &&
              !criteria.getCategory().equalsIgnoreCase(recipe.getCategory())) {
            return false;
          }
          if (criteria.getCuisine() != null &&
              !criteria.getCuisine().equalsIgnoreCase(recipe.getCusine())) {
            return false;
          }
          return true;
        })
        .collect(Collectors.toList());
  }

  /**
   * Filters recipes by category.
   *
   * @param category the category to filter by
   * @return a list of recipes matching the category
   * @throws IllegalArgumentException if the category is null or empty
   */
  public List<Recipe> filterByCategory(String category) {
    if (category == null || category.trim().isEmpty()) {
      throw new IllegalArgumentException("Kategorien kan ikke være tom.");
    }
    return recipes.stream()
        .filter(recipe -> recipe.getCategory().equalsIgnoreCase(category.trim()))
        .collect(Collectors.toList());
  }

  /**
   * Gets all unique recipe categories in the cookbook.
   *
   * @return a sorted list of unique categories
   */
  public List<String> getAllCategories() {
    return recipes.stream()
        .map(Recipe::getCategory)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
  }
}


