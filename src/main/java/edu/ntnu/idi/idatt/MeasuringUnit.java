package edu.ntnu.idi.idatt;

/**
 * Enum som representerer forskjellige måleenheter for varer, hver med en tilknyttet konverteringsfaktor til basisenhet.
 */
public enum MeasuringUnit {
    GRAM(1),
    KILOGRAM(1000),
    MILLILITER(1),
    LITER(1000),
    PIECE(1);  // For stykker, konverteringsfaktoren er 1

    private final int conversionFactor;

    MeasuringUnit(int conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public int getConversionFactor() {
        return conversionFactor;
    }

    // Konverter mengde til basisenhet
    public double toBaseUnit(double amount) {
        return amount / conversionFactor;
    }

    // Konverter mengde fra basisenhet
    public double fromBaseUnit(double amount) {
        return amount * conversionFactor;
    }

    // Kombiner to mengder med enhetskonvertering
    public static double combine(double amount1, MeasuringUnit unit1, double amount2, MeasuringUnit unit2, MeasuringUnit targetUnit) {
        // Sjekk kompatibilitet (valgfritt)
        if (!areUnitsCompatible(unit1, unit2)) {
            throw new IllegalArgumentException("Ulike typer måleenheter kan ikke kombineres: " + unit1 + " og " + unit2);
        }

        // Konverter begge mengdene til basisenheter
        double amountInBase1 = unit1.toBaseUnit(amount1);
        double amountInBase2 = unit2.toBaseUnit(amount2);

        // Summen i basisenhet
        double totalInBase = amountInBase1 + amountInBase2;

        // Konverter summen til ønsket mål
        return targetUnit.fromBaseUnit(totalInBase);
    }

    // Sjekk om to måleenheter er kompatible
    private static boolean areUnitsCompatible(MeasuringUnit unit1, MeasuringUnit unit2) {
        return (unit1 == GRAM || unit1 == KILOGRAM) && (unit2 == GRAM || unit2 == KILOGRAM) ||
               (unit1 == MILLILITER || unit1 == LITER) && (unit2 == MILLILITER || unit2 == LITER) ||
               (unit1 == PIECE && unit2 == PIECE);
    }
}
