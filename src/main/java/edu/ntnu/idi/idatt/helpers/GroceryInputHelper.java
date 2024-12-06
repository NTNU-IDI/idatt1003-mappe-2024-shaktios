package edu.ntnu.idi.idatt.helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ntnu.idi.idatt.enums.MeasuringUnit;


public class GroceryInputHelper {
    private Scanner scanner;

    public GroceryInputHelper() {
        this.scanner = new Scanner(System.in);
    }

    public String readGroceryName() {
        while (true) {
            System.out.print("Navn: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                return name;
            }
            System.out.println("Navn kan ikke være tomt. Prøv igjen.");
        }
    }

    public MeasuringUnit readMeasuringUnit() {
        while (true) {
            MeasuringUnit.displayMeasuringUnits(); // Viser alternativer
            System.out.print("Velg en måleenhet (f.eks. GRAM, LITER, PIECE): ");
            String unit = scanner.nextLine();
            try {
                return MeasuringUnit.valueOf(unit.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig måleenhet. Skriv inn en av de viste måleenhetene.");
            }
        }
    }

    public double readPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Tømmer bufferet
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Verdien kan ikke være mindre enn 0. Prøv igjen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Skriv inn et tall.");
                scanner.nextLine(); // Tømmer ugyldig input
            }
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Tømmer bufferet
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Antall dager kan ikke være negativt. Prøv igjen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ugyldig input. Skriv inn et heltall.");
                scanner.nextLine(); // Tømmer ugyldig input
            }
        }
    }

    public void close() {
        scanner.close(); // Lukk scanner for å frigjøre ressurser
    }
}
