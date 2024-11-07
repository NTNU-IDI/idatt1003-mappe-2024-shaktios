package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;



public class RecipeManager {
    private Fridge fridge; // Referanse til kjøleskapet

    public RecipeManager(Fridge fridge) {
        this.fridge = fridge;
    }

    // Metode for å sjekke om kjøleskapet har nok ingredienser til en oppskrift
    public boolean hasIngredients(Recipe recipe) {
        List<String> missingIngredients = new ArrayList<>();

        for (Grocery requiredIngredient : recipe.getIngredients()) {
            boolean found = false;

            for (Grocery fridgeItem : fridge.getItems()) {
                if (fridgeItem.getName().equalsIgnoreCase(requiredIngredient.getName())) {
                    found = true;

                    if (fridgeItem.getAmount() < requiredIngredient.getAmount()) {
                        double shortage = requiredIngredient.getAmount() - fridgeItem.getAmount();
                        missingIngredients.add(requiredIngredient.getName() + " (mangler " + shortage + " " + fridgeItem.getMeasuringUnit() + ")");
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
            for (String ingredient : missingIngredients) {
                System.out.println("- " + ingredient);
            }
            return false;
        }

        return true;
    }
}

    

