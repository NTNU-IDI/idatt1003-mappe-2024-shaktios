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
        // Satt opp en gyldig ingrediensliste før hver test
        validIngredients = new ArrayList<>();
        validIngredients.add(new Grocery("Mel", 300, MeasuringUnit.GRAM, 7, 20.0));
        validIngredients.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 7, 3.0));
    }

    // Positiv test for konstruktøren
    @Test
    public void testValidRecipeCreation() {
        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift på pannekaker", "Bland og stek", validIngredients, 4);

        // Sjekk at feltene er satt riktig
        assertEquals("Pannekaker", recipe.getName());
        assertEquals("Enkel oppskrift på pannekaker", recipe.getDescription());
        assertEquals("Bland og stek", recipe.getInstructions());
        assertEquals(validIngredients, recipe.getIngredients());
        assertEquals(4, recipe.getServings());
    }

    // Negativ tester for konstruktøren
    @Test
    public void testRecipeCreationWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("", "Enkel oppskrift", "Bland og stek", validIngredients, 4);
        });
    }

    @Test
    public void testRecipeCreationWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", null, "Bland og stek", validIngredients, 4);
        });
    }

    @Test
    public void testRecipeCreationWithEmptyInstructions() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "", validIngredients, 4);
        });
    }

    @Test
    public void testRecipeCreationWithEmptyIngredientsList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", new ArrayList<>(), 4);
        });
    }

    @Test
    public void testRecipeCreationWithNullIngredientsList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", null, 4);
        });
    }

    @Test
    public void testRecipeCreationWithInvalidServings() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", validIngredients, -1);
        });
    }

    // Test for addIngredient-metoden
    @Test
    public void testAddIngredient() {
        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", validIngredients, 4);

        Grocery newIngredient = new Grocery("Melk", 200, MeasuringUnit.MILLILITER, 5, 15.0);
        recipe.addIngredient(newIngredient);

        // Bekreft at ingrediensen ble lagt til i listen
        assertTrue(recipe.getIngredients().contains(newIngredient), "Forventer at 'Melk' er lagt til i ingredienslisten");
    }

    @Test
    public void testAddNullIngredient() {
        Recipe recipe = new Recipe("Pannekaker", "Enkel oppskrift", "Bland og stek", validIngredients, 4);

        assertThrows(IllegalArgumentException.class, () -> {
            recipe.addIngredient(null);
        });
    }
}
