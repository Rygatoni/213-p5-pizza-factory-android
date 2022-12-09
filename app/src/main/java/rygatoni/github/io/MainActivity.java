package rygatoni.github.io;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button add_btn, current_orders_btn, store_orders_btn;
    private static Order order = new Order();
    private static StoreOrders currentStoreOrders = new StoreOrders();
    private static int currentOrderNumber = 1000;

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

    public static StoreOrders getStoreOrders() {return currentStoreOrders;}

    public static Order getCurrentOrder() {
        return order;
    }

    public static int getCurrentOrderNumber() {
        return currentOrderNumber;
    }

    public static void placeOrder() {
        currentStoreOrders.add(order);
        currentOrderNumber++;
        order = new Order(currentOrderNumber);
    }

    public static void clearOrder() {
        order.getPizzas().clear();
    }
}