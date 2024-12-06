package edu.ntnu.idi.idatt.helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.enums.MeasuringUnit;

public class CookbookInputHelper {
    private Scanner scanner;

    public CookbookInputHelper() {
        this.scanner = new Scanner(System.in);
    }



    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input kan ikke være tomt. Prøv igjen.");
            return scanner.nextLine().trim(); // Trim for å fjerne unødvendig whitespace
        }
    }

   

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Tømmer linjeskift fra bufferen
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Vennligst skriv inn et heltall.");
                scanner.nextLine(); // Tømmer ugyldig input fra bufferen
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Tømmer linjeskift fra bufferen
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Vennligst skriv inn et tall.");
                scanner.nextLine(); // Tømmer ugyldig input fra bufferen
            }
        }
    }


    public DietCategory readDietCategory() {
        while (true) {
            try {
                System.out.println("Tilgjengelige diettkategorier:");
                for (DietCategory category : DietCategory.values()) {
                    System.out.println("- " + category.name() + ": " + category.getDescription());
                }
                String input = readString("Velg en diettkategori: ");
                return DietCategory.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig diettkategori. Prøv igjen.");
            }
        }
    }

    public Difficulty readDifficulty() {
        while (true) {
            try {
                Difficulty.displayDifficulties();
                String input = readString("Velg en vanskelighetsgrad: ");
                return Difficulty.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig vanskelighetsgrad. Prøv igjen.");
            }
        }
    }

    public int readServings() {
        while (true) {
            try {
                int servings = readInt("Antall porsjoner (må være > 0): ");
                if (servings > 0) {
                    return servings;
                } else {
                    System.out.println("Antall porsjoner må være større enn 0. Prøv igjen.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig input. Prøv igjen.");
            }
        }
    }

    public String readCategory() {
        while (true) {
            String category = readString("Kategori (f.eks. Hovedrett, Forrett, Dessert): ");
            if (!category.isEmpty()) {
                return category;
            } else {
                System.out.println("Kategori kan ikke være tom. Prøv igjen.");
            }
        }
    }

    public String readCuisine() {
        while (true) {
            String cuisine = readString("Opphavskjøkken (f.eks. Indisk, Italiensk, Norsk): ");
            if (!cuisine.isEmpty()) {
                return cuisine;
            } else {
                System.out.println("Opphavskjøkken kan ikke være tomt. Prøv igjen.");
            }
        }
    }

    public MeasuringUnit readMeasuringUnit() {
        while (true) {
            MeasuringUnit.displayMeasuringUnits(); // Viser alternativer
            String unit = readString("Velg en måleenhet: ");
            try {
                return MeasuringUnit.valueOf(unit.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig måleenhet. Prøv igjen.");
            }
        }
    }
    

    public void close() {
        scanner.close();
    }
}
