package edu.ntnu.idi.idatt;

//Valgt å bruke enum da det er mer skalerbart enn en string for disse beskrivelsene 

public enum Difficulty {

    EASY("Enkel å lage"),
    MEDIUM("Middels vanskelig"),
    HARD("Krever mye innsats"); 

    // Et felt for å lagre beskrivelsen av vanskelighetsgraden (for eksempel "Enkel å lage").
    private final String description;


    // Konstruktør for enum som setter beskrivelsen for hver vanskelighetsgrad.
    Difficulty(String description) {
        this.description = description;
    }

    // Metode for å hente beskrivelsen av vanskelighetsgraden.
    // Denne brukes for å gi en mer leservennlig beskrivelse i stedet for enum-navnet.
    public String getDescription() {
        return description;
    }



}
