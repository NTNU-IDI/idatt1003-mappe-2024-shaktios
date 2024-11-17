package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cookbook {
    private List<Recipe> recipes; // Liste over alle oppskriftene

    // Konstruktør
      public Cookbook(){
        this.recipes = new ArrayList<>(); // starter oppskriftslisten
    }

    // Legg til en oppskrift
    public void addRecipe(Recipe recipe) {
        if (recipe != null && !recipes.contains(recipe)) {
            recipes.add(recipe);
            System.out.println("Oppskrift lagt til: " + recipe.getName());
        } else {
            System.out.println("Oppskriften finnes allerede eller er ugyldig.");
        }
    }

    // Fjern en oppskrift
    public void removeRecipe(Recipe recipe) {
        if (recipes.remove(recipe)) {
            System.out.println("Oppskrift fjernet: " + recipe.getName());
        } else {
            System.out.println("Oppskriften ble ikke funnet.");
        }
    }

    public List <Recipe> getAllRecipes(){
        return new ArrayList<>(recipes); //returnerer en kopi av listen. 
    }

    //søk etter en oppskrift etter navn
    public List<Recipe> searchByName(String name){
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe :recipes){
            if(recipe.getName().equalsIgnoreCase(name)){
                foundRecipes.add(recipe);
            }
        }
    return foundRecipes;    

    }


    //finner oppskrifter med en spesifikk ingrediens
    public List<Recipe> searchByIngredient(String ingredientName) {
        List<Recipe> foundRecipes = new ArrayList<>(); 
        
        for (Recipe recipe : recipes) {
            for (Grocery ingredient : recipe.getIngredients()) {
                if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                    foundRecipes.add(recipe);
                    break; // Bryter ut av den indre løkken etter å ha funnet ingrediensen
                }
            }
        }
        
        return foundRecipes; // Returnerer listen over oppskrifter den har funnet
    }
   

    public List<Recipe> suggestRecipes(Fridge fridge, int desiredServings) {
        Map<Recipe, Integer> recipeAvailabilityMap = new HashMap<>();

        for (Recipe recipe : recipes) {
            int availableCount = 0;
            double scalingFactor = (double) desiredServings / recipe.getServings();

            for (Grocery ingredient : recipe.getIngredients()) {
                Grocery fridgeItem = fridge.searchItem(ingredient.getName());
                double adjustedAmountNeeded = ingredient.getAmount() * scalingFactor;

                if (fridgeItem != null && fridgeItem.getAmount() >= adjustedAmountNeeded) {
                    availableCount++;
                }
            }

            recipeAvailabilityMap.put(recipe, availableCount);
        }

        return recipeAvailabilityMap.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    
}
    

