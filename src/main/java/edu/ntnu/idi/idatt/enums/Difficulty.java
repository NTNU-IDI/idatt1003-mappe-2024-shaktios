package edu.ntnu.idi.idatt.enums;

/**
 * Represents different difficulty levels for recipes.
 * Using enums makes it easier to scale and prevents invalid values compared to strings.
 */
public enum Difficulty {

  /**
   * Easy difficulty level, suitable for quick and simple recipes.
   * (Enkel å lage)
   */
  EASY("Enkel å lage"),

  /**
   * Medium difficulty level, requires some effort but is manageable.
   * (Middels vanskelig)
   */
  MEDIUM("Middels vanskelig"),

  /**
   * Hard difficulty level, requires significant effort and time.
   * (Krever mye innsats)
   */
  HARD("Krever mye innsats");

  /**
   * A descriptive string for the difficulty level.
   * This provides a user-friendly representation of the enum value.
   */
  private final String description;

  /**
   * Constructor for initializing the difficulty level with its description.
   *
   * @param description a user-friendly description of the difficulty level
   */
  Difficulty(String description) {
    this.description = description;
  }

  /**
   * Retrieves the description of the difficulty level.
   * This can be used to display a readable text instead of the enum name.
   *
   * @return the description of the difficulty level
   */
  public String getDescription() {
    return description;
  }

  /**
   * Displays all available difficulty levels with their descriptions.
   * This method prints each difficulty level and its user-friendly description to the console.
   */
  public static void displayDifficulties() {
    System.out.println(
        "Velg vanskelighetsgrad: (skriv inn navnet til venstre i listen som parameter)");
    for (Difficulty difficulty : Difficulty.values()) {
      System.out.println("- " + difficulty.name() + ": " + difficulty.getDescription());
    }
  }
}
