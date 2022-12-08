package rygatoni.github.io;

import java.util.ArrayList;

/**
 * An RU Pizzeria order.
 * Stores the order number and the list of pizzas.
 * Contains methods for adding/removing pizzas, and returning the subtotal,
 * sales tax, and order total.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class Order implements Customizable{
    /**
     * List of pizzas in an order
     */
    private ArrayList<Pizza> pizzas;
    /**
     * Number of order
     */
    private int orderNumber;
    /**
     * New Jersey Tax Rate
     */
    private static double TAX_RATE = 0.06625;

    /**
     * Constructor for first Order.
     * Sets the pizzas and number of the order.
     */

    public Order() {
        this.pizzas = new ArrayList<>();
        this.orderNumber = 1000;
    }

    /**
     * Constructor for an Order.
     * Sets the pizzas and the inputted order number of an order.
     * @param orderNumber Number of Order
     */
    public Order(int orderNumber) {
        this.pizzas = new ArrayList<>();
        this.orderNumber = orderNumber;
    }

    /**
     * Gets the order number
     * @return The order number
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Gets the pizzas of an order
     * @return Pizza list of an order
     */
    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    /**
     * Adds a pizza to the order
     * @param obj The pizza to be added
     * @return true if the pizza was added, false if otherwise
     */
    public boolean add(Object obj) {
        if(!(obj instanceof Pizza)) {
            return false;
        }
        return this.pizzas.add((Pizza) obj);
    }

    /**
     * Removes a pizza from the order
     * @param obj The pizza to be removed
     * @return true if the pizza was removed, false if otherwise
     */
    public boolean remove(Object obj) {
        if(!(obj instanceof Pizza)) {
            return false;
        }
        return this.pizzas.remove((Pizza) obj);
    }

    /**
     * Returns the subtotal for this order.
     * @return the subtotal for this order
     */
    public double subtotal() {
        double subtotal = 0;
        for(int i = 0; i < this.pizzas.size(); i++) {
            Pizza currentPizza = this.pizzas.get(i);
            double currentPrice = currentPizza.price();
            subtotal += currentPrice;
        }
        return subtotal;
    }

    /**
     * Returns the sales tax for this order.
     * @return the sales tax for this order
     */
    public double salesTax() {
        return subtotal() * TAX_RATE;
    }

    /**
     * Returns the subtotal + sales tax for this order.
     * @return the subtotal + sales tax for this order
     */
    public double orderTotal() {
        return subtotal() + salesTax();
    }

}
