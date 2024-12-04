package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Comparator;
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

    /* //søk etter en oppskrift etter navn
    public List<Recipe> searchByName(String name){
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe :recipes){
            if(recipe.getName().equalsIgnoreCase(name)){
                foundRecipes.add(recipe);
            }
        }
    return foundRecipes;    

    }
 */
    public List<Recipe> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Navnet kan ikke være tomt eller null.");
        }

        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name.trim())) { // Trimmer whitespace
                foundRecipes.add(recipe);
            }
        }

        return foundRecipes; // Returner alle oppskrifter som matcher
    }


    //finner oppskrifter med en spesifikk ingrediens
    public List<Recipe> searchByIngredient(String ingredientName) {

        if (ingredientName == null || ingredientName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrediensnavn kan ikke være tomt.");
        }

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
            int availableIngredients = 0;
            double scalingFactor = (double) desiredServings / recipe.getServings();
            boolean canMakeRecipe = true;
    
            for (Grocery ingredient : recipe.getIngredients()) {
                Grocery fridgeItem = fridge.searchItem(ingredient.getName());
                double adjustedAmountNeeded = ingredient.getAmount() * scalingFactor;
    
                // Sjekk om ingrediensen finnes i kjøleskapet og er tilstrekkelig
                if (fridgeItem == null || fridgeItem.getAmount() < adjustedAmountNeeded) {
                    canMakeRecipe = false;
                    break;
                } else {
                    availableIngredients++;
                }
            }
    
            // Legg til oppskriften i resultatet hvis den kan lages
            if (canMakeRecipe) {
                recipeAvailabilityMap.put(recipe, availableIngredients);
            }
        }
    
        // Returner oppskrifter sortert etter hvor mange ingredienser som finnes i kjøleskapet
        return recipeAvailabilityMap.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    

    //Sorterer oppskrifter i stigende rekkefølge basert på tilberedningstid. Bruker stream da det er mye enklere enn for-løkke
    public List <Recipe> sortByPreperationTime(){
        return recipes.stream()
                        .sorted(Comparator.comparingInt(Recipe::getPreperationTimeMinutes))
                        .collect(Collectors.toList()); 
    }


    //Sorterer oppskrifter basert på vanskelighetsgrad i stigende rekkefølge. Bruker stream her også. 
    public List <Recipe> sortByDifficulty(){
        return recipes.stream()
                        .sorted(Comparator.comparing(Recipe::getDifficulty))
                        .collect(Collectors.toList());
    }

    //Filtrerer oppskrifter basert på diettkategori. Brukeren kan for eksempel finne alle oppskrifter som er veganske, vegetariske, pescetarianske, inneholder kjøtt eller tilhører en annen kategori.
    public List<Recipe> filterByDiet(DietCategory dietCategory) {
        return recipes.stream()
                .filter(recipe -> recipe.getDietCategory() == dietCategory)
                .collect(Collectors.toList());
    }
    

    public List<Recipe> filterRecipes(FilterCriteria criteria) {
        return recipes.stream()
            .filter(recipe -> {
                // Sjekker diettkategori
                if (criteria.getDietCategory() != null && 
                    !criteria.getDietCategory().equals(recipe.getDietCategory())) {
                    return false;
                }
    
                // Sjekker vanskelighetsgrad
                if (criteria.getDifficulty() != null && 
                    !criteria.getDifficulty().equals(recipe.getDifficulty())) {
                    return false;
                }
    
                // Sjekker maks tilberedningstid
                if (criteria.getMaxPreparationTime() != null && 
                    recipe.getPreperationTimeMinutes() > criteria.getMaxPreparationTime()) {
                    return false;
                }
    
                // Sjekker ingredienser
                if (criteria.getIngredients() != null && 
                    !criteria.getIngredients().isEmpty() &&
                    !criteria.getIngredients().stream()
                        .allMatch(ingredient -> recipe.getIngredients().stream()
                            .anyMatch(grocery -> grocery.getName().equalsIgnoreCase(ingredient)))) {
                    return false;
                }
    
                // Sjekker kategori
                if (criteria.getCategory() != null && 
                    !criteria.getCategory().equalsIgnoreCase(recipe.getCategory())) {
                    return false;
                }
    
                // Sjekker cuisine
                if (criteria.getCuisine() != null && 
                    !criteria.getCuisine().equalsIgnoreCase(recipe.getCusine())) {
                    return false;
                }
    
                return true;
            })
            .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return recipes.stream()
                      .map(Recipe::getCategory) // Henter kategorien for hver oppskrift
                      .distinct()               // Fjerner duplikater
                      .sorted()                 // Sorterer alfabetisk
                      .collect(Collectors.toList());
    }
    
    
    

}
    

