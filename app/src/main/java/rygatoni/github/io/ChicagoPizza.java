package rygatoni.github.io;

/**
 * Chicago-Style Pizza Factory. Creates the four pizza types in Chicago-style.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class ChicagoPizza implements PizzaFactory {
    /**
     * Returns a Chicago-style Deluxe pizza.
     * @return a Chicago-style Deluxe pizza
     */
    public Pizza createDeluxe() {
        Crust deluxeCrust = Crust.DEEP_DISH;
        Pizza newDeluxe = new Deluxe(deluxeCrust);
        return newDeluxe;
    }

    /**
     * Returns a Chicago-style BBQChicken pizza.
     * @return a Chicago-style BBQChicken pizza
     */
    public Pizza createBBQChicken() {
        Crust bbqCrust = Crust.PAN;
        Pizza newBBQChicken = new BBQChicken(bbqCrust);
        return newBBQChicken;
    };

    /**
     * Returns a Chicago-style Meatzza pizza.
     * @return a Chicago-style Meatzza pizza
     */
    public Pizza createMeatzza() {
        Crust meatzzaCrust = Crust.STUFFED;
        Pizza newMeatzza = new Meatzza(meatzzaCrust);
        return newMeatzza;
    }

    /**
     * Returns a Chicago-style BuildYourOwn pizza.
     * @return a Chicago-style BuildYourOwn pizza
     */
    public Pizza createBuildYourOwn() {
        Crust byoCrust = Crust.PAN;
        Pizza newBuildYourOwn = new BuildYourOwn(byoCrust);
        return newBuildYourOwn;
    }
}
