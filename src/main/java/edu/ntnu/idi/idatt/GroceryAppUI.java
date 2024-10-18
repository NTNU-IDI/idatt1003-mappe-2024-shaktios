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
        // Tester kun fjerning og logging for enkel feilsøking

       // Opprett varer med utgått dato
        Grocery melk = new Grocery("Melk", 1.5, "liter", 0, 20.0); // Melk som gikk ut for 2 dager siden
    Grocery egg = new Grocery("Egg", 12, "stk", 0, 30.0); // Egg som gikk ut for 1 dag siden

        fridge.addItem(melk);
        fridge.addItem(egg);

        // Kjør metoden for å vise varer som har gått ut på dato
        fridge.displayExpiredItemsAndTotalValue();




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
