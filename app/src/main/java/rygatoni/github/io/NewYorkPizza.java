package rygatoni.github.io;

/**
 * New York-Style Pizza Factory. Creates the four pizza types in New York-style.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class NewYorkPizza implements PizzaFactory{
    /**
     * Returns a New York-style Deluxe pizza.
     * @return a New York-style Deluxe pizza
     */
    public Pizza createDeluxe() {
        Crust deluxeCrust = Crust.BROOKLYN;
        Pizza newDeluxe = new Deluxe(deluxeCrust);
        return newDeluxe;
    }
    /**
     * Returns a New York-style BBQChicken pizza.
     * @return a New York-style BBQChicken pizza
     */
    public Pizza createBBQChicken() {
        Crust bbqCrust = Crust.THIN;
        Pizza newBBQChicken = new BBQChicken(bbqCrust);
        return newBBQChicken;
    };
    /**
     * Returns a New York-style Meatzza pizza.
     * @return a New York-style Meatzza pizza
     */
    public Pizza createMeatzza() {
        Crust meatzzaCrust = Crust.HAND_TOSSED;
        Pizza newMeatzza = new Meatzza(meatzzaCrust);
        return newMeatzza;
    }
    /**
     * Returns a New York-style BuildYourOwn pizza.
     * @return a New York-style BuildYourOwn pizza
     */
    public Pizza createBuildYourOwn() {
        Crust byoCrust = Crust.HAND_TOSSED;
        Pizza newBuildYourOwn = new BuildYourOwn(byoCrust);
        return newBuildYourOwn;
    }
}
