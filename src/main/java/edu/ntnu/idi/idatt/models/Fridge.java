package edu.ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a fridge to manage grocery items.
 */
public class Fridge {
    private List<Grocery> items;

    /**
     * Constructs a fridge with an empty list of items.
     */
    public Fridge() {
        this.items = new ArrayList<>();
    }

    /**
     * Returns all items in the fridge.
     *
     * @return a list of groceries in the fridge
     */
    public List<Grocery> getItems() {
        return items;
    }

    /**
     * Adds an item to the fridge.
     *
     * @param item the grocery to add
     */
    public void addItem(Grocery item) {
        items.add(item);
        System.out.println(item.getName() + " er lagt til i kjøleskapet");
    }

    /**
     * Removes a whole item from the fridge.
     *
     * @param item the grocery to remove
     */
    public void removeWholeItem(Grocery item) {
        if (items.remove(item)) {
            System.out.println(item.getName() + " er fjernet fra kjøleskapet");
        } else {
            System.out.println(item.getName() + " finnes ikke i kjøleskapet");
        }
    }

    /**
     * Displays all items in the fridge.
     */
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Kjøleskapet er tomt");
        } else {
            System.out.println("Varer i kjøleskapet:");
            for (Grocery item : items) {
                System.out.println(item);
            }
        }
    }

    /**
     * Displays items expiring within a certain number of days.
     *
     * @param daysUntilExpiry the number of days to check for expiry
     */
    public void displayExpiringSoon(int daysUntilExpiry) {
        System.out.println("Varer som går ut på dato innen " + daysUntilExpiry + " dager:");
        for (Grocery item : items) {
            if (item.getDaysUntilExpiry() <= daysUntilExpiry) {
                System.out.println(item);
            }
        }
    }

    /**
     * Searches for an item by name.
     *
     * @param name the name of the grocery to search for
     * @return the grocery if found, otherwise null
     */
    public Grocery searchItem(String name) {
        for (Grocery item : items) {
            if (item.getName().equalsIgnoreCase(name.trim())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Calculates the total value of items in the fridge.
     *
     * @return the total value of groceries
     */
    public double calculateTotalValue() {
        double totalValue = 0;
        for (Grocery item : items) {
            totalValue += item.getAmount() * item.getPricePerUnit();
        }
        return totalValue;
    }

    /**
     * Removes a specific amount of a grocery item from the fridge.
     *
     * @param itemName      the name of the grocery
     * @param amountToRemove the amount to remove
     */
    public void removeItemByAmount(String itemName, double amountToRemove) {
        Iterator<Grocery> iterator = items.iterator();

        while (iterator.hasNext()) {
            Grocery item = iterator.next();

            if (item.getName().equalsIgnoreCase(itemName.trim())) {
                System.out.println("Varen " + item.getName() + " ble funnet! Opprinnelig mengde: " + item.getAmount());

                if (item.getAmount() >= amountToRemove) {
                    double newAmount = item.getAmount() - amountToRemove;
                    item.setAmount(newAmount);

                    System.out.println("Mengden etter fjerning: " + newAmount);

                    if (newAmount <= 0) {
                        iterator.remove();
                        System.out.println(itemName + " ble fjernet helt fra kjøleskapet.");
                    }
                } else {
                    System.out.println("Kan ikke fjerne " + amountToRemove + " av varen " + itemName +
                            " fordi du har kun " + item.getAmount() + " igjen.");
                }
                return;
            }
        }
        System.out.println(itemName + " finnes ikke i kjøleskapet.");
    }

    /**
     * Displays expired items and their total value.
     */
    public void displayExpiredItemsAndTotalValue() {
        double totalValue = 0;
        boolean foundExpiredItems = false;

        System.out.println("Varer som har gått ut på dato:");

        for (Grocery item : items) {
            if (item.getDaysUntilExpiry() <= 0) {
                System.out.println(item);
                totalValue += item.getPricePerUnit() * item.getAmount();
                foundExpiredItems = true;
            }
        }

        if (foundExpiredItems) {
            System.out.println("Den samlede verdien av utgåtte varer er " + totalValue + " NOK");
        } else {
            System.out.println("Ingen varer har gått ut på dato enda");
        }
    }

    /**
     * Returns a sorted list of items by name.
     *
     * @return a sorted list of groceries by name
     */
    public List<Grocery> getSortedItemsByName() {
        return items.stream()
                .sorted(Comparator.comparing(item -> item.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Displays all expired items.
     *
     * @return a list of expired items
     */
    public List<Grocery> displayExpiredItems() {
        return items.stream()
                .filter(item -> item.getDaysUntilExpiry() <= 0)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    /**
     * Removes all expired items from the fridge.
     *
     * @return a list of removed expired items
     */
    public List<Grocery> removeAllExpiredItems() {
        List<Grocery> expiredItems = items.stream()
                .filter(item -> item.getDaysUntilExpiry() <= 0)
                .peek(item -> System.out.println(item + " er fjernet fra kjøleskapet"))
                .collect(Collectors.toList());
        items.removeAll(expiredItems);
        return expiredItems;
    }

    /**
     * Returns a sorted list of items with a specific name by expiry date.
     *
     * @param name the name of the grocery
     * @return a sorted list of groceries by expiry date
     */
    public List<Grocery> getSortedItemsByExpiry(String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .sorted(Comparator.comparing(Grocery::getBestBeforeDate))
                .collect(Collectors.toList());
    }
}
