package edu.ntnu.idi.idatt;
import java.util.Date;



public class Grocery {
    private String name; //Navn på matvaren
    private double amount; //mengde matvare
    private String measuringUnit; //enhet på matvaren
    private Date bestBeforeDate ; //dato med hjelp av java.util bibloteket
    private double pricePerUnit; //pris per enhet 
    
    
    //konstruktør 

    public Grocery(String name, double amount, String measuringUnit, Date bestBeforeDate,double pricePerUnit){

        if (name==null || name.isEmpty()){
            throw new IllegalArgumentException("Navnet kan ikke være tomt, du må deklarere et navn");
        }
        if (amount<0){
            throw new IllegalArgumentException("Mengden kan ikke være mindre enn 0/negativ ");
        }
        if(pricePerUnit<0){
            throw new IllegalArgumentException("Pris per enhet kan ikke være mindre enn 0");
        }

        this.name = name; 
        this.amount = amount; 
        this.measuringUnit = measuringUnit; 
        this.bestBeforeDate = bestBeforeDate; 
        this.pricePerUnit = pricePerUnit; 
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



    

