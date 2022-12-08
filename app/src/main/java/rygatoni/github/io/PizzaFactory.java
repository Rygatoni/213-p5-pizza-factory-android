package rygatoni.github.io;

/**
 * Basic interface for pizza styles, allowing for
 * creation of the four pizza types.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public interface PizzaFactory {
    /**
     * Returns a Deluxe pizza.
     * @return a Deluxe pizza
     */
    Pizza createDeluxe();
    /**
     * Returns a Meatzza pizza.
     * @return a Meatzza pizza
     */
    Pizza createMeatzza();
    /**
     * Returns a BBQChicken pizza.
     * @return a BBQChickenDeluxe pizza
     */
    Pizza createBBQChicken();
    /**
     * Returns a BuildYourOwn pizza.
     * @return a BuildYourOwn pizza
     */
    Pizza createBuildYourOwn();
}
