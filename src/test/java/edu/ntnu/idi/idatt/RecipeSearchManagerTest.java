package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSearchManagerTest {

    private Cookbook cookbook;
    private CookbookInputHelper cookbookInputHelper;
    private RecipeSearchManager recipeSearchManager;

    @BeforeEach
    void setUp() {
        // Opprett reelle instanser av Cookbook og CookbookInputHelper
        cookbook = new Cookbook();
        cookbookInputHelper = new CookbookInputHelper();
        recipeSearchManager = new RecipeSearchManager(cookbook, cookbookInputHelper);

        // Legg til noen testoppskrifter i Cookbook
        Recipe recipe1 = new Recipe("Pasta", "Enkel pasta", "Kok pasta", List.of(), 2, "Hovedrett", 10, DietCategory.VEGETARIAN, Difficulty.EASY, "Italiensk");
        Recipe recipe2 = new Recipe("Pizza", "Hjemmelaget pizza", "Lag deig", List.of(), 4, "Hovedrett", 30, DietCategory.NONE, Difficulty.MEDIUM, "Italiensk");
        cookbook.addRecipe(recipe1);
        cookbook.addRecipe(recipe2);
    }

    @Test
    void testSearchRecipesWithDietCategory() {
        // Lag en ny FilterCriteria og sett en diettkategori
        FilterCriteria criteria = new FilterCriteria();
        criteria.setDietCategory(DietCategory.VEGETARIAN);

        // Utfør søk basert på kriteriene
        List<Recipe> results = cookbook.filterRecipes(criteria);

        // Verifiser resultatene
        assertEquals(1, results.size(), "Forventet én oppskrift som matcher diettkategori.");
        assertEquals("Pasta", results.get(0).getName(), "Oppskriften som ble funnet er feil.");
    }

    @Test
    void testSearchRecipesWithDifficulty() {
        // Lag en ny FilterCriteria og sett vanskelighetsgrad
        FilterCriteria criteria = new FilterCriteria();
        criteria.setDifficulty(Difficulty.EASY);

        // Utfør søk basert på kriteriene
        List<Recipe> results = cookbook.filterRecipes(criteria);

        // Verifiser resultatene
        assertEquals(1, results.size(), "Forventet én oppskrift som matcher vanskelighetsgrad.");
        assertEquals("Pasta", results.get(0).getName(), "Oppskriften som ble funnet er feil.");
    }

    @Test
    void testSearchRecipesWithMaxPreparationTime() {
        // Lag en ny FilterCriteria og sett maks tilberedningstid
        FilterCriteria criteria = new FilterCriteria();
        criteria.setMaxPreparationTime(15);

        // Utfør søk basert på kriteriene
        List<Recipe> results = cookbook.filterRecipes(criteria);

        // Verifiser resultatene
        assertEquals(1, results.size(), "Forventet én oppskrift med kort tilberedningstid.");
        assertEquals("Pasta", results.get(0).getName(), "Oppskriften som ble funnet er feil.");
    }
}
