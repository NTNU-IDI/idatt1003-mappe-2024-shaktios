package edu.ntnu.idi.idatt;


public class GroceryAppUI {

    private Fridge fridge; // Legger til et kjøleskap

    // Konstruktør
    public GroceryAppUI() {
        this.fridge = new Fridge();
    }


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


        fridge.addItem(melk);
        fridge.addItem(egg);
        fridge.addItem(brød);
        fridge.addItem(epler);

    
        

        // Viser alle varer i kjøleskapet
        fridge.displayItems();

        // Viser varer som går ut på dato snart (innen 3 dager)
        fridge.displayExpiringSoon(3);

        searchAndDisplayItem("Melk"); // Søker etter "Melk"
        searchAndDisplayItem("Appelsin"); // Søker etter en vare som ikke er der

        fridge.removeItem("epler",5);
        fridge.removeItem("appelsin",12);
        fridge.removeItem("melk",1.7);

        searchAndDisplayItem("epler");
    }


     // Metode for å skrive ut detaljene for en vare
     public void printGroceryDetails(Grocery grocery) {
        System.out.println(grocery);  
    }

    public void searchAndDisplayItem(String name){
        Grocery item = fridge.searchItem(name); //bruker søkemetoden vi lagde i fridge.java
        if (item != null){
            System.out.println("Varen ble funnet! " + item);
        }
        else{
            System.out.println("Varen " + name + " finnes dessverre ikke i kjøleskapet ");
        }
    }


    // Hovedmetode som starter applikasjonen
    public static void main(String[] args) {
        GroceryAppUI app = new GroceryAppUI();
        app.init();  // starter applikasjonen
        app.start(); // Starter og tester Grocery-klassen
    }
    
}
