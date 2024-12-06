package edu.ntnu.idi.idatt.managers;

import java.util.List;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.helpers.CookbookInputHelper;
import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.FilterCriteria; 


public class RecipeSearchManager {
    private Cookbook cookbook;
    private CookbookInputHelper cookbookInputHelper;

    public RecipeSearchManager(Cookbook cookbook, CookbookInputHelper cookbookInputHelper) {
        this.cookbook = cookbook;
        this.cookbookInputHelper = cookbookInputHelper;
    }

    public void searchRecipes() {
        System.out.println("\n--- Søk etter oppskrifter ---");
        FilterCriteria criteria = new FilterCriteria();

        // Velg diettkategori
        selectDietCategory(criteria);

        // Velg vanskelighetsgrad
        selectDifficulty(criteria);

        // Velg maks tilberedningstid
        selectMaxPreparationTime(criteria);

        // Velg kategori
        selectCategory(criteria);

        // Velg opphavskjøkken
        selectCuisine(criteria);

        // Utfør filtrering og vis resultatene
        displayFilteredRecipes(criteria);
    }

    private void selectDietCategory(FilterCriteria criteria) {
        System.out.println("Tilgjengelige diettkategorier:");
        for (DietCategory category : DietCategory.values()) {
            System.out.println("- " + category.name() + ": " + category.getDescription());
        }
        String diet;
        do {
            diet = cookbookInputHelper.readString("Velg diettkategori (eller skriv Q for å hoppe over): ");
            if (diet.equalsIgnoreCase("Q")) break;
            try {
                criteria.setDietCategory(DietCategory.valueOf(diet.toUpperCase()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig diettkategori. Prøv igjen.");
            }
        } while (true);
    }

    private void selectDifficulty(FilterCriteria criteria) {
        System.out.println("Tilgjengelige vanskelighetsgrader:");
        Difficulty.displayDifficulties();
        String diff;
        do {
            diff = cookbookInputHelper.readString("Velg vanskelighetsgrad (eller skriv Q for å hoppe over): ");
            if (diff.equalsIgnoreCase("Q")) break;
            try {
                criteria.setDifficulty(Difficulty.valueOf(diff.toUpperCase()));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig vanskelighetsgrad. Prøv igjen.");
            }
        } while (true);
    }

    private void selectMaxPreparationTime(FilterCriteria criteria) {
        while (true) {
            String prepTime = cookbookInputHelper.readString("Maks tilberedningstid i minutter (eller skriv Q for å hoppe over): ");
            if (prepTime.equalsIgnoreCase("Q")) break;
            try {
                int time = Integer.parseInt(prepTime);
                if (time >= 0) {
                    criteria.setMaxPreparationTime(time);
                    break;
                } else {
                    System.out.println("Tid må være et positivt tall. Prøv igjen.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig tid. Vennligst skriv inn et heltall.");
            }
        }
    }

    private void selectCategory(FilterCriteria criteria) {
        System.out.println("Tilgjengelige kategorier:");
        List<String> categories = cookbook.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("Ingen kategorier tilgjengelige. Hopper over.");
        } else {
            categories.forEach(System.out::println);
            String category = cookbookInputHelper.readString("Velg kategori (eller skriv Q for å hoppe over): ");
            if (!category.equalsIgnoreCase("Q")) {
                criteria.setCategory(category);
            }
        }
    }

    private void selectCuisine(FilterCriteria criteria) {
        String cuisine = cookbookInputHelper.readString("Opphavskjøkken (eller skriv Q for å hoppe over): ");
        if (!cuisine.equalsIgnoreCase("Q")) {
            criteria.setCuisine(cuisine);
        }
    }

    private void displayFilteredRecipes(FilterCriteria criteria) {
        try {
            List<Recipe> filteredRecipes = cookbook.filterRecipes(criteria);
            if (filteredRecipes.isEmpty()) {
                System.out.println("Fant ingen oppskrifter som matcher kriteriene.");
            } else {
                System.out.println("Oppskrifter som matcher:");
                filteredRecipes.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("En feil oppstod under filtrering: " + e.getMessage());
        }
    }
}