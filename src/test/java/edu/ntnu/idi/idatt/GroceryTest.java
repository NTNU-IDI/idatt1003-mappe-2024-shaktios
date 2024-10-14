package edu.ntnu.idi.idatt;
import java.util.Date;
public class GroceryTest {

    public static void main(String[] args) {
        // Kjør testene direkte fra main()
        testValidGrocery();
        testNegativeAmountThrowsException();
        testEmptyNameThrowsException();
    }

    public static void testValidGrocery() {
        Date date = new Date(); // oppretter dagens dato
        Grocery melk = new Grocery("Melk", 1.5, "liter", date, 20.0); // setter opp melk som en "Grocery"

        // Tester om feltene i Grocery-objektet har riktig verdi
        if ("Melk".equals(melk.getName()) &&
            1.5 == melk.getAmount() &&
            "liter".equals(melk.getMeasuringUnit()) &&
            20.0 == melk.getPricePerUnit()) {
            System.out.println("testValidGrocery: OK");
        } else {
            System.out.println("testValidGrocery: FEILET");
        }
    }

    public static void testNegativeAmountThrowsException() {
        Date date = new Date();
        try {
            new Grocery("Melk", -1.0, "liter", date, 20.0);
            System.out.println("testNegativeAmountThrowsException: FEILET (Forventet unntak ble ikke kastet)");
        } catch (IllegalArgumentException e) {
            System.out.println("testNegativeAmountThrowsException: OK");
        }
    }

    public static void testEmptyNameThrowsException() {
        Date date = new Date();
        try {
            new Grocery("", 1.0, "liter", date, 20.0);
            System.out.println("testEmptyNameThrowsException: FEILET (Forventet unntak ble ikke kastet)");
        } catch (IllegalArgumentException e) {
            System.out.println("testEmptyNameThrowsException: OK");
        }
    }
}
