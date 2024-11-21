package edu.ntnu.idi.idatt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;



public class Fridge {

    private List<Grocery> items; //liste som "Holder" på varene. 



    //konstruktør for å starte listen; 
    public Fridge(){
        this.items = new ArrayList<>();
    }   

    //Metode for å returnere alle varer i kjøleskapet
    public List<Grocery> getItems() {
        return items;
    }

    //Legg til vare 
    public void addItem(Grocery item){
        items.add(item);
        System.out.println(item.getName() + " er lagt til i kjøleskapet");
    }



    //fjern vare 
    public void removeItem(Grocery item){
        if(items.remove(item)){
            System.out.println(item.getName() + " er fjernet fra kjøleskapet");
        } else{
            System.out.println(item.getName()+ " finnes ikke i kjøleskapet");
        }
        
    }


    //vis alle varene i kjøleskapet; 
    public void displayItems(){
        if(items.isEmpty()){
            System.out.println("Kjøleskapet er tomt");
        }else{
            System.out.println("Varer i kjøleskapet: ");
            for(Grocery item: items){
                System.out.println(item);
            }
        }
    } 

    //finner varer som går ut på dato snart; 

    public void displayExpiringSoon(int daysUntilExpiry){
        System.out.println("Varer som går ut på dato innen " + daysUntilExpiry + " dager:");
        for (Grocery item : items){
            if(item.getDaysUntilExpiry() <= daysUntilExpiry){
                System.out.println(item);
            }
        }
    }

    //metode for å søke etter varer    
    public Grocery searchItem(String name){
        //lager en løkke for å gå gjennom alle varene i kjøleskapet
        for(Grocery item: items){
            if(item.getName().equalsIgnoreCase(name.trim())){ // Sammenlign navnene i listen med input fra bruker (case-insensitive) + trimmer for mellomrom
                return item; //returnerer hvis den finner varen. 
            }
        }
        return null; //retunerer ingenting hvis den ikke finner varen.
    }



//metode for å beregne totalverdien av varer i kjøleskapet med enhetskonvertering

public double calculateTotalValue() {
    double totalValue = 0;
    for (Grocery item : items) {
        double adjustedAmount = item.getAmount();
        
        // Sjekk enhetstype og konverter om nødvendig
        if (item.getMeasuringUnit() == MeasuringUnit.GRAM) {
            adjustedAmount = item.getAmount() / 1000.0; // Konverter gram til kilo
        } else if (item.getMeasuringUnit() == MeasuringUnit.MILLILITER) {
            adjustedAmount = item.getAmount() / 1000.0; // Konverter milliliter til liter
        }
        // Beregn pris basert på justert mengde
        totalValue += adjustedAmount * item.getPricePerUnit();
    }
    return totalValue;
}



// Metode for å fjerne vare fra kjøleskapet
public void removeItem(String itemName, double amountToRemove) {
    Iterator<Grocery> iterator = items.iterator();
    
    while (iterator.hasNext()) {
        Grocery item = iterator.next();

        if (item.getName().equalsIgnoreCase(itemName.trim())) {
            System.out.println("Varen " + item.getName() + " ble funnet! Opprinnelig mengde: " + item.getAmount());

            // Sjekk om vi har nok mengde til å fjerne alt
            if (item.getAmount() >= amountToRemove) {
                double newAmount = item.getAmount() - amountToRemove;
                item.setAmount(newAmount);

                System.out.println("Mengden etter fjerning: " + newAmount);

                // Fjern kun varen hvis mengden er null eller negativ
                if (newAmount <= 0) {
                    iterator.remove(); // Bruk iteratorens remove-metode for å unngå ConcurrentModificationException
                    System.out.println(itemName + " ble fjernet helt fra kjøleskapet.");
                }
            } else {
                System.out.println("Kan ikke fjerne " + amountToRemove + " av varen " + itemName + " fordi du har kun " + item.getAmount() + " igjen.");
            }
            return; // Avslutter metoden etter å ha behandlet varen
        }
    }
    // Hvis varen ikke ble funnet
    System.out.println(itemName + " finnes ikke i kjøleskapet.");
}


public void displayExpiredItemsAndTotalValue(){

    double totalValue = 0;  //Variabel for å lagre totalverdi av utgåtte varer
    boolean foundExpiredItems = false; // Flag for å sjekke om det finnes utgåtte varer

    System.out.println("Varer som har gått ut på dato:");

    for(Grocery item: items){
        if(item.getDaysUntilExpiry()<=0){
            System.out.println(item);
            totalValue +=  item.getPricePerUnit() * item.getAmount();  
            foundExpiredItems = true;
        }

    }

    if(foundExpiredItems){
        System.out.println("Den samlede verdien av utgåtte varer er " + totalValue + " NOK");
    }else{
        System.out.println("Ingen varer har gått ut på dato enda");
    }


}

//metode for å sortere matvarer på navn
public List<Grocery> getSortedItemsByName(){
    List<Grocery> sortedItems = new ArrayList<>(items); //kopierer listen
    sortedItems.sort(Comparator.comparing(g -> g.getName().toLowerCase())); // Alfabetisk sortering, case-insensitive
    return sortedItems;
}

}

