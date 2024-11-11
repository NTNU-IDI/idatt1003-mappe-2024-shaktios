package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FridgeTest {

    @Test
    public void testAddItem() {
        Fridge fridge = new Fridge();
        Grocery melk = new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0);
        fridge.addItem(melk);
        
        // Sjekk at varen er lagt til
        assertTrue(fridge.getItems().contains(melk), "Forventer at 'Melk' er lagt til i kjøleskapet");
    }

    @Test
    public void testRemoveItem() {
        Fridge fridge = new Fridge();
        Grocery melk = new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0);
        fridge.addItem(melk);
        
        fridge.removeItem(melk);
        
        // Sjekk at varen er fjernet
        assertFalse(fridge.getItems().contains(melk), "Forventer at 'Melk' er fjernet fra kjøleskapet");
    }

    @Test
    public void testSearchItem() {
        Fridge fridge = new Fridge();
        Grocery melk = new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0);
        fridge.addItem(melk);

        Grocery foundItem = fridge.searchItem("Melk");

        // Sjekk at søket returnerer riktig vare
        assertNotNull(foundItem, "Forventer at 'Melk' ble funnet i kjøleskapet");
        assertEquals("Melk", foundItem.getName());
    }

    @Test
    public void testCalculateTotalValue() {
        Fridge fridge = new Fridge();
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0));
        fridge.addItem(new Grocery("Egg", 6, MeasuringUnit.PIECE, 7, 3.0));
        
        double totalValue = fridge.calculateTotalValue();

        // Forventer totalverdien basert på pris per enhet og mengde
        assertEquals(20.0 + (6 * 3.0), totalValue, 0.01, "Forventer riktig totalverdi i kjøleskapet");
    }

    @Test
    public void testDisplayExpiringSoon() {
        Fridge fridge = new Fridge();
    
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 2, 20.0));  // 2 dager til utløp
        fridge.addItem(new Grocery("Egg", 6, MeasuringUnit.PIECE, 5, 3.0));      // 5 dager til utløp
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 8, 10.0));    // 8 dager til utløp
    
        System.out.println("Testing varer som går ut på dato innen 3 dager:");
        fridge.displayExpiringSoon(3);  // Forventer at kun "Melk" vises
        assertEquals(1, fridge.getItems().stream()
                                .filter(item -> item.getDaysUntilExpiry() <= 3).count(),
                     "Kun 'Melk' forventes å utløpe innen 3 dager");
    
        System.out.println("Testing varer som går ut på dato innen 6 dager:");
        fridge.displayExpiringSoon(6);  // Forventer at "Melk" og "Egg" vises
        assertEquals(2, fridge.getItems().stream()
                                .filter(item -> item.getDaysUntilExpiry() <= 6).count(),
                     "Både 'Melk' og 'Egg' forventes å utløpe innen 6 dager");
    
        System.out.println("Testing varer som går ut på dato innen 8 dager:");
        fridge.displayExpiringSoon(8);  // Forventer at alle varer vises
        assertEquals(3, fridge.getItems().stream()
                                .filter(item -> item.getDaysUntilExpiry() <= 8).count(),
                     "Alle varer forventes å utløpe innen 8 dager");
    }
    

    @Test
    public void testDisplayExpiredItemsAndTotalValue() {
        Fridge fridge = new Fridge();

        // Legg til varer som har kort tid til utløp
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 0, 20.0));  // Utløper i dag
        fridge.addItem(new Grocery("Egg", 6, MeasuringUnit.PIECE, 2, 3.0));      // Ikke utløpt
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 0, 10.0));    // Utløper i dag

        System.out.println("Testing varer som har gått ut på dato:");
        fridge.displayExpiredItemsAndTotalValue(); // Forventer at kun 'Melk' og 'Mel' vises

        long expiredItemsCount = fridge.getItems().stream()
                                    .filter(item -> item.getDaysUntilExpiry() <= 0)
                                    .count();
        assertEquals(2, expiredItemsCount, "Forventer at 2 varer har gått ut på dato");
    }

}
