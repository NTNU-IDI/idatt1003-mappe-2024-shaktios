package edu.ntnu.idi.idatt.managers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.enums.MeasuringUnit;
import edu.ntnu.idi.idatt.helpers.CookbookInputHelper;
import edu.ntnu.idi.idatt.models.Cookbook;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;
import edu.ntnu.idi.idatt.utils.FilterCriteria;

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

    // Opprett ingredienser
    List<Grocery> pastaIngredients = List.of(
        new Grocery("Pasta", 200, MeasuringUnit.GRAM, 5, 10.0)
    );
    List<Grocery> pizzaIngredients = List.of(
        new Grocery("Deig", 500, MeasuringUnit.GRAM, 5, 20.0),
        new Grocery("Ost", 200, MeasuringUnit.GRAM, 10, 30.0)
    );

    // Legg til noen testoppskrifter i Cookbook
    Recipe recipe1 = new Recipe("Pasta", "Enkel pasta", "Kok pasta", pastaIngredients, 2, "Hovedrett", 10, DietCategory.VEGETARIAN, Difficulty.EASY, "Italiensk");
    Recipe recipe2 = new Recipe("Pizza", "Hjemmelaget pizza", "Lag deig", pizzaIngredients, 4, "Hovedrett", 30, DietCategory.NONE, Difficulty.MEDIUM, "Italiensk");
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
