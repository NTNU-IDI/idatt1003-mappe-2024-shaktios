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

       
       // Legg til noen varer med ulike enheter
       Grocery mel = new Grocery("Mel", 500, MeasuringUnit.GRAM, 10, 20.0); // 500 gram mel
       Grocery melk = new Grocery("Melk", 2, MeasuringUnit.LITER, 5, 15.0); // 2 liter melk
       Grocery egg = new Grocery("Egg", 12, MeasuringUnit.PIECE, 3, 3.0);   // 12 stk egg

       // Legg til varer i kjøleskapet
       fridge.addItem(mel);
       fridge.addItem(melk);
       fridge.addItem(egg);

        
         // Viser alle varer i kjøleskapet
        fridge.displayItems();

        // Kjør metoden for å vise varer som har gått ut på dato
        fridge.displayExpiredItemsAndTotalValue();

        //viser totalverdien i kjøleskapet. 
        displayTotalValueInFridge();
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

     //metode for å vise totalsummen av varer

     public void displayTotalValueInFridge(){
        double totalValue = fridge.calculateTotalValue();
        System.out.println("Den samlede verdien i kjøleskapet er " + totalValue + " NOK");

     }


    // Hovedmetode som starter applikasjonen
    public static void main(String[] args) {
        GroceryAppUI app = new GroceryAppUI();
        app.init();  // starter applikasjonen
        app.start(); // Starter og tester Grocery-klassen
    }
    
}
