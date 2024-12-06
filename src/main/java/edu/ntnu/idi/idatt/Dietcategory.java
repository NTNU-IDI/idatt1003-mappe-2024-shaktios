package edu.ntnu.idi.idatt;

// Valgt å bruke enum da det er mer skalerbart enn en string for disse beskrivelsene 
public enum DietCategory {

    NONE("Ingen kategori"),
    VEGETARIAN("Vegetar"),
    VEGAN("Vegansk"),
    PESCETARIAN("Pescetariansk (inkluderer fisk)"),
    MEAT("Inkluderer kjøtt"),
    OTHER("Annet");

    // Et felt som lagrer en beskrivelse av dietten (for eksempel "Vegetarisk diett").
    private final String description;

    // Konstruktør for enum som setter beskrivelsen for hver diettkategori.
    DietCategory(String description) {
        this.description = description;
    }

    // Metode for å hente beskrivelsen av diettkategorien.
    // Denne gjør det mulig å vise en mer leservennlig tekst i stedet for kun enum-navnet.
    public String getDescription() {
        return description;
    }
}
