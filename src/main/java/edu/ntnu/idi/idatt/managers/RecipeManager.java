package edu.ntnu.idi.idatt.managers;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.idi.idatt.models.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;

/**
 * Manages recipes and checks if required ingredients are available in the fridge.
 */
public class RecipeManager {
  private Fridge fridge;

  /**
   * Constructs a RecipeManager with a given fridge.
   *
   * @param fridge the fridge to check for ingredients
   */
  public RecipeManager(Fridge fridge) {
    this.fridge = fridge;
  }

  /**
   * Checks if the fridge has enough ingredients for a given recipe with the desired servings.
   *
   * @param recipe the recipe to check
   * @param desiredServings the desired number of servings
   * @return true if all required ingredients are available (sufficient quantities), false otherwise
   */
  public boolean hasIngredients(Recipe recipe, int desiredServings) {
    double scalingFactor = (double) desiredServings / recipe.getServings();
    List<String> missingIngredients = new ArrayList<>();
    final double tolerance = 0.01;

    for (Grocery requiredIngredient : recipe.getIngredients()) {
      boolean found = false;
      double adjustedAmountNeeded = requiredIngredient.getAmount() * scalingFactor;

      for (Grocery fridgeItem : fridge.getItems()) {
        if (fridgeItem.getName().equalsIgnoreCase(requiredIngredient.getName())) {
          found = true;

          // Check available quantity with tolerance
          if (fridgeItem.getAmount() + tolerance < adjustedAmountNeeded) {
            missingIngredients.add(
                requiredIngredient.getName()
                    + " (mangler "
                    + (adjustedAmountNeeded - fridgeItem.getAmount())
                    + " "
                    + fridgeItem.getMeasuringUnit()
                    + ")");
          }
          break;
        }
      }

      if (!found) {
        missingIngredients.add(requiredIngredient.getName() + " (mangler helt)");
      }
    }

    if (!missingIngredients.isEmpty()) {
      System.out.println("Kjøleskapet mangler følgende ingredienser eller har for lite mengde:");
      missingIngredients.forEach(ingredient -> System.out.println("- " + ingredient));
      return false;
    }

    return true;
  }
}

