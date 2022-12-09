package rygatoni.github.io;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * The first activity ran by the RU Pizzeria app. Holds buttons to all of the activities.
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Buttons to access the rest of the activities.
     */
    private Button add_btn, current_orders_btn, store_orders_btn;
    /**
     * The current order.
     */
    private static Order order = new Order();
    /**
     * The current store orders.
     */
    private static StoreOrders currentStoreOrders = new StoreOrders();
    /**
     * The current order number.
     */
    private static int currentOrderNumber = 1000;

    /**
     * Ran when MainActivity is initizlied.
     * @param savedInstanceState The current savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), PizzaActivity.class);
            view.getContext().startActivity(intent);

        });
        current_orders_btn = findViewById(R.id.current_orders_btn);
        current_orders_btn.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CurrentOrderActivity.class);
            view.getContext().startActivity(intent);
        });
        store_orders_btn = findViewById(R.id.store_orders_btn);
        store_orders_btn.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StoreOrdersActivity.class);
            view.getContext().startActivity(intent);
        });
    }

    /**
     * Returns the current store orders.
     * @return the current store orders
     */
    public static StoreOrders getStoreOrders() {return currentStoreOrders;}

    /**
     * Returns the current order.
     * @return the current order
     */
    public static Order getCurrentOrder() {
        return order;
    }

    /**
     * Returns the current order number.
     * @return the current order number
     */
    public static int getCurrentOrderNumber() {
        return currentOrderNumber;
    }

    /**
     * Places the order into store orders.
     */
    public static void placeOrder() {
        currentStoreOrders.add(order);
        currentOrderNumber++;
        order = new Order(currentOrderNumber);
    }

    /**
     * Clears the current order.
     */
    public static void clearOrder() {
        order.getPizzas().clear();
    }
}