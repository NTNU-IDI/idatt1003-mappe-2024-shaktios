package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        // Legg til nødvendige ingredienser i kjøleskapet
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0));
        fridge.addItem(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));

        // Opprett en oppskrift som krever de samme ingrediensene
        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients);

        assertTrue(recipeManager.hasIngredients(recipe), "Forventer at alle ingredienser er tilstrekkelige for oppskriften");
    }

    @Test
    public void testHasIngredientsWithMissingIngredient() {
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0)); // Bare mel, ingen egg

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 5, 20.0));
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0)); // Krever egg
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients);

        assertFalse(recipeManager.hasIngredients(recipe), "Forventer at det mangler en ingrediens for oppskriften");
    }

    @Test
    public void testHasIngredientsWithInsufficientQuantity() {
        fridge.addItem(new Grocery("Mel", 200, MeasuringUnit.GRAM, 7, 20.0)); // Mengde er for liten
        fridge.addItem(new Grocery("Egg", 4, MeasuringUnit.PIECE, 5, 3.0));

        List<Grocery> ingredients = new ArrayList<>();
        ingredients.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 5, 20.0)); // Krever 300 gram mel
        ingredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        Recipe recipe = new Recipe("Pannekaker", "Enkel pannekakeoppskrift", "Bland ingrediensene og stek", ingredients);

        assertFalse(recipeManager.hasIngredients(recipe), "Forventer at mengden av 'Mel' er for liten for oppskriften");
    }
}

