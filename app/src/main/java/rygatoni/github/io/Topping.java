package rygatoni.github.io;

/**
 * The Topping enum stores the different types of toppings.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public enum Topping {
    /**
     * Sausage pizza topping
     */
    SAUSAGE(R.drawable.topping_sausage, 0),
    /**
     * Pepperoni pizza topping
     */
    PEPPERONI(R.drawable.topping_pepperoni, 1),
    /**
     * Green pepper pizza topping
     */
    GREEN_PEPPER(R.drawable.green_pepper_topping, 2),
    /**
     * Onion pizza topping
     */
    ONION(R.drawable.onions_topping, 3),
    /**
     * Mushroom pizza topping
     */
    MUSHROOM(R.drawable.mushrooms_topping, 4),
    /**
     * BBQ chicken pizza topping
     */
    BBQ_CHICKEN(R.drawable.bbqchicken_topping, 5),
    /**
     * Provolone pizza topping
     */
    PROVOLONE(R.drawable.provolone_topping, 6),
    /**
     * Cheddar pizza topping
     */
    CHEDDAR(R.drawable.cheddar_topping, 7),
    /**
     * Beef pizza topping
     */
    BEEF(R.drawable.beef_topping, 8),
    /**
     * Ham pizza topping
     */
    HAM(R.drawable.ham_topping, 9),
    /**
     * Pineapple pizza topping
     */
    PINEAPPLE(R.drawable.pineapple_topping, 10),
    /**
     * Buffalo chicken pizza topping
     */
    BUFFALO_CHICKEN(R.drawable.buffalo_topping, 11),
    /**
     * Meatball pizza topping
     */
    MEATBALL(R.drawable.meatball_topping, 12);


    private int image;
    private int id;

    Topping(int image, int id) {
        this.image = image;
        this.id = id;
    }

    public int getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }
}