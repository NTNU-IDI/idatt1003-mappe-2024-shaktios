package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private Fridge fridge;

    public RecipeManager(Fridge fridge) {
        this.fridge = fridge;
    }

    public boolean hasIngredients(Recipe recipe, int desiredServings) {
        double scalingFactor = (double) desiredServings / recipe.getServings();
        List<String> missingIngredients = new ArrayList<>();

        for (Grocery requiredIngredient : recipe.getIngredients()) {
            boolean found = false;
            double adjustedAmountNeeded = requiredIngredient.getAmount() * scalingFactor;

            for (Grocery fridgeItem : fridge.getItems()) {
                if (fridgeItem.getName().equalsIgnoreCase(requiredIngredient.getName())) {
                    found = true;
                    if (fridgeItem.getAmount() < adjustedAmountNeeded) {
                        missingIngredients.add(requiredIngredient.getName() +
                                               " (mangler " + (adjustedAmountNeeded - fridgeItem.getAmount()) + " " +
                                               fridgeItem.getMeasuringUnit() + ")");
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
