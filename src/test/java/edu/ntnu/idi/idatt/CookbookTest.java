package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CookbookTest {

    private Cookbook cookbook;
    private Recipe pannekaker;
    private Recipe omelett;

    @BeforeEach
    public void setUp() {
        cookbook = new Cookbook();

        // Opprett eksempler på oppskrifter med de nye feltene
        List<Grocery> pannekakerIngredienser = new ArrayList<>();
        pannekakerIngredienser.add(new Grocery("Mel", 200, MeasuringUnit.GRAM, 5, 10.0));
        pannekakerIngredienser.add(new Grocery("Egg", 3, MeasuringUnit.PIECE, 7, 3.0));
        pannekaker = new Recipe("Pannekaker", "Enkle pannekaker", "Bland og stek", 
                                pannekakerIngredienser, 4, "Dessert", 20, 
                                DietCategory.VEGETARIAN, Difficulty.EASY, "Norsk");

        List<Grocery> omelettIngredienser = new ArrayList<>();
        omelettIngredienser.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        omelettIngredienser.add(new Grocery("Melk", 100, MeasuringUnit.MILLILITER, 3, 5.0));
        omelett = new Recipe("Omelett", "Enkel omelett", "Visp og stek", 
                             omelettIngredienser, 2, "Frokost", 10, 
                             DietCategory.VEGETARIAN, Difficulty.EASY, "Fransk");
    }

    @Test
    public void testAddRecipe() {
        cookbook.addRecipe(pannekaker);
        assertTrue(cookbook.getAllRecipes().contains(pannekaker), "Pannekaker bør være lagt til i kokeboken.");
    }

    @Test
    public void testRemoveRecipe() {
        cookbook.addRecipe(pannekaker);
        cookbook.removeRecipe(pannekaker);
        assertFalse(cookbook.getAllRecipes().contains(pannekaker), "Pannekaker bør være fjernet fra kokeboken.");
    }

    @Test
    public void testSearchByName() {
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        List<Recipe> results = cookbook.searchByName("Pannekaker");
        assertEquals(1, results.size(), "Bør kun finne én oppskrift med navnet 'Pannekaker'.");
        assertEquals("Pannekaker", results.get(0).getName(), "Oppskriften bør ha navnet 'Pannekaker'.");
    }

    @Test
    public void testSearchByIngredient() {
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        List<Recipe> results = cookbook.searchByIngredient("Egg");
        assertEquals(2, results.size(), "Bør finne to oppskrifter som inneholder 'Egg'.");
    }

    @Test
    public void testFilterByCategory() {
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        List<Recipe> results = cookbook.filterByCategory("Dessert");
        assertEquals(1, results.size(), "Bør kun finne én oppskrift i kategorien 'Dessert'.");
        assertEquals("Pannekaker", results.get(0).getName(), "Oppskriften bør ha navnet 'Pannekaker'.");
    }

    @Test
    public void testSortByPreparationTime() {
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        List<Recipe> results = cookbook.sortByPreperationTime();
        assertEquals("Omelett", results.get(0).getName(), "Omelett bør være først basert på tilberedningstid.");
        assertEquals("Pannekaker", results.get(1).getName(), "Pannekaker bør være sist basert på tilberedningstid.");
    }

    @Test
    public void testsortByDifficulty() {
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        List<Recipe> results = cookbook.sortByDifficulty();
        assertEquals(2, results.size(), "Bør finne to oppskrifter sortert etter vanskelighetsgrad.");
        assertEquals("Pannekaker", results.get(0).getName(), "Pannekaker bør være først basert på vanskelighetsgrad.");
    }

    @Test
    public void testFilterByDiet() {
        cookbook.addRecipe(pannekaker);
        cookbook.addRecipe(omelett);
        List<Recipe> results = cookbook.filterByDiet(DietCategory.VEGETARIAN);
        assertEquals(2, results.size(), "Bør finne to vegetariske oppskrifter.");
    }
    @Test
    public void testFilterRecipes() {
        // Oppretter en Cookbook med noen oppskrifter
        Cookbook cookbook = new Cookbook();

        // Oppretter oppskrifter med alle påkrevde felter
        Recipe recipe1 = new Recipe(
            "Vegansk Salat", 
            "En sunn salat for veganere", 
            "Bland alle ingrediensene sammen og server.", 
            Arrays.asList(
                new Grocery("salat", 1.0, MeasuringUnit.PIECE, 5, 10.0), 
                new Grocery("tomat", 2.0, MeasuringUnit.PIECE, 10, 5.0), 
                new Grocery("olje", 0.1, MeasuringUnit.LITER, 30, 20.0)
            ), 
            2, // Antall personer
            "Forrett", // Kategori
            10, // Forberedelsestid
            DietCategory.VEGAN, 
            Difficulty.EASY, 
            "Norsk" // Opprinnelse
        );

        Recipe recipe2 = new Recipe(
            "Vegetarisk Pizza", 
            "En deilig pizza for vegetarianere", 
            "Lag deigen, legg på saus og ost, og stek.", 
            Arrays.asList(
                new Grocery("deig", 1.0, MeasuringUnit.KILOGRAM, 20, 15.0), 
                new Grocery("ost", 0.5, MeasuringUnit.KILOGRAM, 40, 40.0), 
                new Grocery("tomatsaus", 0.3, MeasuringUnit.LITER, 15, 10.0)
            ), 
            4, // Antall personer
            "Hovedrett", // Kategori
            30, // Forberedelsestid
            DietCategory.VEGETARIAN, 
            Difficulty.MEDIUM, 
            "Italiensk" // Opprinnelse
        );

        Recipe recipe3 = new Recipe(
            "Fiskesuppe", 
            "En klassisk fiskesuppe", 
            "Kok opp fløte, tilsett fisk og grønnsaker, og la det småkoke.", 
            Arrays.asList(
                new Grocery("fisk", 0.7, MeasuringUnit.KILOGRAM, 100, 50.0), 
                new Grocery("fløte", 0.5, MeasuringUnit.LITER, 50, 30.0), 
                new Grocery("grønnsaker", 1.0, MeasuringUnit.KILOGRAM, 30, 20.0)
            ), 
            3, // Antall personer
            "Hovedrett", // Kategori
            40, // Forberedelsestid
            DietCategory.PESCETARIAN, 
            Difficulty.HARD, 
            "Norsk" // Opprinnelse
        );

        cookbook.addRecipe(recipe1);
        cookbook.addRecipe(recipe2);
        cookbook.addRecipe(recipe3);

        // Opprett FilterCriteria
        FilterCriteria criteria = new FilterCriteria();
        criteria.setDietCategory(DietCategory.VEGETARIAN);
        criteria.setDifficulty(Difficulty.MEDIUM);
        criteria.setMaxPreparationTime(30);
        criteria.setIngredients(Arrays.asList("deig", "ost"));

        // Utfør filteringen
        List<Recipe> filteredRecipes = cookbook.filterRecipes(criteria);

        // Verifiser resultatet
        assertEquals(1, filteredRecipes.size(), "Det burde bare være én oppskrift som matcher kriteriene");
        assertEquals("Vegetarisk Pizza", filteredRecipes.get(0).getName(), "Den matchende oppskriften burde være 'Vegetarisk Pizza'");

        // Test uten noen kriterier
        FilterCriteria emptyCriteria = new FilterCriteria();
        List<Recipe> allRecipes = cookbook.filterRecipes(emptyCriteria);
        assertEquals(3, allRecipes.size(), "Alle oppskrifter burde returneres når ingen kriterier er satt.");
    }


}
