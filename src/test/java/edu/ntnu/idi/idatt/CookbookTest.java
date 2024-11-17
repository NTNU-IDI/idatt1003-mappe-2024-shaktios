package edu.ntnu.idi.idatt;

import java.util.ArrayList;
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

        // Opprett eksempler på oppskrifter med porsjonsstørrelse (servings)
        List<Grocery> pannekakerIngredienser = new ArrayList<>();
        pannekakerIngredienser.add(new Grocery("Mel", 200, MeasuringUnit.GRAM, 5, 10.0));
        pannekakerIngredienser.add(new Grocery("Egg", 3, MeasuringUnit.PIECE, 7, 3.0));
        pannekaker = new Recipe("Pannekaker", "Enkle pannekaker", "Bland og stek", pannekakerIngredienser, 4);

        List<Grocery> omelettIngredienser = new ArrayList<>();
        omelettIngredienser.add(new Grocery("Egg", 2, MeasuringUnit.PIECE, 5, 3.0));
        omelettIngredienser.add(new Grocery("Melk", 100, MeasuringUnit.MILLILITER, 3, 5.0));
        omelett = new Recipe("Omelett", "Enkel omelett", "Visp og stek", omelettIngredienser, 2);
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


}
