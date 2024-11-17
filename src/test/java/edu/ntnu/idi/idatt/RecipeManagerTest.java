package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        //legger til nødvendige ting i kjøleskapet 
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0));
        fridge.addItem(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));

        
        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients, 2);

        assertTrue(recipeManager.hasIngredients(recipe, 2), "Forventer at alle ingredienser er tilstrekkelige for 2 porsjoner");
 }

    @Test
    public void testHasIngredientsWithMissingIngredient() {
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0)); // Bare mel, ingen egg

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0)); // Krever egg
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients, 2);

        assertFalse(recipeManager.hasIngredients(recipe, 2), "Forventer at det mangler en ingrediens for oppskriften for 2 porsjoner");
    }

    @Test
    public void testHasIngredientsWithInsufficientQuantity() {
        fridge.addItem(new Grocery("Mel", 200, MeasuringUnit.GRAM, 7, 20.0)); // Mengde er for liten
        fridge.addItem(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0)); // Krever 250 gram mel
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients, 2);

        assertFalse(recipeManager.hasIngredients(recipe, 2), "Forventer at mengden av 'Mel' er for liten for oppskriften for 2 porsjoner");
    }

    @Test
    public void testHasIngredientsForDifferentServings() {
        fridge.addItem(new Grocery("Mel", 1000, MeasuringUnit.GRAM, 7, 20.0));
        fridge.addItem(new Grocery("Egg", 10, MeasuringUnit.PIECE, 5, 3.0));

        // Opprett en oppskrift for 2 porsjoner
        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 250, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients, 2);

        // Test for 4 porsjoner (skalering av ingredienser)
        assertTrue(recipeManager.hasIngredients(recipe, 4), "Forventer at alle ingredienser er tilstrekkelige for 4 porsjoner");

        // Test for 8 porsjoner (skalering av ingredienser)
        assertFalse(recipeManager.hasIngredients(recipe, 8), "Forventer at det mangler ingredienser for 8 porsjoner");
    }
}
