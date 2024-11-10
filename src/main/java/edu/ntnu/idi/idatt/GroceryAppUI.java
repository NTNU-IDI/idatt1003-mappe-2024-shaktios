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
    
        // Legg til noen varer i kjøleskapet
        fridge.addItem(new Grocery("Mel", 200, MeasuringUnit.GRAM, 10, 20.0)); // 200 gram mel
        fridge.addItem(new Grocery("Egg", 2, MeasuringUnit.PIECE, 7, 3.0));    // 2 egg
        fridge.addItem(new Grocery("Melk", 200, MeasuringUnit.MILLILITER, 5, 15.0)); // 200 ml melk
        
        // Viser alle varer i kjøleskapet
        fridge.displayItems();
    
        // Opprett noen oppskrifter
        List<Grocery> pannekakeIngredienser = new ArrayList<>();
        pannekakeIngredienser.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 5, 20.0)); // Krever 300 gram mel
        pannekakeIngredienser.add(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));   // Krever 4 egg
        Recipe pannekaker = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", pannekakeIngredienser);
    
        List<Grocery> omelettIngredienser = new ArrayList<>();
        omelettIngredienser.add(new Grocery("Egg", 6, MeasuringUnit.PIECE, 7, 3.0));   // Krever 6 egg
        omelettIngredienser.add(new Grocery("Melk", 200, MeasuringUnit.MILLILITER, 5, 15.0)); // Krever 200 ml melk
        Recipe omelett = new Recipe("Omelett", "Enkel omelettoppskrift", "Visp egg og melk og stek", omelettIngredienser);
    
        List<Grocery> vaffelIngredienser = new ArrayList<>();
        vaffelIngredienser.add(new Grocery("Mel", 200, MeasuringUnit.GRAM, 5, 20.0));   // Krever 200 gram mel
        vaffelIngredienser.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));     // Krever 2 egg
        Recipe vafler = new Recipe("Vafler", "Sprø vaffeloppskrift", "Bland ingredienser og stek i vaffeljern", vaffelIngredienser);
    
        // Legg oppskrifter til kokeboken
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        cookbook.addRecipe(vafler);
    
        // Skriv ut foreslåtte oppskrifter basert på tilgjengelige ingredienser
        System.out.println("\nForeslåtte oppskrifter basert på ingrediensene i kjøleskapet:");
        List<Recipe> direkteForslag = cookbook.suggestRecipes(fridge);
        for (Recipe recipe : direkteForslag) {
            System.out.println("- " + recipe.getName());
        }
    }

    // Hovedmetode som starter applikasjonen
    public static void main(String[] args) {
        GroceryAppUI app = new GroceryAppUI();
        app.init();
        app.start();
    }
}