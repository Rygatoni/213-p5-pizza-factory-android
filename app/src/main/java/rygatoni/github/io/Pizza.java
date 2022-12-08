package rygatoni.github.io;

import java.util.ArrayList;

/**
 * The Pizza abstract class contains attributes of the
 * Pizza Object as well as methods to retrieve details of a pizza.
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public abstract class Pizza implements Customizable {
    /**
     * List of toppings
     */
    private ArrayList<Topping> toppings;
    /**
     * Pizza Crust
     */
    private Crust crust;
    /**
     * Pizza size
     */
    private Size size;

    /**
     * Returns the price of a pizza
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Returns the ArrayList of toppings.
     * @return the ArrayList of toppings
     */

    public abstract ArrayList<Topping> getToppings();

    /**
     * Gets the size of a pizza
     * @return Size of the pizza
     */
    public abstract Size getSize();

    /**
     * Sets the size of a pizza
     * @param size Pizza size
     */
    public abstract void setSize(Size size);

    /**
     * Gets the crust of a pizza
     * @return Crust of the pizza
     */

    public abstract Crust getCrust();
}
