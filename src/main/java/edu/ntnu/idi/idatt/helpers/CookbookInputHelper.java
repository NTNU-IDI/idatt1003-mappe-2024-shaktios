package edu.ntnu.idi.idatt.helpers;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ntnu.idi.idatt.enums.DietCategory;
import edu.ntnu.idi.idatt.enums.Difficulty;
import edu.ntnu.idi.idatt.enums.MeasuringUnit;



/**
 * A helper class for managing user input for cookbook-related operations.
 */
public class CookbookInputHelper {
  private final Scanner scanner;

  /**
   * Constructor to initialize the scanner.
   */
  public CookbookInputHelper() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Reads a non-empty string from the user.
   *
   * @param prompt the message to display to the user.
   * @return the input string.
   */
  public String readString(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine().trim();
      if (!input.isEmpty()) {
        return input;
      }
      System.out.println("Input kan ikke være tomt. Prøv igjen.");
    }
  }

  /**
   * Reads an integer value from the user.
   *
   * @param prompt the message to display to the user.
   * @return the input integer.
   */
  public int readInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        return value;
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig input. Vennligst skriv inn et heltall.");
        scanner.nextLine(); // Clear invalid input
      }
    }
  }

  /**
   * Reads a double value from the user.
   *
   * @param prompt the message to display to the user.
   * @return the input double value.
   */
  public double readDouble(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        return value;
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig input. Vennligst skriv inn et tall.");
        scanner.nextLine(); // Clear invalid input
      }
    }
  }

  /**
   * Reads a diet category from the user.
   *
   * @return the selected diet category.
   */
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

  /**
   * Reads a difficulty level from the user.
   *
   * @return the selected difficulty level.
   */
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

  /**
   * Reads the number of servings from the user.
   *
   * @return the number of servings.
   */
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

  /**
   * Reads the category of a dish from the user.
   *
   * @return the dish category.
   */
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

  /**
   * Reads the cuisine of a dish from the user.
   *
   * @return the dish cuisine.
   */
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

  /**
   * Reads a measuring unit from the user.
   *
   * @return the selected measuring unit.
   */
  public MeasuringUnit readMeasuringUnit() {
    while (true) {
      MeasuringUnit.displayMeasuringUnits(); // Display options
      String unit = readString("Velg en måleenhet: ");
      try {
        return MeasuringUnit.valueOf(unit.toUpperCase());
      } catch (IllegalArgumentException e) {
        System.out.println("Ugyldig måleenhet. Prøv igjen.");
      }
    }
  }

  /**
   * Closes the scanner to release resources.
   */
  public void close() {
    scanner.close();
  }
}
