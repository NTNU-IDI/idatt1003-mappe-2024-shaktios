package edu.ntnu.idi.idatt.models;

import java.util.List;
import java.util.stream.Collectors;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;


public class Recipe {
    
    private String name;           // Navnet på oppskriften
    private String description;     // En kort beskrivelse av hva oppskriften lager
    private String instructions;    // Instruksjoner for hvordan man kan lage retten
    private List<Grocery> ingredients; // Liste over ingredienser som kreves for oppskriften
    private int servings;               //hvor mange personen oppskriften er egnet for 
    private String category;            //Forrett, dessert, hovedrett osv osv 
    private int preparationTimeMinutes; //hvor lang tid det tar å lage retten i minutter
    private DietCategory dietCategory;        // representerer matrettens diettkategori (f.eks. "Vegetar", "Vegansk", "Pescetariansk", "Med kjøtt")
    private Difficulty difficulty;         // hvor vanskelig er det å lage retten? (f.eks. medium, enkelt easy hard osv osv)
    private String cusine;              // Hvor stammer retten fra? (f.eks. indisk, norsk, somalsk osv osv)


    // Konstruktør med validering
    public Recipe(String name, String description, String instructions, List<Grocery> ingredients, int servings, String category, int preparationTimeMinutes, DietCategory dietCategory, Difficulty difficulty, String cusine) {
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
        if ( category== null || category.isEmpty()) {
            throw new IllegalArgumentException(" Kategorien kan ikke være tomt, du må deklarere en kategori (Hovedrett, Forrett, Dessert osv) .");
        }
        if (preparationTimeMinutes<=0) {
            throw new IllegalArgumentException("Oppskriften må ta over 0 minutter å lage");
        }
        if (dietCategory== null) {
            throw new IllegalArgumentException("Diettkategorien kan ikke være tom");
        }
        if (difficulty== null) {
            throw new IllegalArgumentException(" Vanskelighetsgraden kan ikke være tom, du må deklarere en vanskelighetsgrad (f.eks lett, vanskelig, medium osv) .");
        }
        if (cusine== null || cusine.trim().isEmpty()) {
            throw new IllegalArgumentException(" Cusine kan ikke være tomt, du må deklarere hvor retten kommer fra (India, Norge, Somalia osv) .");
        }


        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.servings = servings; 
        this.category = category; 
        this.preparationTimeMinutes = preparationTimeMinutes; 
        this.dietCategory = dietCategory; 
        this.difficulty = difficulty; 
        //fjerner ekstra mellomrom rundt hvor retten kommer fra
        this.cusine = cusine.trim().toLowerCase(); 
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

    public String getCategory(){
        return category;
    }
    
    public int getPreperationTimeMinutes(){
        return preparationTimeMinutes;
    }

    public DietCategory getDietCategory() {
        return dietCategory;
    }
    
    public String getDietCategoryDescription() {
        return dietCategory.getDescription();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    public String getDifficultyDescription() {
        return difficulty.getDescription();
    }

    public String getCusine(){
        return cusine; 
    }

    // Metode for å oppdatere en eksisterende oppskrift ved å legge til nye ingredienser. 
    public void addIngredient(Grocery ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediensen kan ikke være null.");
        }
        ingredients.add(ingredient);
    }


    private String formatIngredients() {
    return ingredients.stream()
        .map(ingredient -> "\t- " + ingredient.toString()) // Innrykk og listepunkt
        .collect(Collectors.joining("\n"));
}


    @Override
    public String toString() {
        return String.format(
            "Oppskrift: %s\n" +
            "Beskrivelse: %s\n" +
            "Instruksjoner: %s\n" +
            "Ingredienser:\n%s\n" +
            "Porsjoner: %d\n" +
            "Kategori: %s\n" +
            "Tilberedningstid: %d minutter\n" +
            "Diettkategori: %s\n" +
            "Vanskelighetsgrad: %s\n" +
            "Kjøkken: %s",
            name,
            description,
            instructions,
            formatIngredients(),
            servings,
            category,
            preparationTimeMinutes,
            dietCategory.getDescription(),
            difficulty.getDescription(),
            cusine
        );
    }
    

}
