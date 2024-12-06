package edu.ntnu.idi.idatt.enums;

/**
 * Enum representing various measuring units for items, each with a conversion factor 
 * to its base unit. This allows consistent calculations across different units.
 */
public enum MeasuringUnit {

    /**
     * Gram as the base unit (conversion factor is 1).
     */
    GRAM(1),

    /**
     * Kilogram with a conversion factor of 1000 to grams.
     */
    KILOGRAM(1000),

    /**
     * Milliliter as the base unit for liquid measurement (conversion factor is 1).
     */
    MILLILITER(1),

    /**
     * Liter with a conversion factor of 1000 to milliliters.
     */
    LITER(1000),

    /**
     * Piece for countable items (conversion factor is 1).
     */
    PIECE(1);  
    
    /**
     * The conversion factor of the measuring unit to its base unit.
     */
    private final int conversionFactor;

    /**
     * Constructor for initializing the measuring unit with its conversion factor.
     *
     * @param conversionFactor the factor to convert this unit to its base unit
     */
    MeasuringUnit(int conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    /**
     * Retrieves the conversion factor of the measuring unit.
     *
     * @return the conversion factor of this unit
     */
    public int getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a given amount from this unit to its base unit.
     *
     * @param amount the amount in this unit to be converted
     * @return the equivalent amount in the base unit
     */
    public double toBaseUnit(double amount) {
        return amount / conversionFactor;
    }

    /**
     * Converts a given amount from the base unit to this unit.
     *
     * @param amount the amount in the base unit to be converted
     * @return the equivalent amount in this unit
     */
    public double fromBaseUnit(double amount) {
        return amount * conversionFactor;
    }

    /**
     * Displays all available measuring units in the console.
     * This method lists the names of all units.
     */
    public static void displayMeasuringUnits() {
        System.out.println("Tilgjengelige måleenheter: (skriv inn det som står til venstre i listen nedenfor som parameter)");
        for (MeasuringUnit unit : MeasuringUnit.values()) {
            System.out.println("- " + unit.name());
        }
    }
}
