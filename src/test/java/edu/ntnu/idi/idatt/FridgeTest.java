package edu.ntnu.idi.idatt;

import java.util.List;

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
    public void testRemoveWholeItem() {
        Fridge fridge = new Fridge();
        Grocery melk = new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0);
        fridge.addItem(melk);
        
        fridge.removeWholeItem(melk);
        
        // Sjekk at varen er fjernet
        assertFalse(fridge.getItems().contains(melk), "Forventer at 'Melk' er fjernet fra kjøleskapet");
    }

    //sjekker om den klarer å søke etter ting i kjøleskapet. 
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

    //tester om den klarer å kalkulerer totale verdien av matvarene i kjøleskapet. 
    @Test
    public void testCalculateTotalValue() {
        Fridge fridge = new Fridge();
    
        // Legg til varer
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 7, 20.0)); // 20 kr per kilo
        fridge.addItem(new Grocery("Melk", 2, MeasuringUnit.LITER, 5, 15.0)); // 15 kr per liter
        fridge.addItem(new Grocery("Egg", 12, MeasuringUnit.PIECE, 10, 3.0)); // 3 kr per egg
    
        // Beregn totalverdi
        double totalValue = fridge.calculateTotalValue();
    
        // Forventet verdi:
        // Mel: (500 gram * 20 kr/kilo) = 10000 kr
        // Melk: (2 liter * 15 kr/liter) = 30 kr
        // Egg: (12 stykker * 3 kr/stk) = 36 kr
        // Total: 10 + 30 + 36 = 76 kr
        assertEquals(10066, totalValue, 0.01, "Totalverdien skal være korrekt beregnet.");
    }
    
    

    //tester displayExpiringSoon funksjonen.
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
    

    //test for å sjekke om den skjønner hva som har gått ut på dato og klarer å legge sammen verdien for dette.
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

    //test for å sjekke om matvarene blir sortert for navn riktig. 
    @Test
    public void testGetSortedItemsByName() {
        // Oppsett
        Fridge fridge = new Fridge();
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0));
        fridge.addItem(new Grocery("Egg", 12.0, MeasuringUnit.PIECE, 7, 3.0));
        fridge.addItem(new Grocery("Mel", 500.0, MeasuringUnit.GRAM, 10, 10.0));
        fridge.addItem(new Grocery("Ost", 0.5, MeasuringUnit.KILOGRAM, 6, 50.0));

        // Utfører
        List<Grocery> sortedItems = fridge.getSortedItemsByName();

        // Forventede resultater
        assertEquals("Egg", sortedItems.get(0).getName(), "Første element bør være 'Egg'.");
        assertEquals("Mel", sortedItems.get(1).getName(), "Andre element bør være 'Mel'.");
        assertEquals("Melk", sortedItems.get(2).getName(), "Tredje element bør være 'Melk'.");
        assertEquals("Ost", sortedItems.get(3).getName(), "Fjerde element bør være 'Ost'.");
    }

    //tester om sorteringen basert på utløpsdato fungerer ordentlig. 
    @Test
    public void testGetSortedItemsByExpiry() {
        // Oppretter et kjøleskap
        Fridge fridge = new Fridge(); 

        // Legger til varer med relative dager til utløp
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 5, 20.0)); // Utløper om 5 dager
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 10, 20.0)); // Utløper om 10 dager
        fridge.addItem(new Grocery("Brød", 1.0, MeasuringUnit.PIECE, 3, 15.0));  // Utløper om 3 dager

        // Henter ut sorterte varer for "Melk"
        List<Grocery> sortedMilk = fridge.getSortedItemsByExpiry("Melk");

        // Sjekker at listen ikke er tom
        assertFalse(sortedMilk.isEmpty(), "Listen over melk bør ikke være tom.");

        // Sjekker antall elementer
        assertEquals(2, sortedMilk.size(), "Det bør være 2 forekomster av melk i kjøleskapet.");

        // Dynamisk beregning av dager til utløp basert på nåværende dato
        int firstMilkDays = sortedMilk.get(0).getDaysUntilExpiry();
        int secondMilkDays = sortedMilk.get(1).getDaysUntilExpiry();

        // Sjekk rekkefølgen basert på dager til utløp
        assertTrue(firstMilkDays < secondMilkDays, 
                "Den første melken bør utløpe før den andre melken.");

        // Test for et navn som ikke finnes
        List<Grocery> sortedJuice = fridge.getSortedItemsByExpiry("Juice");
        assertTrue(sortedJuice.isEmpty(), "Listen over 'Juice' bør være tom fordi ingen varer matcher.");
    }


    @Test
    public void testRemoveItemByAmount() {
        Fridge fridge = new Fridge();
        Grocery melk = new Grocery("Melk", 2.0, MeasuringUnit.LITER, 5, 20.0);
        fridge.addItem(melk);

        fridge.removeItemByAmount("Melk", 1.0); // Fjerner 1 liter melk

        // Sjekk at mengden er oppdatert
        assertEquals(1.0, melk.getAmount(), 0.01, "Mengden melk bør reduseres til 1.0 liter.");
        assertTrue(fridge.getItems().contains(melk), "Melk bør fortsatt være i kjøleskapet.");
    }

    @Test
    public void testDisplayExpiredItems() {
        Fridge fridge = new Fridge();
        fridge.addItem(new Grocery("Melk", 1.0, MeasuringUnit.LITER, 0, 20.0)); // Utløpt
        fridge.addItem(new Grocery("Egg", 6, MeasuringUnit.PIECE, 5, 3.0));     // Ikke utløpt
        fridge.addItem(new Grocery("Mel", 500, MeasuringUnit.GRAM, 0, 10.0));  // Utløpt

        List<Grocery> expiredItems = fridge.displayExpiredItems();

        assertEquals(2, expiredItems.size(), "Forventer 2 utgåtte varer.");
        assertTrue(expiredItems.stream().anyMatch(item -> item.getName().equals("Melk")), "Melk skal være i listen over utgåtte varer.");
        assertTrue(expiredItems.stream().anyMatch(item -> item.getName().equals("Mel")), "Mel skal være i listen over utgåtte varer.");
    }

    @Test
    public void testRemoveAllExpiredItems(){
        Fridge fridge = new Fridge(); 

        // Lagt til varene Adrak, Agurk og Egg
        Grocery adrak = new Grocery("Adrak", 200, MeasuringUnit.GRAM, 5, 50.0); // Utløper om 5 dager
        Grocery agurk = new Grocery("Agurk", 1, MeasuringUnit.PIECE, 2, 10.0);  // Utløper om 2 dager
        Grocery egg = new Grocery("Egg", 12, MeasuringUnit.PIECE, 0, 3.0);      // Utløper i dag

        fridge.addItem(egg);
        fridge.addItem(adrak);
        fridge.addItem(agurk); 

        // Utføre metoden removeAllExpiredItems
        List<Grocery> removedItems = fridge.removeAllExpiredItems();

        // Verifiserer resultatet
        // 1. Sjekke at utgåtte varer er fjernet
        assertFalse(fridge.getItems().contains(egg), "Egg bør være fjernet fra kjøleskapet.");
        
        // 2. Sjekke at varer som ikke har gått ut på dato, fortsatt finnes
        assertTrue(fridge.getItems().contains(adrak), "Adrak bør fortsatt være i kjøleskapet.");
        assertTrue(fridge.getItems().contains(agurk), "Agurk bør fortsatt være i kjøleskapet.");

        // 3. Sjekke at den returnerte listen med fjernede varer er korrekt
        assertEquals(1, removedItems.size(), "Listen over fjernede varer bør inneholde én vare.");
        assertTrue(removedItems.contains(egg), "Listen over fjernede varer bør inneholde 'Egg'.");
    }


}
