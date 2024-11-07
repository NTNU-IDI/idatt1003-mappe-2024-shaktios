package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

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


    
}
