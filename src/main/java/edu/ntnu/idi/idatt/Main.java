package edu.ntnu.idi.idatt;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.enums.MeasuringUnit;
import edu.ntnu.idi.idatt.helpers.CookbookInputHelper;
import edu.ntnu.idi.idatt.helpers.GroceryInputHelper;
import edu.ntnu.idi.idatt.managers.RecipeSearchManager;
import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;

public class Main {

    private Fridge fridge;
    private Cookbook cookbook;
    private Scanner scanner;

    public Main() {
        this.fridge = new Fridge();
        this.cookbook = new Cookbook();
        this.scanner = new Scanner(System.in);
        this.cookbookInputHelper = new CookbookInputHelper();
        this.recipeSearchManager = new RecipeSearchManager(cookbook, cookbookInputHelper);
    }
    

    public void start() {
        while (true) {
            System.out.println("\n--- Hovedmeny ---");
            System.out.println("1. Administrer kjøleskap");
            System.out.println("2. Administrer oppskrifter");
            System.out.println("3. Avansert søk etter oppskrifter (flere kategorier)");
            System.out.println("4. Foreslå oppskrifter basert på det du har i kjøleskapet (Foreslår bare oppskrifter der du har alle ingrediensene)");
            System.out.println("5. Avslutt");

            int choice = readInt("Velg et alternativ: ");
            switch (choice) {
                case 1 -> manageFridge();
                case 2 -> manageRecipes();
                case 3 -> searchRecipes();
                case 4 -> suggestRecipes();
                case 5 -> {
                    System.out.println("Avslutter programmet. Ha en fin dag!");
                    return;
                }
                default -> System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }


    private void manageFridge() {
        while (true) {
            System.out.println("\n--- Kjøleskapsmeny ---");
            System.out.println("1. Legg til vare");
            System.out.println("2. Fjern alt av en spesifikk vare");
            System.out.println("3. Vis alle varer");
            System.out.println("4. Søk etter en vare");
            System.out.println("5. Vis varer som går ut på dato snart");
            System.out.println("6. Vis totalverdien av varer");
            System.out.println("7. Fjern en bestemt mengde av en vare");
            System.out.println("8. Vis utgåtte varer");
            System.out.println("9. Fjern alle utgåtte varer");
            System.out.println("10. Vis varer sortert alfabetisk");
            System.out.println("11. Sorter like varer etter utløpsdato");
            System.out.println("12. Vis utgåtte varer og totalverdi");
            System.out.println("13. Tilbake til hovedmenyen");
    
            int choice = readInt("Velg et alternativ: ");
            switch (choice) {
                case 1 -> addGrocery();
                case 2 -> removeGrocery();
                case 3 -> displayItems();
                case 4 -> searchGrocery();
                case 5 -> displayExpiringSoon();
                case 6 -> displayTotalValue();
                case 7 -> removeItemByAmount();
                case 8 -> displayExpiredItems();
                case 9 -> removeAllExpiredItems();
                case 10 -> displaySortedItemsByName();
                case 11 -> sortItemsByExpiry();
                case 12 -> displayExpiredItemsAndTotalValue();
                case 13 -> {
                    System.out.println("Går tilbake til hovedmenyen.");
                    return;
                }
                default -> System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }
    
    private GroceryInputHelper groceryInputHelper = new GroceryInputHelper(); // Opprett en instans

    private void addGrocery() {
        System.out.println("\n--- Legg til vare ---");
        String name = groceryInputHelper.readGroceryName();
        MeasuringUnit unit = groceryInputHelper.readMeasuringUnit();
        double amount = groceryInputHelper.readPositiveDouble("Mengde: ");
        int days = groceryInputHelper.readPositiveInt("Dager til utløp: ");
        double price = groceryInputHelper.readPositiveDouble("Pris per enhet: ");

        try {
            Grocery grocery = new Grocery(name, amount, unit, days, price);
            fridge.addItem(grocery);
            System.out.println("Varen " + name + " er lagt til i kjøleskapet.");
        } catch (IllegalArgumentException e) {
            System.out.println("Feil: " + e.getMessage());
        }
    }

    private void removeGrocery() {
        System.out.println("\n--- Fjern vare ---");
        String name = groceryInputHelper.readGroceryName();
        Grocery item = fridge.searchItem(name);
        if (item != null) {
            fridge.removeWholeItem(item);
        } else {
            System.out.println("Fant ikke varen i kjøleskapet.");
        }
    }

    private void displayItems() {
        System.out.println("\n--- Viser alle varer ---");
    
        // Hent varer fra kjøleskapet
        List<Grocery> items = fridge.getItems();
    
        // Hvis listen er tom, skriv ut melding og avslutt
        if (items.isEmpty()) {
            System.out.println("Det er ingen varer i kjøleskapet, det er tomt.");
            return;
        }
    
        // Hvis det finnes varer, skriv dem ut
        System.out.println("Varer i kjøleskapet:");
        for (Grocery item : items) {
            System.out.println(item); 
            System.out.println("");
        }
    }
    

    private void searchGrocery() {
        System.out.println("\n--- Søk etter vare ---");
        String name = groceryInputHelper.readGroceryName();
        Grocery item = fridge.searchItem(name);
        if (item != null) {
            System.out.println("Fant varen: " + item);
        } else {
            System.out.println("Fant ikke varen.");
        }
    }

    private void displayExpiringSoon() {
        int days = groceryInputHelper.readPositiveInt("Vis varer som går ut på dato innen (antall dager): ");
        fridge.displayExpiringSoon(days);
    }

    private void displayTotalValue() {
        double totalValue = fridge.calculateTotalValue();
        System.out.println("Den totale verdien av varer i kjøleskapet er: " + totalValue + " NOK");
    }

    private void removeItemByAmount() {
        String name = groceryInputHelper.readGroceryName();
        double amount = groceryInputHelper.readPositiveDouble("Mengde som skal fjernes: ");
        fridge.removeItemByAmount(name, amount);
    }

    private void displaySortedItemsByName() {
        fridge.getSortedItemsByName().forEach(System.out::println);
    }
    
    private void sortItemsByExpiry() {
        String name = groceryInputHelper.readGroceryName();
        List<Grocery> sortedItems = fridge.getSortedItemsByExpiry(name);
        if (sortedItems.isEmpty()) {
            System.out.println("Ingen varer med navnet " + name + " finnes i kjøleskapet.");
        } else {
            System.out.println("Varer sortert etter utløpsdato:");
            sortedItems.forEach(System.out::println);
        }
    }

    private void displayExpiredItems() {
        fridge.displayExpiredItems();
    }

    private void removeAllExpiredItems() {
        fridge.removeAllExpiredItems();
        System.out.println("Alle utgåtte varer er fjernet fra kjøleskapet.");
    }
    
    private void displayExpiredItemsAndTotalValue() {
        fridge.displayExpiredItemsAndTotalValue();
    }
    
    
    

    private void manageRecipes() {
        while (true) {
            System.out.println("\n--- Oppskriftsmeny ---");
            System.out.println("1. Legg til oppskrift");
            System.out.println("2. Fjern oppskrift");
            System.out.println("3. Vis alle oppskrifter");
            System.out.println("4. Søk etter oppskrift etter navn");
            System.out.println("5. Søk etter oppskrift basert på ingrediens");
            System.out.println("6. Filtrer oppskrifter etter kategori");
            System.out.println("7. Sorter oppskrifter etter tilberedningstid");
            System.out.println("8. Sorter oppskrifter etter vanskelighetsgrad");
            System.out.println("9. Tilbake til hovedmenyen");
    
            int choice = readInt("Velg et alternativ: ");
            switch (choice) {
                case 1 -> addRecipe();
                case 2 -> removeRecipe();
                case 3 -> displayAllRecipes();
                case 4 -> searchRecipeByName();
                case 5 -> searchRecipeByIngredient();
                case 6 -> filterRecipesByCategory();
                case 7 -> sortRecipesByPreparationTime();
                case 8 -> sortRecipesByDifficulty();
                case 9 -> {
                    System.out.println("Går tilbake til hovedmenyen.");
                    return;
                }
                default -> System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }
    
    private CookbookInputHelper cookbookInputHelper = new CookbookInputHelper();


    private void addRecipe() {
        System.out.println("\n--- Legg til oppskrift ---");
    
        String name = cookbookInputHelper.readString("Navn på oppskriften: ");
        String description = cookbookInputHelper.readString("Beskrivelse: ");
        String instructions = cookbookInputHelper.readString("Instruksjoner: ");
        int prepTime = cookbookInputHelper.readInt("Tilberedningstid (minutter): ");
        int servings = cookbookInputHelper.readServings();
        String category = cookbookInputHelper.readCategory();
        String cuisine = cookbookInputHelper.readCuisine();
        DietCategory dietCategory = cookbookInputHelper.readDietCategory();
        Difficulty difficulty = cookbookInputHelper.readDifficulty();
    
        List<Grocery> ingredients = new ArrayList<>();
        while (true) {
            System.out.println("\nLegg til en ingrediens (eller skriv 'Q' for å avslutte):");
            String ingredientName = cookbookInputHelper.readString("Ingrediensnavn: ");
            if (ingredientName.equalsIgnoreCase("Q")) {
                if (ingredients.isEmpty()) {
                    System.out.println("Du må legge til minst én ingrediens før du avslutter.");
                    continue;
                }
                break;
            }
    
            MeasuringUnit unit = cookbookInputHelper.readMeasuringUnit();
            double amount = cookbookInputHelper.readDouble("Mengde: ");
            ingredients.add(new Grocery(ingredientName, amount, unit, 0, 0));
            System.out.println("Ingrediens " + ingredientName + " er lagt til.");
        }
    
        Recipe recipe = new Recipe(
            name, description, instructions, ingredients, servings, category,
            prepTime, dietCategory, difficulty, cuisine
        );
        cookbook.addRecipe(recipe);
        System.out.println("Oppskrift lagt til: " + name);
    }
    
    

    private void removeRecipe() {
        System.out.println("\n--- Fjern oppskrift ---");
        String name = cookbookInputHelper.readString("Navn: ");
        List<Recipe> foundRecipes = cookbook.searchByName(name);
    
        if (foundRecipes.isEmpty()) {
            System.out.println("Fant ingen oppskrifter med navnet: " + name);
        } else if (foundRecipes.size() == 1) {
            cookbook.removeRecipe(foundRecipes.get(0)); 
            System.out.println("Oppskriften er fjernet.");
        } else {
            System.out.println("Flere oppskrifter ble funnet med navnet: " + name);
            for (int i = 0; i < foundRecipes.size(); i++) {
                System.out.println((i + 1) + ": " + foundRecipes.get(i));
            }
    
            int choice = cookbookInputHelper.readInt("Velg hvilken oppskrift som skal fjernes (tast nummer): ");
            if (choice > 0 && choice <= foundRecipes.size()) {
                cookbook.removeRecipe(foundRecipes.get(choice - 1));
                System.out.println("Oppskriften er fjernet.");
            } else {
                System.out.println("Ugyldig valg. Ingen oppskrift ble fjernet.");
}
        }
    }
    
    

    private void displayAllRecipes() {
        List<Recipe> recipes = cookbook.getAllRecipes();
        if (recipes.isEmpty()) {
            System.out.println("Ingen oppskrifter tilgjengelige.");
        } else {
            System.out.println("\n--- Alle oppskrifter ---");
            int index = 1;
            for (Recipe recipe : recipes) {
                System.out.printf("%d. %s%n", index++, recipe.getName()); // Viser indeks og navn på oppskriften
                System.out.println(recipe); 
                System.out.println(); // Legger til en ekstra linje for bedre lesbarhet
            }
        }
    }
    

    private void searchRecipeByName() {
        System.out.println("\n--- Søk etter oppskrift etter navn ---");
        String name = cookbookInputHelper.readString("Navn på oppskrift: ");
        List<Recipe> foundRecipes = cookbook.searchByName(name);
        if (foundRecipes.isEmpty()) {
            System.out.println("Ingen oppskrifter funnet med navnet: " + name);
        } else {
            System.out.println("Funnet oppskrifter:");
            foundRecipes.forEach(System.out::println); 
        }
    }
    
    private void searchRecipeByIngredient() {
        System.out.println("\n--- Søk etter oppskrifter basert på ingrediens ---");
        String ingredient = cookbookInputHelper.readString("Navn på ingrediens: ");
        List<Recipe> foundRecipes = cookbook.searchByIngredient(ingredient);
        if (foundRecipes.isEmpty()) {
            System.out.println("Ingen oppskrifter funnet med ingrediensen: " + ingredient);
        } else {
            System.out.println("Funnet oppskrifter:");
            foundRecipes.forEach(System.out::println);
        }
    }

    private void filterRecipesByCategory() {
        System.out.println("\n--- Filtrer oppskrifter etter kategori ---");
    
        // Hent og vis alle tilgjengelige kategorier
        List<String> availableCategories = cookbook.getAllCategories();
        if (availableCategories.isEmpty()) {
            System.out.println("Ingen kategorier er tilgjengelige.");
            return;
        }
    
        System.out.println("Tilgjengelige kategorier:");
        availableCategories.forEach(System.out::println); // Vis hver kategori
    
        // La brukeren velge en kategori
        String category = cookbookInputHelper.readString("Skriv inn en kategori for å søke: ");
        List<Recipe> filteredRecipes = cookbook.filterByCategory(category);
        if (filteredRecipes.isEmpty()) {
            System.out.println("Ingen oppskrifter funnet i kategorien: " + category);
        } else {
            System.out.println("Oppskrifter i kategorien " + category + ":");
            filteredRecipes.forEach(System.out::println);
        }
    }
    

    private void sortRecipesByPreparationTime() {
        System.out.println("\n--- Sorter oppskrifter etter tilberedningstid ---");
        List<Recipe> sortedRecipes = cookbook.sortByPreperationTime();
        if (sortedRecipes.isEmpty()) {
            System.out.println("Ingen oppskrifter å vise.");
        } else {
            sortedRecipes.forEach(System.out::println);
        }
    }
    
    private void sortRecipesByDifficulty() {
        System.out.println("\n--- Sorter oppskrifter etter vanskelighetsgrad ---");
        List<Recipe> sortedRecipes = cookbook.sortByDifficulty();
        sortedRecipes.forEach(System.out::println);
    }



    private RecipeSearchManager recipeSearchManager;

    private void searchRecipes() {
        recipeSearchManager.searchRecipes();
    }

    
    private void suggestRecipes() {
        System.out.println("\n--- Foreslå oppskrifter ---");
    
        // Spør brukeren hvor mange porsjoner de ønsker
        int desiredServings = readInt("Antall ønskede porsjoner: ");
    
        // Foreslå oppskrifter basert på kjøleskapet og ønskede porsjoner
        List<Recipe> suggestedRecipes = cookbook.suggestRecipes(fridge, desiredServings);
    
        // Vis resultatene
        if (suggestedRecipes.isEmpty()) {
            System.out.println("Ingen oppskrifter kan foreslås basert på innholdet i kjøleskapet.");
        } else {
            System.out.println("Foreslåtte oppskrifter:");
            suggestedRecipes.forEach(System.out::println);
        }
    }
    
    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Tømmer linjeskift fra bufferen
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Vennligst skriv inn et heltall.");
                scanner.nextLine(); // Tømmer ugyldig input fra bufferen
            }
        }
    }
    

    public static void main(String[] args) {
        new Main().start();
    }
}
