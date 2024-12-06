package edu.ntnu.idi.idatt.managers;

import java.util.ArrayList;
import java.util.List;

import edu.ntnu.idi.idatt.models.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;

public class RecipeManager {
    private Fridge fridge;

    public RecipeManager(Fridge fridge) {
        this.fridge = fridge;
    }

    public boolean hasIngredients(Recipe recipe, int desiredServings) {
        double scalingFactor = (double) desiredServings / recipe.getServings();
        List<String> missingIngredients = new ArrayList<>();
        final double TOLERANCE = 0.01; 
    
        for (Grocery requiredIngredient : recipe.getIngredients()) {
            boolean found = false;
            double adjustedAmountNeeded = requiredIngredient.getAmount() * scalingFactor;
    
            for (Grocery fridgeItem : fridge.getItems()) {
                if (fridgeItem.getName().equalsIgnoreCase(requiredIngredient.getName())) {
                    found = true; 
                    
                    // Sjekk tilgjengelig mengde med toleransemargin
                    if (fridgeItem.getAmount() + TOLERANCE < adjustedAmountNeeded) {
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
