package rygatoni.github.io;
import java.util.ArrayList;

/**
 * The list of orders for RU Pizzeria.
 * Contains the list of orders, and contains methods for
 * adding/removing orders.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class StoreOrders implements Customizable{
    /**
     * List of orders
     */
    private ArrayList<Order> orders;

    /**
     * Constructor for Store Orders.
     * Sets a list of orders as the StoreOrders
     */

    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Gets the list of orders
     * @return A list of orders
     */

    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order if an object is an instance of Order
     * @param obj Object that is checked
     * @return false if the object is not an object.
     * Otherwise, true.
     */
    public boolean add(Object obj) {
        if(!(obj instanceof Order)) {
            return false;
        }
        return this.orders.add((Order) obj);
    }
    /**
     * Removes an order if an object is an instance of Order
     * @param obj Object that is checked
     * @return false if the object is not an object.
     * Otherwise, true.
     */
    public boolean remove(Object obj) {
        if(!(obj instanceof Order)) {
            return false;
        }
        return this.orders.remove((Order) obj);
    }

}
