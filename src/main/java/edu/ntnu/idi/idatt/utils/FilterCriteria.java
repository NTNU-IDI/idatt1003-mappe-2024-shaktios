package edu.ntnu.idi.idatt.utils;

import java.util.List;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;

/**
 * Representerer søkekriterier for å filtrere oppskrifter i en Cookbook.
 * Denne klassen lar brukeren angi ulike kriterier som kan brukes til å søke etter oppskrifter basert på diettkategori, vanskelighetsgrad, maks tilberedningstid og ønskede ingredienser.
 * Brukes som et hjelpeobjekt som sendes inn til filterRecipes-metoden for å returnere en liste over oppskrifter som matcher kriteriene.
 * 
 * Designvalg:
 * "DietCategory" og "Difficulty" er enums for å begrense mulige verdier og gjøre koden mer robust.
 * "Integer" brukes i stedet for "int" for "maxPreparationTime" fordi det tillater en "null"-verdi, som betyr "ingen kriterier spesifisert" (dvs. brukeren har ikke valgt en maks tid)
 * Uten Integer ville man ikke kunne skille mellom 0 og "null(verditypen)". 
 * - "List<String>"" brukes for ingredienser for å tillate fleksibilitet og flere ingredienser i søket.
 * 
 * Eksempel på bruk:
 * 
 * FilterCriteria criteria = new FilterCriteria();
 * criteria.setDietCategory(DietCategory.VEGAN);
 * criteria.setMaxPreparationTime(30);
 * criteria.setIngredients(Arrays.asList("tomat", "basilikum"));
 * 
 * List<Recipe> filteredRecipes = cookbook.filterRecipes(criteria);
 * 
 * 
 * Gettere og settere er inkludert for enkel tilgang til feltene
 */

 public class FilterCriteria {
    private DietCategory dietCategory;  // Diett-kategori, enum
    private Difficulty difficulty;      // Vanskelighetsgrad, enum
    private Integer maxPreparationTime; // Maks tilberedningstid i minutter
    private List<String> ingredients; // Liste over ingredienser som skal være med
    private String category; 
    private String cuisine; 

    /**
     * Henter diettkategorien som er valgt for søkekriteriet.
     * @return DietCategory valgt kategori, eller null hvis ingen er satt.
     */
    public DietCategory getDietCategory() {
        return dietCategory;
    }

    /**
     * Setter diettkategorien som skal brukes for søk.
     * @param dietCategory DietCategory som representerer ønsket kategori.
     */
    public void setDietCategory(DietCategory dietCategory) {
        this.dietCategory = dietCategory;
    }

    /**
     * Henter vanskelighetsgraden som er valgt for søkekriteriet.
     * @return Difficulty valgt vanskelighetsgrad, eller null hvis ingen er satt.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Setter vanskelighetsgraden som skal brukes for søk.
     * @param difficulty Difficulty som representerer ønsket vanskelighetsgrad.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Henter maks tilberedningstid i minutter som er valgt for søkekriteriet.
     * @return Integer maks tid, eller null hvis ingen er satt.
     */
    public Integer getMaxPreparationTime() {
        return maxPreparationTime;
    }

    public void setMaxPreparationTime(Integer maxPreparationTime){
        this.maxPreparationTime = maxPreparationTime; 
    }

    /**
     * Henter listen over ingredienser som er valgt for søkekriteriet.
     * @return Liste over ingredienser, eller null hvis ingen er satt.
     */
    public List<String> getIngredients() {
        return ingredients;

    }

     /**
     * Setter listen over ingredienser som skal brukes for søk.
     * @param ingredients Liste med ingredienser.
     */
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Henter kategorien som er valgt for søkekriteriet.
     * @return String kategori, eller null hvis ingen er satt.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter kategorien som skal brukes for søk.
     * @param category Kategori som forrett, hovedrett, dessert, etc.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Henter retten sitt opprinnelsessted som er valgt for søkekriteriet.
     * @return String cuisine, eller null hvis ingen er satt.
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * Setter retten sitt opprinnelsessted som skal brukes for søk.
     * @param cuisine Opprinnelsessted (indisk, norsk, etc.).
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

}