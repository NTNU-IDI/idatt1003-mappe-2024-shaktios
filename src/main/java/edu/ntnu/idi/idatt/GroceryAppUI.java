package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

public class GroceryAppUI {

    private Fridge fridge; // Legger til et kjøleskap
    private RecipeManager recipeManager; // Oppretter RecipeManager
    private Cookbook cookbook; // Oppretter Cookbook

    // Konstruktør
    public GroceryAppUI() {
        this.fridge = new Fridge();
        this.recipeManager = new RecipeManager(fridge); // Initialiserer RecipeManager med fridge
        this.cookbook = new Cookbook(); // Initialiserer Cookbook
    }

    // Metode for å starte applikasjonen
    public void init(){
        System.out.println("Applikasjonen startes...");
    }

    public void start() {
        System.out.println("Velkommen til Best før appen!");

        // Legg til noen varer med mindre mengde enn det oppskriften krever
        fridge.addItem(new Grocery("Mel", 200, MeasuringUnit.GRAM, 10, 20.0)); // Bare 200 gram mel
        fridge.addItem(new Grocery("Egg", 2, MeasuringUnit.PIECE, 7, 3.0));    // Bare 2 egg
    
        // Viser alle varer i kjøleskapet
        fridge.displayItems();
    
        // Opprett en oppskrift med ingredienser som kjøleskapet delvis ikke har nok av
        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 5, 20.0)); // Krever 300 gram mel
        ingredients.add(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));   // Krever 4 egg
        ingredients.add(new Grocery("Sukker", 100, MeasuringUnit.GRAM, 5, 10.0)); // Krever 100 gram sukker
    
        Recipe pannekakeOppskrift = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients);
    
        // Sjekk om oppskriften kan lages med ingrediensene i kjøleskapet
        if (recipeManager.hasIngredients(pannekakeOppskrift)) {
            System.out.println("Du har nok ingredienser til å lage " + pannekakeOppskrift.getName());
        } else {
            System.out.println("Du mangler noen ingredienser for å lage " + pannekakeOppskrift.getName());
        }

        // Opprett flere oppskrifter
        List<Grocery> omelettIngredienser = new ArrayList<>();
        omelettIngredienser.add(new Grocery("Egg", 6, MeasuringUnit.PIECE, 7, 3.0));
        omelettIngredienser.add(new Grocery("Melk", 200, MeasuringUnit.MILLILITER, 5, 15.0));
        Recipe omelettOppskrift = new Recipe("Omelett", "Enkel omelettoppskrift", "Visp egg og melk og stek", omelettIngredienser);

        // Legg oppskrifter til Cookbook
        cookbook.addRecipe(pannekakeOppskrift);
        cookbook.addRecipe(omelettOppskrift);

        // Søk etter oppskrifter basert på navn
        List<Recipe> funnetNavn = cookbook.searchByName("Pannekaker");
        System.out.println("Søk etter 'Pannekaker' ga følgende resultater:");
        for (Recipe r : funnetNavn) {
            System.out.println(r);
        }

        // Søk etter oppskrifter basert på ingrediens
        List<Recipe> funnetIngrediens = cookbook.searchByIngredient("Egg");
        System.out.println("Søk etter oppskrifter med 'Egg' ga følgende resultater:");
        for (Recipe r : funnetIngrediens) {
            System.out.println(r);
        }
    }

    // Hovedmetode som starter applikasjonen
    public static void main(String[] args) {
        GroceryAppUI app = new GroceryAppUI();
        app.init();
        app.start();
    }
}