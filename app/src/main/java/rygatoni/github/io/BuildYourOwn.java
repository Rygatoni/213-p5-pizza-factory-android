package rygatoni.github.io;

import java.util.ArrayList;

/**
 * Class for creating Build Your Own pizzas.
 * Has methods for adding/removing toppings, getting instance variables,
 * and returning the price of the pizza.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class BuildYourOwn extends Pizza {
    /**
     * ArrayList containing the toppings for BuildYourOwn.
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
     * The price of a small BuildYourOwn pizza without any toppings.
     */
    private static final double SMALL_PRICE = 8.99;
    /**
     * The price of a medium BuildYourOwn pizza without any toppings.
     */
    private static final double MEDIUM_PRICE = 10.99;
    /**
     * The price of a large BuildYourOwn pizza without any toppings.
     */
    private static final double LARGE_PRICE = 12.99;
    /**
     * The extra price for each topping.
     */
    private static final double ADDITIONAL_FEE = 1.59;

    /**
     * Constructor for a BuildYourOwn pizza.
     * @param crust The crust of the pizza to be set.
     */
    public BuildYourOwn(Crust crust) {
        this.crust = crust;
        this.size = null;
        this.toppings = new ArrayList<>();
    }

    /**
     * Returns the price of the pizza.
     */
    public double price() {
        switch(this.size) {
            case SMALL:
                return SMALL_PRICE + toppings.size() * ADDITIONAL_FEE;
            case MEDIUM:
                return MEDIUM_PRICE + toppings.size() * ADDITIONAL_FEE;
            case LARGE:
                return LARGE_PRICE + toppings.size() * ADDITIONAL_FEE;
            default:
                return 0;
        }
    }

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
