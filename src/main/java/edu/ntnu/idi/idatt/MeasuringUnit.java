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
    }
    


