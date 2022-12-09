package rygatoni.github.io;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

/**
 * Controls the current order activity.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class CurrentOrderActivity extends AppCompatActivity {
    /**
     * Text views for setting view data.
     */
    TextView order_number, subtotal_label, tax_label, total_label;
    /**
     * Buttons for setting click listeners
     */
    Button clear_orders_btn, place_order_btn;
    /**
     * Allows the user to scroll through the different orders
     */
    RecyclerView orderList;

    /**
     * Sets the views for the RecyclerView
     */
    CurrentOrderAdapter adapter;

    /**
     * Formats the numbers for use as cash values.
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Sets up the android widgets once the activity has started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        order_number = findViewById(R.id.order_number);
        order_number.setText("Order #" + MainActivity.getCurrentOrderNumber());

        subtotal_label = findViewById(R.id.subtotal_label);
        tax_label = findViewById(R.id.tax_label);
        total_label = findViewById(R.id.total_label);
        updatePrices();

        clear_orders_btn = findViewById(R.id.clear_orders_btn);
        clear_orders_btn_setup();
        place_order_btn = findViewById(R.id.place_order_btn);
        place_order_btn_setup();
        updateButtons();

        adapter = new CurrentOrderAdapter(this, MainActivity.getCurrentOrder().getPizzas(), this);
        orderList = findViewById(R.id.orderList);
        orderList.setAdapter(adapter);
        orderList.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Sets up the Clear Orders button.
     */
    public void clear_orders_btn_setup() {
        clear_orders_btn.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Clear Order");
            alert.setMessage("Would you like to clear all pizzas from this order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(view.getContext(),"Order has been cleared.", Toast.LENGTH_SHORT).show();
                    MainActivity.clearOrder();
                    updatePizzas();
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
     * Sets up the Place Order button
     */
    public void place_order_btn_setup() {
        place_order_btn.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Place Order");
            alert.setMessage("Are you sure you want to place this order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(view.getContext(),"Order has been placed.", Toast.LENGTH_SHORT).show();
                    MainActivity.placeOrder();
                    Intent intent = new Intent(view.getContext(), StoreOrdersActivity.class);
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
     * Updates the pizza list.
     */
    public void updatePizzas() {
        adapter.notifyDataSetChanged();
        updateButtons();
        updatePrices();
    }

    /**
     * Changes the color and clickability of buttons when selected.
     */
    public void updateButtons() {
        if(MainActivity.getCurrentOrder().getPizzas().size() == 0) {
            place_order_btn.setClickable(false);
            place_order_btn.setBackgroundColor(this.getResources().getColor(R.color.light_brown));
            clear_orders_btn.setClickable(false);
            clear_orders_btn.setBackgroundColor(this.getResources().getColor(R.color.light_brown));
        } else {
            place_order_btn.setClickable(true);
            place_order_btn.setBackgroundColor(this.getResources().getColor(R.color.dark_brown));
            clear_orders_btn.setClickable(true);
            clear_orders_btn.setBackgroundColor(this.getResources().getColor(R.color.dark_brown));
        }
    }

    /**
     * Updates the price values.
     */
    public void updatePrices() {
        subtotal_label.setText(df.format(MainActivity.getCurrentOrder().subtotal()));
        tax_label.setText(df.format(MainActivity.getCurrentOrder().salesTax()));
        total_label.setText(df.format(MainActivity.getCurrentOrder().orderTotal()));
    }

}
