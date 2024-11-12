package edu.ntnu.idi.idatt;

import java.util.List;

public class Recipe {
    private String name;           // Navnet på oppskriften
    private String description;     // En kort beskrivelse av hva oppskriften lager
    private String instructions;    // Instruksjoner for hvordan man kan lage retten
    private List<Grocery> ingredients; // Liste over ingredienser som kreves for oppskriften
    private int servings;               //hvor mange personen oppskriften er egnet for 

    // Konstruktør med validering
    public Recipe(String name, String description, String instructions, List<Grocery> ingredients, int servings) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Navnet kan ikke være tomt, du må deklarere et navn.");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Beskrivelsen kan ikke være tom.");
        }
        if (instructions == null || instructions.isEmpty()) {
            throw new IllegalArgumentException("Instruksjonene kan ikke være tomme.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("Ingredienslisten kan ikke være tom.");
        }
        if (servings<=0){
            throw new IllegalArgumentException("Antall personer oppskriften er egnet må være større enn 0"); 
        }

        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings; 
    }

     // Getters
     public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<Grocery> getIngredients() {
        return ingredients;
    }

    public int getServings(){
        return servings;
    }

    // Metode for å legge til en ingrediens (valgfritt)
    public void addIngredient(Grocery ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediensen kan ikke være null.");
        }
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", instructions='" + instructions + '\'' +
                ", ingredients=" + ingredients + 
                ", servings =" + servings +
                 '}';
    }

}
