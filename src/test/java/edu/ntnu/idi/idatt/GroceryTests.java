package edu.ntnu.idi.idatt;

// Importerer JUnit-modulene fra JUnit 5
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class GroceryTests {

    @Test
    public void testValidGrocery(){
        Date date = new Date(); // oppretter dagens dato
        Grocery melk = new Grocery("Melk", 1.5, "liter", date, 20.0); // setter opp melk som en "Grocery"

        // tester om feltene i Grocery-objektet har riktig verdi
        assertEquals("Melk", melk.getName());
        assertEquals(1.5, melk.getAmount());
        assertEquals("liter", melk.getMeasuringUnit());
        assertEquals(20.0, melk.getPricePerUnit());
    }

    @Test
    // Sjekker hva som skjer ved negative mengder
    public void testNegativeAmountThrowsException() {
        Date date = new Date();
        assertThrows(IllegalArgumentException.class, () -> {
            new Grocery("Melk", -1.0, "liter", date, 20.0);
        });
    }

    @Test
    // Sjekker hva som skjer ved tomt navn
    public void testEmptyNameThrowsException() {
        Date date = new Date();
        assertThrows(IllegalArgumentException.class, () -> {
            new Grocery("", 1.0, "liter", date, 20.0);
        });
    }
}
