package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

public class GroceryAppUI {

    private Fridge fridge; // Legger til et kjøleskap
    private RecipeManager recipeManager; // Oppretter RecipeManager

    // Konstruktør
    public GroceryAppUI() {
        this.fridge = new Fridge();
        this.recipeManager = new RecipeManager(fridge); // Initialiserer RecipeManager med fridge
    }


    //metode for å starte applikasjonen
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
    
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients);
    
        // Sjekk om oppskriften kan lages med ingrediensene i kjøleskapet
        RecipeManager recipeManager = new RecipeManager(fridge);
    
        if (recipeManager.hasIngredients(recipe)) {
            System.out.println("Du har nok ingredienser til å lage " + recipe.getName());
        } else {
            System.out.println("Du mangler noen ingredienser for å lage " + recipe.getName());
        }
    }


     // Metode for å skrive ut detaljene for en vare
     public void printGroceryDetails(Grocery grocery) {
        System.out.println(grocery);  
    }

    public void searchAndDisplayItem(String name){
        Grocery item = fridge.searchItem(name); //bruker søkemetoden vi lagde i fridge.java
        if (item != null){
            System.out.println("Varen ble funnet! " + item);
        }
        else{
            System.out.println("Varen " + name + " finnes dessverre ikke i kjøleskapet ");
        }
    }

     //metode for å vise totalsummen av varer

     public void displayTotalValueInFridge(){
        double totalValue = fridge.calculateTotalValue();
        System.out.println("Den samlede verdien i kjøleskapet er " + totalValue + " NOK");

     }


    // Hovedmetode som starter applikasjonen
    public static void main(String[] args) {
        GroceryAppUI app = new GroceryAppUI();
        app.init();  // starter applikasjonen
        app.start(); // Starter og tester Grocery-klassen
    }
    
}
