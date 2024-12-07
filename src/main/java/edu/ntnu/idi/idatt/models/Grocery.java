package edu.ntnu.idi.idatt.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.ntnu.idi.idatt.enums.MeasuringUnit;

/**
 * Represents a grocery item with attributes such as name, amount, best-before date, and price per unit.
 */
public class Grocery {
    private String name;
    private double amount;
    private Date bestBeforeDate;
    private MeasuringUnit measuringUnit;
    private double pricePerUnit;
    private Date today;

    /**
     * Constructs a grocery item with specified attributes.
     *
     * @param name           the name of the grocery item
     * @param amount         the quantity of the grocery item
     * @param measuringUnit  the unit of measurement for the item
     * @param daysUntilExpiry the number of days until the item expires
     * @param pricePerUnit   the price per unit of the item
     */
    public Grocery(String name, double amount, MeasuringUnit measuringUnit,
                   int daysUntilExpiry, double pricePerUnit) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Navnet kan ikke være tomt, du må deklarere et navn");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Mengden kan ikke være mindre enn 0/negativ");
        }
        if (pricePerUnit < 0) {
            throw new IllegalArgumentException("Pris per enhet kan ikke være mindre enn 0");
        }
        if (daysUntilExpiry < 0) {
            throw new IllegalArgumentException("Antall dager til utløp kan ikke være negativt.");
        }
        if (measuringUnit == null) {
            throw new IllegalArgumentException("Måleenheten kan ikke være tom mengde");
        }

        this.name = name;
        this.amount = amount;
        this.measuringUnit = measuringUnit;
        this.pricePerUnit = pricePerUnit;

        this.today = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_YEAR, daysUntilExpiry);
        this.bestBeforeDate = cal.getTime();
    }

    /**
     * Gets the name of the grocery item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the amount of the grocery item.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the unit of measurement for the grocery item.
     *
     * @return the measuring unit
     */
    public MeasuringUnit getMeasuringUnit() {
        return measuringUnit;
    }

    /**
     * Gets the best-before date of the grocery item.
     *
     * @return the best-before date
     */
    public Date getBestBeforeDate() {
        return bestBeforeDate;
    }

    /**
     * Gets the price per unit of the grocery item.
     *
     * @return the price per unit
     */
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * Calculates the number of days until the item expires.
     *
     * @return the number of days until expiry
     */
    public int getDaysUntilExpiry() {
        Date currentDate = new Date();
        long diffInMillies = bestBeforeDate.getTime() - currentDate.getTime();
        return (int) (diffInMillies / (1000 * 60 * 60 * 24));
    }

    /**
     * Sets the name of the grocery item.
     *
     * @param name the new name of the item
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Navnet kan ikke vært tomt");
        }
        this.name = name;
    }

    /**
     * Sets the amount of the grocery item.
     *
     * @param amount the new amount
     */
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Mengden kan ikke være mindre enn 0 eller være negativ.");
        }
        this.amount = amount;
    }

    /**
     * Sets the measuring unit of the grocery item.
     *
     * @param measuringUnit the new measuring unit
     */
    public void setMeasuringUnit(MeasuringUnit measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    /**
     * Sets the best-before date of the grocery item.
     *
     * @param bestBeforeDate the new best-before date
     */
    public void setBestBeforeDate(Date bestBeforeDate) {
        if (bestBeforeDate.before(new Date())) {
            throw new IllegalArgumentException("Best før datoen kan ikke være tidligere enn dagens dato.");
        }
        this.bestBeforeDate = bestBeforeDate;
    }

    /**
     * Sets the price per unit of the grocery item.
     *
     * @param pricePerUnit the new price per unit
     */
    public void setPricePerUnit(double pricePerUnit) {
        if (pricePerUnit < 0) {
            throw new IllegalArgumentException("Pris per enhet kan ikke være negativ/mindre enn 0");
        }
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Calculates the total price of the grocery item.
     *
     * @return the total price
     */
    public double calculateTotalPrice() {
        return amount * pricePerUnit;
    }

    /**
     * Checks if the grocery item is expired.
     *
     * @return true if the item is expired, false otherwise
     */
    public boolean isExpired() {
        return new Date().after(bestBeforeDate);
    }

    /**
     * Formats a date to "dd.MM.yyyy".
     *
     * @param date the date to format
     * @return the formatted date string
     */
    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return String.format(
            "%s (%.1f %s) - Utløpsdato: %s - Pris per enhet: %.2f NOK",
            name, amount, measuringUnit, formatDate(bestBeforeDate), pricePerUnit
        );
    }
}
