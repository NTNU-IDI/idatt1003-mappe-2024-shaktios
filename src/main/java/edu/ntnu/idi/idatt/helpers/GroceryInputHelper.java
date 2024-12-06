package edu.ntnu.idi.idatt.helpers;

import edu.ntnu.idi.idatt.enums.MeasuringUnit;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * A helper class to manage user input for grocery-related operations.
 */
public class GroceryInputHelper {
  private final Scanner scanner;

  /**
   * Constructor to initialize the scanner.
   */
  public GroceryInputHelper() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Reads the name of a grocery item from the user.
   *
   * @return the name of the grocery item.
   */
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

  /**
   * Reads the measuring unit for a grocery item from the user.
   *
   * @return the selected measuring unit.
   */
  public MeasuringUnit readMeasuringUnit() {
    while (true) {
      MeasuringUnit.displayMeasuringUnits(); // Display options
      System.out.print("Velg en måleenhet (f.eks. GRAM, LITER, PIECE): ");
      String unit = scanner.nextLine();
      try {
        return MeasuringUnit.valueOf(unit.toUpperCase());
      } catch (IllegalArgumentException e) {
        System.out.println("Ugyldig måleenhet. Skriv inn en av de viste måleenhetene.");
      }
    }
  }

  /**
   * Reads a positive double value from the user.
   *
   * @param prompt the message to display to the user.
   * @return the positive double value entered by the user.
   */
  public double readPositiveDouble(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        if (value >= 0) {
          return value;
        } else {
          System.out.println("Verdien kan ikke være mindre enn 0. Prøv igjen.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig input. Skriv inn et tall.");
        scanner.nextLine(); // Clear invalid input
      }
    }
  }

  /**
   * Reads a positive integer value from the user.
   *
   * @param prompt the message to display to the user.
   * @return the positive integer value entered by the user.
   */
  public int readPositiveInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        if (value >= 0) {
          return value;
        } else {
          System.out.println("Antall dager kan ikke være negativt. Prøv igjen.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Ugyldig input. Skriv inn et heltall.");
        scanner.nextLine(); // Clear invalid input
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
