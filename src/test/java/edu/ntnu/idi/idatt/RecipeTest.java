package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecipeTest {

    private List<Grocery> validIngredients;

    @BeforeEach
    public void setUp() {
        // Sett opp en gyldig ingrediensliste før hver test
        validIngredients = new ArrayList<>();
        validIngredients.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 7, 20.0));
        validIngredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 7, 3.0));
    }

    // Positiv test for konstruktøren
    @Test
    public void testValidRecipeCreation() {
        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift på pannekaker", "Bland og stek", validIngredients);

        // Sjekk at feltene er satt riktig
        assertEquals("Pannekaker", recipe.getName());
        assertEquals("Enkel oppskrift på pannekaker", recipe.getDescription());
        assertEquals("Bland og stek", recipe.getInstructions());
        assertEquals(validIngredients, recipe.getIngredients());
    }

    // Negativ tester for konstruktøren
    @Test
    public void testRecipeCreationWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("", "Enkel oppskrift", "Bland og stek", validIngredients);
        });
    }

    @Test
    public void testRecipeCreationWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", null, "Bland og stek", validIngredients);
        });
    }

    @Test
    public void testRecipeCreationWithEmptyInstructions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "", validIngredients);
        });
    }

    @Test
    public void testRecipeCreationWithEmptyIngredientsList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", new ArrayList<>());
        });
    }

    @Test
    public void testRecipeCreationWithNullIngredientsList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", null);
        });
    }

    // Test for addIngredient-metoden
    @Test
    public void testAddIngredient() {
        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", validIngredients);

        Grocery newIngredient = new Grocery("Melk", 200, MeasuringUnit.MILLILITER, 5, 15.0);
        recipe.addIngredient(newIngredient);

        // Bekreft at ingrediensen ble lagt til i listen
        assertTrue(recipe.getIngredients().contains(newIngredient), "Forventer at 'Melk' er lagt til i ingredienslisten");
    }

    @Test
    public void testAddNullIngredient() {
        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", validIngredients);

        assertThrows(IllegalArgumentException.class, () -> {
            recipe.addIngredient(null);
        });
    }
}
