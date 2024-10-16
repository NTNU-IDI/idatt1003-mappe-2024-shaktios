package edu.ntnu.idi.idatt;


public class GroceryAppUI {

    //metode for å starte applikasjonen
    public void init(){

        System.out.println("Applikasjonen startes...");

    }

    public void start() {
        System.out.println("Velkommen til Best før appen!");
        // Kommer til å implementere logikk for å håndtere brukerens input
        // lage menyvalg, ta imot data osv


         // tester instanser av Grocery-klassen

         Grocery melk = new Grocery("Melk", 1.5, "liter", 10, 20.0);
         Grocery egg = new Grocery("Egg", 12, "stk", 14, 30.0);
         Grocery brød = new Grocery("Brød", 1, "stk", 3, 25.0);
         Grocery epler = new Grocery("Epler", 6, "stk", 7, 15.0);
 
         // Skriver ut detaljene for hver vare
         printGroceryDetails(melk);
         printGroceryDetails(egg);
         printGroceryDetails(brød);
         printGroceryDetails(epler);
    }


     // Metode for å skrive ut detaljene for en vare
     public void printGroceryDetails(Grocery grocery) {
        System.out.println(grocery);  
    }


    // Hovedmetode som starter applikasjonen
    public static void main(String[] args) {
        GroceryAppUI app = new GroceryAppUI();
        app.init();  // starter applikasjonen
        app.start(); // Starter og tester Grocery-klassen
    }
    
}
