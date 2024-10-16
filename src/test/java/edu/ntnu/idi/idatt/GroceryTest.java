package edu.ntnu.idi.idatt;

// Importerer JUnit-modulene fra JUnit 5
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class GroceryTest {

  @Test
   
    public void testValidGrocery() {
    Grocery melk = new Grocery("Melk", 1.5, "liter", 10, 20.0); // Opprett melk med 10 dager til utløp

    // Tester om feltene i Grocery-objektet har riktig verdi
    assertEquals("Melk", melk.getName());
    assertEquals(1.5, melk.getAmount());
    assertEquals("liter", melk.getMeasuringUnit());
    assertEquals(20.0, melk.getPricePerUnit());

    // Test på antall dager til utløp, tillater en liten forskjell på +/- 1 dag
    int daysUntilExpiry = melk.getDaysUntilExpiry();
    assertTrue(daysUntilExpiry == 10 || daysUntilExpiry == 9, "Forventet 10 eller 9 dager til utløp, men var: " + daysUntilExpiry);
}


    @Test
    // Sjekker hva som skjer ved negative mengder
    public void testNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grocery("Melk", -1.0, "liter", 10, 20.0); // Opprett med negativ mengde
        });
    }

    @Test
    // Sjekker hva som skjer ved tomt navn
    public void testEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grocery("", 1.0, "liter", 10, 20.0); // Opprett med tomt navn
        });
    }

    @Test
    // Sjekker hva som skjer ved negativ verdi for dager til utløp
    public void testNegativeDaysUntilExpiryThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Grocery("Melk", 1.0, "liter", -5, 20.0); // Opprett med negativ dager til utløp
        });
    }
}
