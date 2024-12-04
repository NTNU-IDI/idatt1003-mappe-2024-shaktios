package edu.ntnu.idi.idatt;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Fridge fridge;
    private Cookbook cookbook;
    private Scanner scanner;

    public Main() {
        this.fridge = new Fridge();
        this.cookbook = new Cookbook();
        this.scanner = new Scanner(System.in);
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
    

    private void addGrocery() {
        System.out.println("\n--- Legg til vare ---");
        String name = readString("Navn: ");
        MeasuringUnit unit = readMeasuringUnit(); // Henter en gyldig måleenhet
        double amount = readDouble("Mengde: ");
        int days = readInt("Dager til utløp: ");
        double price = readDouble("Pris per enhet: ");
        fridge.addItem(new Grocery(name, amount, unit, days, price));
    }
    

    private void removeGrocery() {
        System.out.println("\n--- Fjern vare ---");
        String name = readString("Navn: ");
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
        }
    }
    

    private void searchGrocery() {
        System.out.println("\n--- Søk etter vare ---");
        String name = readString("Navn: ");
        Grocery item = fridge.searchItem(name);
        if (item != null) {
            System.out.println("Fant varen: " + item);
        } else {
            System.out.println("Fant ikke varen.");
        }
    }

    private void displayExpiringSoon() {
        int days = readInt("Vis varer som går ut på dato innen (antall dager): ");
        fridge.displayExpiringSoon(days);
    }

    private void displayTotalValue() {
        double totalValue = fridge.calculateTotalValue();
        System.out.println("Den totale verdien av varer i kjøleskapet er: " + totalValue + " NOK");
    }

    private void removeItemByAmount() {
        String name = readString("Navn på varen: ");
        double amount = readDouble("Mengde som skal fjernes: ");
        fridge.removeItemByAmount(name, amount);
    }

    private void displaySortedItemsByName() {
        List<Grocery> sortedItems = fridge.getSortedItemsByName();
        System.out.println("Varer sortert etter navn:");
        sortedItems.forEach(System.out::println);
    }

    private void sortItemsByExpiry() {
        String name = readString("Navn på varen som skal sorteres etter utløpsdato: ");
        List<Grocery> sortedItems = fridge.getSortedItemsByExpiry(name);
        System.out.println("Varer sortert etter utløpsdato:");
        sortedItems.forEach(System.out::println);
    }


    private void displayExpiredItems() {
        System.out.println("\n--- Viser utgåtte varer ---");
    
        List<Grocery> items = fridge.getItems();
    
        // Filtrer ut utgåtte varer
        List<Grocery> expiredItems = new ArrayList<>(); 
        for (Grocery item : items) {
            if (item.getDaysUntilExpiry() < 0) {
                expiredItems.add(item);
            }
        }
    
        // Sjekk om det finnes utgåtte varer
        if (expiredItems.isEmpty()) {
            System.out.println("Ingen varer har gått ut på dato.");
        } else {
            // Print ut alle utgåtte varer
            System.out.println("Følgende varer har gått ut på dato:");
            for (Grocery expiredItem : expiredItems) {
                System.out.println(expiredItem); 
            }
        }
    }

    private void removeAllExpiredItems() {
        System.out.println("Fjerner alle utgåtte varer...");
    
        // Hent alle varer fra kjøleskapet
        List<Grocery> items = fridge.getItems();
    
        // Filtrer ut utgåtte varer
        List<Grocery> expiredItems = new ArrayList<>();
        for (Grocery item : items) {
            if (item.getDaysUntilExpiry() < 0) {
                expiredItems.add(item);
            }
        }
    
        // Sjekk om det finnes utgåtte varer
        if (expiredItems.isEmpty()) {
            System.out.println("Ingen varer har gått ut på dato.");
        } else {
            // Fjern alle utgåtte varer fra kjøleskapet
            items.removeAll(expiredItems);
            System.out.println("Følgende utgåtte varer har blitt fjernet:");
            for (Grocery expiredItem : expiredItems) {
                System.out.println(expiredItem);
            }
        }
    }


    private void displayExpiredItemsAndTotalValue() {
        System.out.println("Viser alle utgåtte varer og totalverdien deres...");
    
        // Hent alle varer fra kjøleskapet
        List<Grocery> items = fridge.getItems();
    
        // Filtrer ut utgåtte varer
        List<Grocery> expiredItems = new ArrayList<>();
        double totalValue = 0; // Deklarer og initialiser totalValue
        for (Grocery item : items) {
            if (item.getDaysUntilExpiry() < 0) {
                expiredItems.add(item);
                totalValue += item.getAmount() * item.getPricePerUnit(); // Beregn totalverdi
            }
        }
    
        // Sjekk om det finnes utgåtte varer
        if (expiredItems.isEmpty()) {
            System.out.println("Ingen varer har gått ut på dato.");
        } else {
            System.out.println("Følgende varer har gått ut på dato:");
            for (Grocery expiredItem : expiredItems) {
                System.out.println(expiredItem);
            }
            System.out.printf("Den totale verdien av utgåtte varer er: " + totalValue + " NOK");
        }
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
    


    private void addRecipe() {
        System.out.println("\n--- Legg til oppskrift ---");
    
        String name = readString("Navn: ");
        String description = readString("Beskrivelse: ");
        String instructions = readString("Instruksjoner: ");
        int prepTime = readInt("Tilberedningstid (minutter): ");
        int servings = readServings();
        String category = readCategory();
        String cuisine = readCuisine();
    
        DietCategory dietCategory = readDietCategory();
        Difficulty difficulty = readDifficulty();
    
        List<Grocery> ingredients = new ArrayList<>();
        while (true) {
            System.out.println("\nLegg til en ingrediens (eller skriv 'Q' for å avslutte):");
            String ingredientName = readString("Ingrediensnavn: ");
            if (ingredientName.equalsIgnoreCase("Q")) {
                if (ingredients.isEmpty()) {
                    System.out.println("Du må legge til minst én ingrediens før du avslutter.");
                    continue;
                }
                break;
            }
    
            MeasuringUnit unit = readMeasuringUnit();
            double amount = readDouble("Mengde: ");
            
    
            int daysUntilExpiry = 0;
            double pricePerUnit = 0;
    
            ingredients.add(new Grocery(ingredientName, amount, unit, daysUntilExpiry, pricePerUnit));
        }
    
        // Opprett oppskriften og legg den til i kokeboken
        Recipe recipe = new Recipe(name, description, instructions, ingredients, servings, category, prepTime, dietCategory, difficulty, cuisine);
        cookbook.addRecipe(recipe);
        System.out.println("Oppskrift lagt til: " + name);
    }
    
    
    
    
    
    
    private void removeRecipe() {
        System.out.println("\n--- Fjern oppskrift ---");
        String name = readString("Navn: ");
        List<Recipe> foundRecipes = cookbook.searchByName(name);
    
        if (foundRecipes.isEmpty()) {
            System.out.println("Fant ingen oppskrifter med navnet: " + name);
        } else if (foundRecipes.size() == 1) {
            cookbook.removeRecipe(foundRecipes.get(0)); // Fjern eneste treff
        } else {
            System.out.println("Flere oppskrifter ble funnet med navnet: " + name);
            for (int i = 0; i < foundRecipes.size(); i++) {
                System.out.println((i + 1) + ": " + foundRecipes.get(i));
            }
    
            int choice = readInt("Velg hvilken oppskrift som skal fjernes (tast nummer): ");
            if (choice > 0 && choice <= foundRecipes.size()) {
                cookbook.removeRecipe(foundRecipes.get(choice - 1)); // Fjern valgt oppskrift
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
        String name = readString("Navn på oppskrift: ");
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
        String ingredient = readString("Navn på ingrediens: ");
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
        String category = readString("Skriv inn en kategori for å søke: ");
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
        sortedRecipes.forEach(System.out::println);
    }
    
    private void sortRecipesByDifficulty() {
        System.out.println("\n--- Sorter oppskrifter etter vanskelighetsgrad ---");
        List<Recipe> sortedRecipes = cookbook.sortByDifficulty();
        sortedRecipes.forEach(System.out::println);
    }
    
    

    private void searchRecipes() {
        System.out.println("\n--- Søk etter oppskrifter ---");
        FilterCriteria criteria = new FilterCriteria();
    
        // Velg diettkategori
        System.out.println("Tilgjengelige diettkategorier:");
        for (DietCategory category : DietCategory.values()) {
            System.out.println("- " + category.name() + ": " + category.getDescription());
        }
        String diet = readString("Velg diettkategori (eller skriv Q for å hoppe over): ");
        if (!diet.equalsIgnoreCase("Q")) {
            try {
                criteria.setDietCategory(DietCategory.valueOf(diet.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig diettkategori. Hopper over.");
            }
        }
    
        // Velg vanskelighetsgrad
        System.out.println("Tilgjengelige vanskelighetsgrader:");
        Difficulty.displayDifficulties();
        String diff = readString("Velg vanskelighetsgrad (eller skriv Q for å hoppe over) ");
        if (!diff.equalsIgnoreCase("Q")) {
            try {
                criteria.setDifficulty(Difficulty.valueOf(diff.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig vanskelighetsgrad. Hopper over.");
            }
        }
    
        // Velg maks tilberedningstid
        String prepTime = readString("Maks tilberedningstid i minutter (eller skriv Q for å hoppe over): ");
        if (!prepTime.equalsIgnoreCase("Q")) {
            try {
                criteria.setMaxPreparationTime(Integer.parseInt(prepTime));
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig tid. Hopper over.");
            }
        }
    
        // Velg kategori
        System.out.println("Tilgjengelige kategorier:");
        List<String> categories = cookbook.getAllCategories();
        categories.forEach(System.out::println);
        String category = readString("Velg kategori (eller skriv Q for å hoppe over): ");
        if (!category.equalsIgnoreCase("Q")) {
            criteria.setCategory(category);
        }
    
        // Velg opphavskjøkken
        String cuisine = readString("Opphavskjøkken (eller skriv Q for å hoppe over): ");
        if (!cuisine.equalsIgnoreCase("Q")) {
            criteria.setCuisine(cuisine);
        }
    
        // Utfører filtrering
        List<Recipe> filteredRecipes = cookbook.filterRecipes(criteria);
        if (filteredRecipes.isEmpty()) {
            System.out.println("Fant ingen oppskrifter som matcher kriteriene.");
        } else {
            System.out.println("Oppskrifter som matcher:");
            filteredRecipes.forEach(System.out::println);
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
    
    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Tømmer linjeskift fra bufferen
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Vennligst skriv inn et tall.");
                scanner.nextLine(); // Tømmer ugyldig input fra bufferen
            }
        }
    }
    
    private MeasuringUnit readMeasuringUnit() {
        while (true) {
            MeasuringUnit.displayMeasuringUnits(); // Viser alternativer
            String unit = readString("Velg en måleenhet: ");
            try {
                return MeasuringUnit.valueOf(unit.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig måleenhet. Prøv igjen.");
            }
        }
    }

    private DietCategory readDietCategory() {
        while (true) {
            try {
                // Vis alle tilgjengelige alternativer
                System.out.println("Tilgjengelige diettkategorier:");
                for (DietCategory category : DietCategory.values()) {
                    System.out.println("- " + category.name() + ": " + category.getDescription());
                }
                String input = readString("Velg en diettkategori: ");
                return DietCategory.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig diettkategori. Prøv igjen.");
            }
        }
    }

    private Difficulty readDifficulty() {
        while (true) {
            try {
                // Vis alle tilgjengelige alternativer
                Difficulty.displayDifficulties();
                String input = readString("Velg en vanskelighetsgrad: ");
                return Difficulty.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig vanskelighetsgrad. Prøv igjen.");
            }
        }
    }

    private int readServings() {
        while (true) {
            try {
                int servings = readInt("Antall porsjoner (må være > 0): ");
                if (servings > 0) {
                    return servings;
                } else {
                    System.out.println("Antall porsjoner må være større enn 0. Prøv igjen.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig input. Prøv igjen.");
            }
        }
    }

    private String readCategory() {
        while (true) {
            String category = readString("Kategori (f.eks. Hovedrett, Forrett, Dessert): ");
            if (!category.isEmpty()) {
                return category;
            } else {
                System.out.println("Kategori kan ikke være tom. Prøv igjen.");
            }
        }
    }

    private String readCuisine() {
        while (true) {
            String cuisine = readString("Opphavskjøkken (f.eks. Indisk, Italiensk, Norsk): ");
            if (!cuisine.isEmpty()) {
                return cuisine;
            } else {
                System.out.println("Opphavskjøkken kan ikke være tomt. Prøv igjen.");
            }
        }
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
    
    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine(); // Les hele linjen
    }
    

    public static void main(String[] args) {
        new Main().start();
    }
}
