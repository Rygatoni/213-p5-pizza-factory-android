package rygatoni.github.io;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaActivity extends AppCompatActivity {
    Button add_order_btn;

    private static final boolean CHICAGO_STYLE = false;
    private static final boolean NEWYORK_STYLE = true;

    private static final int DELUXE = 0;
    private static final int BBQCHICKEN = 1;
    private static final int MEATZZA = 2;
    private static final int BUILDYOUROWN = 3;

    private boolean style = CHICAGO_STYLE;
    private Size size = Size.SMALL;
    private int flavor = DELUXE;

    /**
     * Price for deluxe small pizza
     */
    private static final double DELUXE_SMALL = 14.99;
    /**
     * Price for deluxe medium pizza
     */
    private static final double DELUXE_MEDIUM = 16.99;
    /**
     * Price for deluxe large pizza
     */
    private static final double DELUXE_LARGE = 18.99;
    /**
     * Price for BBQ small pizza
     */

    private static final double BBQ_SMALL = 13.99;
    /**
     * Price for BBQ medium pizza
     */
    private static final double BBQ_MEDIUM = 15.99;
    /**
     * Price for BBQ large pizza
     */
    private static final double BBQ_LARGE = 17.99;
    /**
     * Price for meatzza small pizza
     */


    private static final double MEATZZA_SMALL = 15.99;
    /**
     * Price for meatzza medium pizza
     */
    private static final double MEATZZA_MEDIUM = 17.99;
    /**
     * Price for meatzza large pizza
     */
    private static final double MEATZZA_LARGE = 19.99;
    /**
     * Price for Build Your Own small pizza
     */

    private static final double BYO_SMALL = 8.99;
    /**
     * Price for Build Your Own medium pizza
     */
    private static final double BYO_MEDIUM = 10.99;
    /**
     * Price for Build Your Own large pizza
     */
    private static final double BYO_LARGE = 12.99;
    /**
     * Additional fee amount
     */
    private static final double ADDITIONAL_FEE = 1.59;
    /**
     * Decimal Format for prices
     */

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private ArrayList<Topping> toppings = new ArrayList<>();
    private ArrayList<Topping> filledToppings = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION,
                    Topping.MUSHROOM, Topping.BBQ_CHICKEN, Topping.PROVOLONE, Topping.CHEDDAR,
                    Topping.BEEF, Topping.HAM, Topping.PINEAPPLE, Topping.BUFFALO_CHICKEN, Topping.MEATBALL, Topping.MEATBALL));
    private ArrayList<Topping> currentToppings = new ArrayList<>();
    private PizzaAdapter adapter;

    /**
     * Sets initial Pizza Activity view
     * @param savedInstanceState Current Saved Instance State
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
        add_order_btn = findViewById(R.id.add_order_btn);
        RecyclerView rcView_add = findViewById(R.id.rcView_add);
        adapter = new PizzaAdapter(this, toppings, currentToppings,this);
        rcView_add.setAdapter(adapter);
        rcView_add.setLayoutManager(new LinearLayoutManager(this));
        add_order_btn.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Add Pizza To Order");
            alert.setMessage("Are you sure you would like to add this pizza to the order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    add_pizza();
                    Toast.makeText(view.getContext(),"Pizza has been added.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), CurrentOrderActivity.class);
                    view.getContext().startActivity(intent);
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    /**
     * Adds a pizza to an order
     */

    private void add_pizza() {
        Pizza finalPizza = null;
        PizzaFactory pizzaFactory;
        if(this.style == NEWYORK_STYLE) {
            pizzaFactory = new NewYorkPizza();
        } else {
            pizzaFactory = new ChicagoPizza();
        }
        switch(flavor) {
            case DELUXE:
                finalPizza = pizzaFactory.createDeluxe();
                finalPizza.setSize(this.size);
                MainActivity.getCurrentOrder().add(finalPizza);
                break;
            case BBQCHICKEN:
                finalPizza = pizzaFactory.createBBQChicken();
                finalPizza.setSize(this.size);
                MainActivity.getCurrentOrder().add(finalPizza);
                break;
            case MEATZZA:
                finalPizza = pizzaFactory.createMeatzza();
                finalPizza.setSize(this.size);
                MainActivity.getCurrentOrder().add(finalPizza);
                break;
            case BUILDYOUROWN:
                finalPizza = pizzaFactory.createBuildYourOwn();
                finalPizza.setSize(this.size);
                for (Topping currentTopping : currentToppings) {
                    finalPizza.add(currentTopping);
                }
                MainActivity.getCurrentOrder().add(finalPizza);
                break;
        }
    }

    /**
     * Gets the style of Pizza based on the crust
     * @param currentPizza Current Pizza
     * @return The style of pizza
     */

    private static String getPizzaType(Pizza currentPizza){
        switch(currentPizza.getCrust()) {
            case DEEP_DISH:
            case PAN:
            case STUFFED:
                return "CHICAGO STYLE";
            case BROOKLYN:
            case THIN:
            case HAND_TOSSED:
                return "NEW YORK STYLE";
            default:
                return null;
        }
    }

    /**
     * Converts a pizza into a string
     * @param currentPizza Current pizza
     * @return Current pizza as a string with its ingredients
     */
    public static String pizzaPrint(Pizza currentPizza) {
        String finalString = "";
        String pizzaType = getPizzaType(currentPizza);
        String flavorName = currentPizza.getClass().toString().substring(currentPizza.getClass().toString().lastIndexOf('.') + 1).toUpperCase();
        finalString += pizzaType + " - " + flavorName + " - " + currentPizza.getCrust() + " - " + currentPizza.getSize() + "\n";
        for(int j = 0; j < currentPizza.getToppings().size(); j++) {
            finalString += "       -" + currentPizza.getToppings().get(j) + "\n";
        }
        return finalString;
    }

    /**
     * Converts a pizza into a string
     * @param currentPizza Current pizza
     * @return Current pizza as a string. (no toppings)
     */

    public static String pizzaPrintLabel(Pizza currentPizza) {
        String finalString = "";
        String pizzaType = getPizzaType(currentPizza);
        String flavorName = currentPizza.getClass().toString().substring(currentPizza.getClass().toString().lastIndexOf('.') + 1).toUpperCase();
        finalString += pizzaType + " - " + flavorName + " - " + currentPizza.getCrust() + " - " + currentPizza.getSize();
        return finalString.replace("_", " ");
    }

    /**
     * Gets the toppings of a pizza as a sting
     * @param currentPizza Current pizza
     * @return Pizza toppings as a string
     */

    public static String pizzaPrintToppings(Pizza currentPizza) {
        String finalString = "";
        for(int j = 0; j < currentPizza.getToppings().size(); j++) {
            finalString += "-" + currentPizza.getToppings().get(j) + "\n";
        }
        return finalString.replace("_", " ");
    }

    /**
     * Updates pizza adapter list
     */
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    /**
     * Allows toppings to be added if build your own pizza is toggled
     * @param toggled Build Your Own Toggle
     */

    public void byoToggle(boolean toggled) {
        if(toggled) {
            toppings.addAll(filledToppings);
            adapter.notifyDataSetChanged();
        } else {
            toppings.clear();
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Sets pizza style based on the toggle
     * @param style Pizza Style
     */

    public void setStyle(boolean style) {
        this.style = style;
    }

    /**
     * Sets the size of the pizza and updates the cost
     * @param size Size of pizza
     */

    public void setSize(Size size) {
        this.size = size;
        updateTotal();
    }

    /**
     * Sets the flavor of the pizza and updates the cost
     * @param flavor Pizza flavor
     */

    public void setFlavor(int flavor) {
        this.flavor = flavor;
        updateTotal();
    }

    /**
     * Updates the total cost of a pizza
     */

    public void updateTotal() {
        String total = null;
        switch(flavor) {
            case DELUXE:
                if(this.size == Size.SMALL) {
                    total = df.format(DELUXE_SMALL);
                } else if(this.size == Size.MEDIUM) {
                    total = df.format(DELUXE_MEDIUM);
                } else {
                    total = df.format(DELUXE_LARGE);
                }
                break;
            case BBQCHICKEN:
                if(this.size == Size.SMALL) {
                    total = df.format(BBQ_SMALL);
                } else if(this.size == Size.MEDIUM) {
                    total = df.format(BBQ_MEDIUM);
                } else {
                    total = df.format(BBQ_LARGE);
                }
                break;
            case MEATZZA:
                if(this.size == Size.SMALL) {
                    total = df.format(MEATZZA_SMALL);
                } else if(this.size == Size.MEDIUM) {
                    total = df.format(MEATZZA_MEDIUM);
                } else {
                    total = df.format(MEATZZA_LARGE);
                }
                break;
            case BUILDYOUROWN:
                if(this.size == Size.SMALL) {
                    total = df.format(BYO_SMALL + ADDITIONAL_FEE * currentToppings.size());
                } else if(this.size == Size.MEDIUM) {
                    total = df.format(BYO_MEDIUM + ADDITIONAL_FEE * currentToppings.size());
                } else {
                    total = df.format(BYO_LARGE + ADDITIONAL_FEE * currentToppings.size());
                }
                break;
        }
        add_order_btn.setText("Total: $" + total + " - ADD TO ORDER");
    }
}
