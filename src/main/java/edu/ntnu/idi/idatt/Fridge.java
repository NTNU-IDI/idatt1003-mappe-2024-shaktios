package edu.ntnu.idi.idatt;
import java.util.ArrayList;
import java.util.List;


public class Fridge {

    private List<Grocery> items; //liste som "Holder" på varene. 



    //konstruktør for å starte listen; 
    public Fridge(){
        this.items = new ArrayList<>();
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






}

