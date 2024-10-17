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

        Grocery epler = new Grocery("Epler", 6, "stk", 7, 15.0);
        fridge.addItem(epler);

        System.out.println("Før fjerning:");
        fridge.displayItems();

        fridge.removeItem("epler", 5);  // Prøv å fjerne 5 epler

        System.out.println("Etter fjerning av 5 epler:");
        fridge.displayItems();

        fridge.removeItem("epler", 1);  // Prøv å fjerne resten av eplene

        System.out.println("Etter fjerning av siste eple:");
        fridge.displayItems();

        Grocery appelsiner = new Grocery("Appelsiner", 20, "stk", 7, 15.0);
        fridge.addItem(appelsiner);

        System.out.println("Før fjerning:");
        fridge.displayItems();

        fridge.removeItem("appelsiner", 21);  // Prøv å fjerne 5 epler

        fridge.removeItem("sukker",20);

        searchAndDisplayItem("epler");
        searchAndDisplayItem("Appelsiner");

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
