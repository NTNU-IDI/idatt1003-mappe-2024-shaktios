package edu.ntnu.idi.idatt.managers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.enums.MeasuringUnit;
import edu.ntnu.idi.idatt.models.Fridge;
import edu.ntnu.idi.idatt.models.Grocery;
import edu.ntnu.idi.idatt.models.Recipe;

public class RecipeManagerTest {

    private Fridge fridge;
    private RecipeManager recipeManager;

    @BeforeEach
    public void setUp() {
        fridge = new Fridge();
        recipeManager = new RecipeManager(fridge);
    }

    @Test
    public void testHasIngredientsWithSufficientIngredients() {
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0));
        fridge.addItem(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", 
                                   ingredients, 2, "Dessert", 20, 
                                   DietCategory.VEGETARIAN, Difficulty.EASY, "Norsk");

        assertTrue(recipeManager.hasIngredients(recipe, 2), "Forventer at alle ingredienser er tilstrekkelige for 2 porsjoner");
    }

    @Test
    public void testHasIngredientsWithMissingIngredient() {
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0)); // Bare mel, ingen egg

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0)); // Krever egg
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", 
                                   ingredients, 2, "Dessert", 20, 
                                   DietCategory.VEGETARIAN, Difficulty.EASY, "Norsk");

        assertFalse(recipeManager.hasIngredients(recipe, 2), "Forventer at det mangler en ingrediens for oppskriften for 2 porsjoner");
    }

    @Test
    public void testHasIngredientsWithInsufficientQuantity() {
        fridge.addItem(new Grocery("Mel", 200, MeasuringUnit.GRAM, 7, 20.0)); // Mengde er for liten
        fridge.addItem(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0)); // Krever 250 gram mel
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", 
                                   ingredients, 2, "Dessert", 20, 
                                   DietCategory.VEGETARIAN, Difficulty.EASY, "Norsk");

        assertFalse(recipeManager.hasIngredients(recipe, 2), "Forventer at mengden av 'Mel' er for liten for oppskriften for 2 porsjoner");
    }

    @Test
    public void testHasIngredientsWithMissingItems() {
        fridge.addItem(new Grocery("Mel", 300, MeasuringUnit.GRAM, 5, 20.0)); // Kun mel, ikke egg

        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", 
                                   List.of(
                                       new Grocery("Mel", 300, MeasuringUnit.GRAM, 0, 0),
                                       new Grocery("Egg", 2, MeasuringUnit.PIECE, 0, 0)
                                   ), 
                                   4, "Dessert", 20, 
                                   DietCategory.VEGETARIAN, Difficulty.EASY, "Norsk");

        assertFalse(recipeManager.hasIngredients(recipe, 4), "Forventer at det mangler ingredienser (egg)");
    }
}
