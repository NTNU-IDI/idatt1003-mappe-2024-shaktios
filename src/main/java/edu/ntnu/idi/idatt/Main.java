package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.utils.Kitchenmanager;

/**
 * Wrapper class for the static main method to run the application.
 */
public class Main {
      /**
     * Main method - entry point of the application.
     *
     * 
     */
    public static void main(String[] args) {

        Kitchenmanager kitchenManager = new Kitchenmanager();

         // Initialiserer programmet med standarddata
        kitchenManager.init();

        kitchenManager.start();
  }
}


