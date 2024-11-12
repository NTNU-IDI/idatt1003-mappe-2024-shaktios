package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;



public class RecipeManager {
    private Fridge fridge; // Referanse til kjøleskapet

    public RecipeManager(Fridge fridge) {
        this.fridge = fridge;
    }

    public boolean hasIngredients(Recipe recipe, int desiredServings) {
        List<String> missingIngredients = new ArrayList<>();
    
        // Finn skalering basert på ønsket antall porsjoner
        double scalingFactor = (double) desiredServings / recipe.getServings();
    
        for (Grocery requiredIngredient : recipe.getIngredients()) {
            boolean found = false;
    
            // Juster mengden av ingrediensen basert på ønsket antall porsjoner
            double adjustedAmountNeeded = requiredIngredient.getAmount() * scalingFactor;
    
            for (Grocery fridgeItem : fridge.getItems()) {
                if (fridgeItem.getName().equalsIgnoreCase(requiredIngredient.getName())) {
                    found = true;
    
                    // Sjekk om det er nok i kjøleskapet
                    if (fridgeItem.getAmount() < adjustedAmountNeeded) {
                        double shortage = adjustedAmountNeeded - fridgeItem.getAmount();
                        missingIngredients.add(requiredIngredient.getName() + 
                                               " (mangler " + shortage + " " + fridgeItem.getMeasuringUnit() + ")");
                    }
                    break;
                }
            }
    
            if (!found) {
                missingIngredients.add(requiredIngredient.getName() + " (mangler helt)");
            }
        }
    
        // Hvis det mangler ingredienser, skriv ut listen og returner false
        if (!missingIngredients.isEmpty()) {
            System.out.println("Kjøleskapet mangler følgende ingredienser eller har for lite mengde:");
            for (String ingredient : missingIngredients) {
                System.out.println("- " + ingredient);
            }
            return false;
        }
    
        return true;  // Returner true hvis alle ingredienser er tilgjengelige i riktig mengde
    }
    
}

    

