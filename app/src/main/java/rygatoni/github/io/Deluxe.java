package rygatoni.github.io;

import java.util.ArrayList;

/**
 * Class for creating Deluxe-flavored pizzas.
 * Has methods for adding/removing toppings, getting instance variables,
 * and returning the price of the pizza.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class Deluxe extends Pizza{
    /**
     * ArrayList containing the toppings for Deluxe.
     */
    private ArrayList<Topping> toppings;
    /**
     * The crust of the pizza.
     */
    private Crust crust;
    /**
     * The size of the pizza.
     */
    private Size size;

    /**
     * The price of a small Deluxe pizza.
     */
    private static double SMALL_PRICE = 14.99;
    /**
     * The price of a medium Deluxe pizza.
     */
    private static double MEDIUM_PRICE = 16.99;
    /**
     * The price of a large Deluxe pizza.
     */
    private static double LARGE_PRICE = 18.99;

    /**
     * Constructor for a Deluxe pizza.
     * @param crust The crust of the pizza.
     */
    public Deluxe(Crust crust) {
        this.crust = crust;
        this.size = null;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.SAUSAGE);
        this.toppings.add(Topping.PEPPERONI);
        this.toppings.add(Topping.GREEN_PEPPER);
        this.toppings.add(Topping.ONION);
        this.toppings.add(Topping.MUSHROOM);
    }

    /**
     * Returns the price of the pizza.
     */
    public double price() {
        switch(this.size) {
            case SMALL:
                return SMALL_PRICE;
            case MEDIUM:
                return MEDIUM_PRICE;
            case LARGE:
                return LARGE_PRICE;
            default:
                return 0;
        }
    };

    /**
     * Adds toppings to the pizza.
     */
    public boolean add(Object obj) {
        if(!(obj instanceof Topping)) {
            return false;
        }
        return toppings.add((Topping) obj);
    }

    /**
     * Removes toppings from the pizza.
     */
    public boolean remove(Object obj) {
        if(!(obj instanceof Topping)) {
            return false;
        }
        return toppings.remove((Topping) obj);
    }

    /**
     * Returns the type of crust.
     * @return the type of crust
     */
    public Crust getCrust() {
        return this.crust;
    }

    /**
     * Returns the ArrayList of toppings.
     * @return the ArrayList of toppings
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Returns the size of the pizza.
     * @return the size of the pizza.
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Sets the size of the pizza.
     * @param size Size of the pizza.
     */
    public void setSize(Size size) {
        this.size = size;
    }
}
