package edu.ntnu.idi.idatt;

import java.util.Calendar;
import java.util.Date;


public class Grocery {
    private String name; //Navn på matvaren
    private double amount; //mengde matvare
    private String measuringUnit; //enhet på matvaren
    private Date bestBeforeDate ; //dato med hjelp av java.util bibloteket
    private double pricePerUnit; //pris per enhet 
    private Date today; //dagens dato
    

    //har ingen "daysUntilExpiry", koden er dynamisk og oppdaterer seg hver gang den kjøres på denne måten.  
    
    //konstruktør 

    public Grocery(String name, double amount, String measuringUnit,int daysUntilExpiry ,double pricePerUnit){

        if (name==null || name.isEmpty()){
            throw new IllegalArgumentException("Navnet kan ikke være tomt, du må deklarere et navn");
        }
        if (amount<0){
            throw new IllegalArgumentException("Mengden kan ikke være mindre enn 0/negativ ");
        }
        if(pricePerUnit<0){
            throw new IllegalArgumentException("Pris per enhet kan ikke være mindre enn 0");
        }
        if (daysUntilExpiry < 0) {
            throw new IllegalArgumentException("Antall dager til utløp kan ikke være negativt.");
        }

        this.name = name; 
        this.amount = amount; 
        this.measuringUnit = measuringUnit; 
        this.pricePerUnit = pricePerUnit; 
    

        //setter dagens dato
        this.today = new Date();

        //setter best før dato ved å legge til dager til dagens dato

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_YEAR,daysUntilExpiry);
        this.bestBeforeDate = cal.getTime();
        
    }

    //setter opp mine gettere og settere 

    //getters

    public String getName(){
        return name;
    }

    public double getAmount(){
        return amount;
    }

    public String getMeasuringUnit(){
        return measuringUnit;
    }

    public Date getBestBeforeDate(){
        return bestBeforeDate; 
    }

    public double getPricePerUnit(){
        return pricePerUnit;
    }

    
    
    public int getDaysUntilExpiry() {
        Date currentDate = new Date(); // Hent dagens faktiske dato
        long diffInMillies = bestBeforeDate.getTime() - currentDate.getTime(); // Forskjell i millisekunder
        int daysUntilExpiry = (int) (diffInMillies / (1000 * 60 * 60 * 24)); // Konverter millisekunder til dager
        return daysUntilExpiry;
    }

    
   
    //setters; 

    public void setName(String name){
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException("Navnet kan ikke vært tomt");
        } 
        this.name = name;

    }

    public void setAmount(double amount){
        if(amount<0){
            throw new IllegalArgumentException("Mengden kan ikke være mindre enn 0 eller være negativ.");
        }
        this.amount = amount; 
    }
    
    public void setMeasuringUnit(String measuringUnit){
        this.measuringUnit = measuringUnit;
    }

    public void setBestBeforeDate(Date bestBeforeDate){
        this.bestBeforeDate = bestBeforeDate; 
    }

    public void setPricePerUnit(double pricePerUnit){
        if(pricePerUnit<0){
            throw new IllegalArgumentException("Pris per enhet kan ikke være negativ/mindre enn 0");
        }
        this.pricePerUnit = pricePerUnit; 
    }



    @Override
    //tilpaser hvordan objekter blir representert
    public String toString() {
        return "Grocery{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + measuringUnit + '\'' +
                ", bestBeforeDate=" + bestBeforeDate +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}



    

