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
        // Opprett kjøleskap og kokebok
        Fridge fridge = new Fridge();
        Cookbook cookbook = new Cookbook();
    
        // Legg til noen ingredienser i kjøleskapet
        fridge.addItem(new Grocery("Melk", 2.0, MeasuringUnit.LITER, 5, 20.0));
        fridge.addItem(new Grocery("Egg", 12, MeasuringUnit.PIECE, 7, 3.0));
        fridge.addItem(new Grocery("Mel", 1000, MeasuringUnit.GRAM, 10, 10.0));
    
        // Opprett oppskrifter
        List<Grocery> pancakeIngredients = new ArrayList<>();
        pancakeIngredients.add(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 0, 20.0));
        pancakeIngredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 0, 3.0));
        pancakeIngredients.add(new Grocery("Mel", 200, MeasuringUnit.GRAM, 0, 10.0));
        Recipe pancakes = new Recipe("Pannekaker", "Enkle pannekaker", "Bland og stek", pancakeIngredients, 4);
    
        List<Grocery> omeletIngredients = new ArrayList<>();
        omeletIngredients.add(new Grocery("Egg", 3, MeasuringUnit.PIECE, 0, 3.0));
        omeletIngredients.add(new Grocery("Melk", 0.5, MeasuringUnit.LITER, 0, 20.0));
        Recipe omelet = new Recipe("Omelett", "Fluffy omelett", "Pisk og stek", omeletIngredients, 2);
    
        // Legg til oppskrifter i kokeboken
        cookbook.addRecipe(pancakes);
        cookbook.addRecipe(omelet);
    
        // Sjekk om kjøleskapet har ingredienser til pannekakeoppskriften for ønsket antall porsjoner
        RecipeManager recipeManager = new RecipeManager(fridge);
        int desiredServings = 2;
        System.out.println("Har ingredienser til Pannekaker for " + desiredServings + " porsjoner? " +
                recipeManager.hasIngredients(pancakes, desiredServings));
    
        // Foreslå oppskrifter basert på tilgjengelige ingredienser for ønsket antall porsjoner
        System.out.println("\nForeslåtte oppskrifter for " + desiredServings + " porsjoner:");
        List<Recipe> suggestedRecipes = cookbook.suggestRecipes(fridge, desiredServings);
        for (Recipe recipe : suggestedRecipes) {
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

