package edu.ntnu.idi.idatt.managers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.enums.MeasuringUnit;
import edu.ntnu.idi.idatt.helpers.CookbookInputHelper;
import edu.ntnu.idi.idatt.helpers.GroceryInputHelper;
import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;

/**
 * Manager class for the application.
 *
 * This class serves as the entry point for the program provides a menu-driven
 * interface for managing recipes, groceries, and other related functionalities.
 * It integrates with various helper classes and managers to handle user inputs,
 * process commands, and display results.
 */
public class Kitchenmanager {

    /**
     * Constructor to initialize the application components.
     */
    private Fridge fridge;
    private Cookbook cookbook;
    private Scanner scanner;

    public Kitchenmanager() {
        this.fridge = new Fridge();
        this.cookbook = new Cookbook();
        this.scanner = new Scanner(System.in);
        this.cookbookInputHelper = new CookbookInputHelper();
        this.recipeSearchManager = new RecipeSearchManager(cookbook, cookbookInputHelper);
    }

    /**
     * Initializes the kitchen manager with predefined data.
     *
     * This method sets up initial groceries in the fridge and predefined
     * recipes in the cookbook for demonstration and testing purposes.
     *
     * Groceries added:
     *
     * Carrot (2.0 kg, 7 days to expiry, 30 NOK) Onion (1.5 kg, 5 days to
     * expiry, 20 NOK) Tomato (1.0 kg, 3 days to expiry, 25 NOK)
     *
     *
     *
     * Recipes added:
     *
     * "Gulrotsuppe" - A vegan carrot soup made with carrots and onions.
     * "Middelhavssalat" - A vegetarian tomato salad made with tomatoes and feta
     * cheese.
     *
     *
     */
    /**
     * Starts the application and displays the main menu. The application runs
     * in a loop until the user chooses to exit.
     */
    public void init() {
        // Legger til grønnsaker i kjøleskapet
        fridge.addItem(new Grocery("Gulrot", 2.0, MeasuringUnit.KILOGRAM, 7, 30));
        fridge.addItem(new Grocery("Løk", 1.5, MeasuringUnit.KILOGRAM, 5, 20));
        fridge.addItem(new Grocery("Tomat", 1.0, MeasuringUnit.KILOGRAM, 3, 25));

        // Lager ingredienser til oppskrifter
        List<Grocery> soupIngredients = new ArrayList<>();
        soupIngredients.add(new Grocery("Gulrot", 0.5, MeasuringUnit.KILOGRAM, 0, 0));
        soupIngredients.add(new Grocery("Løk", 0.2, MeasuringUnit.KILOGRAM, 0, 0));

        List<Grocery> saladIngredients = new ArrayList<>();
        saladIngredients.add(new Grocery("Tomat", 0.3, MeasuringUnit.KILOGRAM, 0, 0));
        saladIngredients.add(new Grocery("Fetaost", 0.5, MeasuringUnit.KILOGRAM, 0, 0));

        // Legger til oppskrifter i kokeboken
        cookbook.addRecipe(new Recipe(
                "Gulrotsuppe",
                "En sunn og enkel gulrotsuppe.",
                "Kok opp gulrøtter og løk, mos dem sammen til suppe.",
                soupIngredients,
                4,
                "Supper",
                30,
                DietCategory.VEGAN,
                Difficulty.EASY,
                "Norsk"));

        cookbook.addRecipe(new Recipe(
                "Middelhavssalat",
                "En frisk og enkel Middelhavssalat.",
                "Bland tomater og fetaost, server med dressing.",
                saladIngredients,
                2,
                "Salater",
                10,
                DietCategory.VEGETARIAN,
                Difficulty.EASY,
                "Middelhavsmat"));
    }

    /**
     * Starts the application and displays the main menu. The application runs
     * in a loop until the user chooses to exit.
     */
    public void start() {
        while (true) {
            System.out.println("\n--- Hovedmeny ---");
            System.out.println("1. Administrer kjøleskap");
            System.out.println("2. Administrer oppskrifter");
            System.out.println("3. Avansert søk etter oppskrifter (flere kategorier)");
            System.out.println(
                    "4. Foreslå oppskrifter basert på det du har i kjøleskapet (Foreslår bare oppskrifter der du har alle ingrediensene)");
            System.out.println("5. Avslutt");

            int choice = readInt("Velg et alternativ: ");
            switch (choice) {
                case 1 ->
                    manageFridge();
                case 2 ->
                    manageRecipes();
                case 3 ->
                    searchRecipes();
                case 4 ->
                    suggestRecipes();
                case 5 -> {
                    System.out.println("Avslutter programmet. Ha en fin dag!");
                    return;
                }
                default ->
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    /**
     * Displays the fridge management menu and handles user interactions.
     */
    private void manageFridge() {
        // Fridge management logic
        while (true) {
            System.out.println("\n--- Kjøleskapsmeny ---");
            System.out.println("1. Legg til vare");
            System.out.println("2. Fjern alt av en spesifikk vare");
            System.out.println("3. Vis alle varer");
            System.out.println("4. Søk etter en vare");
            System.out.println("5. Vis varer som går ut på dato snart");
            System.out.println("6. Vis totalverdien av alle varene i kjøleskapet");
            System.out.println("7. Fjern en bestemt mengde av en vare");
            System.out.println("8. Vis utgåtte varer");
            System.out.println("9. Fjern alle utgåtte varer");
            System.out.println("10. Vis varer sortert alfabetisk");
            System.out.println("11. Sorter like varer etter utløpsdato");
            System.out.println("12. Vis utgåtte varer og totalverdi");
            System.out.println("13. Tilbake til hovedmenyen");

            int choice = readInt("Velg et alternativ: ");
            switch (choice) {
                case 1 ->
                    addGrocery();
                case 2 ->
                    removeGrocery();
                case 3 ->
                    displayItems();
                case 4 ->
                    searchGrocery();
                case 5 ->
                    displayExpiringSoon();
                case 6 ->
                    displayTotalValue();
                case 7 ->
                    removeItemByAmount();
                case 8 ->
                    displayExpiredItems();
                case 9 ->
                    removeAllExpiredItems();
                case 10 ->
                    displaySortedItemsByName();
                case 11 ->
                    sortItemsByExpiry();
                case 12 ->
                    displayExpiredItemsAndTotalValue();
                case 13 -> {
                    System.out.println("Går tilbake til hovedmenyen.");
                    return;
                }
                default ->
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    private GroceryInputHelper groceryInputHelper = new GroceryInputHelper(); // Creating an instance

    /**
     * Adds a new grocery item to the fridge. Prompts the user for grocery
     * details.
     */
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

    /**
     * Removes a grocery item completely from the fridge based on the user's
     * input.
     */
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

    /**
     * Displays all items currently in the fridge.
     */
    private void displayItems() {
        System.out.println("\n--- Viser alle varer ---");

        // Getting groceries from the fridge
        List<Grocery> items = fridge.getItems();

        // if the list is empty, print the message and melding og exit
        if (items.isEmpty()) {
            System.out.println("Det er ingen varer i kjøleskapet, det er tomt.");
            return;
        }

        // If there is groceries, print them out.
        System.out.println("Varer i kjøleskapet:");
        for (Grocery item : items) {
            System.out.println(item);
            System.out.println("");
        }
    }

    /**
     * Searches for a grocery item by name in the fridge.
     */
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

    /**
     * Displays items in the fridge that are expiring soon. Prompts the user to
     * specify the number of days.
     */
    private void displayExpiringSoon() {
        int days = groceryInputHelper.readPositiveInt("Vis varer som går ut på dato innen (antall dager): ");
        fridge.displayExpiringSoon(days);
    }

    /**
     * Calculates and displays the total value of all items in the fridge.
     */
    private void displayTotalValue() {
        double totalValue = fridge.calculateTotalValue();
        System.out.println("Den totale verdien av varer i kjøleskapet er: " + totalValue + " NOK");
    }

    /**
     * Removes a specified amount of a grocery item from the fridge.
     */
    private void removeItemByAmount() {
        String name = groceryInputHelper.readGroceryName();
        double amount = groceryInputHelper.readPositiveDouble("Mengde som skal fjernes: ");
        fridge.removeItemByAmount(name, amount);
    }

    /**
     * Displays all items in the fridge sorted alphabetically by name.
     */
    private void displaySortedItemsByName() {
        fridge.getSortedItemsByName().forEach(System.out::println);
    }

    /**
     * Sorts and displays items with the same name by their expiry dates.
     */
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

    /**
     * Displays all expired items in the fridge.
     */
    private void displayExpiredItems() {
        fridge.displayExpiredItems();
    }

    /**
     * Removes all expired items from the fridge.
     */
    private void removeAllExpiredItems() {
        fridge.removeAllExpiredItems();
        System.out.println("Alle utgåtte varer er fjernet fra kjøleskapet.");
    }

    /**
     * Displays expired items and calculates their total value.
     */
    private void displayExpiredItemsAndTotalValue() {
        fridge.displayExpiredItemsAndTotalValue();
    }

    /**
     * Displays the recipe management menu and handles user interactions.
     */
    private void manageRecipes() {
        while (true) {
            System.out.println("\n--- Oppskriftsmeny ---");
            System.out.println("1. Legg til oppskrift");
            System.out.println("2. Fjern oppskrift");
            System.out.println("3. Vis alle oppskrifter");
            System.out.println("4. Søk etter oppskrift etter navn");
            System.out.println("5. Søk etter oppskrift basert på en ingrediens");
            System.out.println("6. Filtrer oppskrifter etter en kategori");
            System.out.println("7. Sorter oppskrifter etter tilberedningstid");
            System.out.println("8. Sorter oppskrifter etter vanskelighetsgrad");
            System.out.println("9. Tilbake til hovedmenyen");

            int choice = readInt("Velg et alternativ: ");
            switch (choice) {
                case 1 ->
                    addRecipe();
                case 2 ->
                    removeRecipe();
                case 3 ->
                    displayAllRecipes();
                case 4 ->
                    searchRecipeByName();
                case 5 ->
                    searchRecipeByIngredient();
                case 6 ->
                    filterRecipesByCategory();
                case 7 ->
                    sortRecipesByPreparationTime();
                case 8 ->
                    sortRecipesByDifficulty();
                case 9 -> {
                    System.out.println("Går tilbake til hovedmenyen.");
                    return;
                }
                default ->
                    System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    private CookbookInputHelper cookbookInputHelper = new CookbookInputHelper();

    /**
     * Adds a new recipe to the cookbook. Prompts the user for recipe details.
     */
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
                prepTime, dietCategory, difficulty, cuisine);
        cookbook.addRecipe(recipe);
        System.out.println("Oppskrift lagt til: " + name);
    }

    /**
     * Removes a recipe from the cookbook. Handles cases where multiple recipes
     * with the same name exist.
     */
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

    /**
     * Displays all recipes in the cookbook.
     */
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

    /**
     * Searches for recipes by name in the cookbook.
     */
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

    /**
     * Searches for recipes containing a specific ingredient.
     */
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

    /**
     * Filters recipes in the cookbook by a specific category.
     */
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

    /**
     * Sorts recipes in the cookbook by preparation time.
     */
    private void sortRecipesByPreparationTime() {
        System.out.println("\n--- Sorter oppskrifter etter tilberedningstid ---");
        List<Recipe> sortedRecipes = cookbook.sortByPreperationTime();
        if (sortedRecipes.isEmpty()) {
            System.out.println("Ingen oppskrifter å vise.");
        } else {
            sortedRecipes.forEach(System.out::println);
        }
    }

    /**
     * Sorts recipes in the cookbook by difficulty level.
     */
    private void sortRecipesByDifficulty() {
        System.out.println("\n--- Sorter oppskrifter etter vanskelighetsgrad ---");
        List<Recipe> sortedRecipes = cookbook.sortByDifficulty();
        sortedRecipes.forEach(System.out::println);
    }

    private RecipeSearchManager recipeSearchManager;

    /**
     * Initiates a multi-category recipe search using the recipe search manager.
     */
    private void searchRecipes() {
        recipeSearchManager.searchRecipes();
    }

    /**
     * Suggests recipes based on the contents of the fridge and the desired
     * servings.
     */
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

    /**
     * Reads an integer input from the user with the provided prompt.
     *
     * @param prompt The prompt message to display to the user.
     * @return The integer input from the user.
     */
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

}
