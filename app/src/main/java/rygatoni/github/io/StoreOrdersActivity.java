package rygatoni.github.io;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {
    /**
     * Format for costs
     */

    private static final DecimalFormat df = new DecimalFormat("0.00");
    /**
     * List view for store orders
     */
    private ListView orderView;
    /**
     * Adapter for Store Orders
     */
    private ArrayAdapter adapter;
    /**
     * Order Selection
     */
    private View currentSelection;
    /**
     * Cancel order Button
     */
    private Button cancel_order_btn;
    /**
     * Initial value of order position
     */
    private int currentPosition = -1;
    /**
     * List of orders as strings
     */
    ArrayList<String> orders;
    /**
     * List of store orders
     */
    ArrayList<Order> storeOrders;

    /**
     * Sets inital settings of Store Orders Activity
     * @param savedInstanceState Current Instance State
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        orders = new ArrayList<>();
        storeOrders = MainActivity.getStoreOrders().getOrders();
        String finalString;
        for(int i = 0; i < storeOrders.size(); i++) {
            finalString = "";
            int currentOrderNumber = storeOrders.get(i).getOrderNumber();
            ArrayList<Pizza> currentOrderPizzas = storeOrders.get(i).getPizzas();
            finalString += "---------------------ORDER #: " + currentOrderNumber
                    + "---------------------\n";
            for(int j = 0; j < currentOrderPizzas.size(); j++) {
                finalString += PizzaActivity.pizzaPrint(currentOrderPizzas.get(j));
            }
            finalString += "                                                Order Total: $"
                    + df.format(storeOrders.get(i).orderTotal());
            orders.add(finalString);
        }
        orderView = findViewById(R.id.orderView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, orders);
        orderView.setAdapter(adapter);
        orderView.setOnItemClickListener(this::onItemClick);

        cancel_order_btn = findViewById(R.id.cancel_order_btn);
        cancel_order_btn_setup();
        updateList();
    }

    /**
     * Sets Alert dialog and cancel order button settings.
     * Alert Dialog will ask if the user is sure of cancelling
     */

    private void cancel_order_btn_setup() {
        cancel_order_btn.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Cancel Order");
            alert.setMessage("Are you sure you would like to cancel this order?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if(currentPosition <= orders.size() - 1) {
                        orders.remove(currentPosition);
                    }
                    if(currentPosition <= storeOrders.size() - 1) {
                        storeOrders.remove(currentPosition);
                    }
                    currentSelection = null;
                    currentPosition = -1;
                    updateList();
                    Toast.makeText(view.getContext(),"Order has been cancelled.", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    private void updateList() {
        if(orders.size() == 0) {
            cancel_order_btn.setBackgroundColor(cancel_order_btn.getResources().getColor(R.color.light_brown));
            cancel_order_btn.setClickable(false);
        } else {
            cancel_order_btn.setBackgroundColor(cancel_order_btn.getResources().getColor(R.color.dark_brown));
            cancel_order_btn.setClickable(true);
        }
        adapter.notifyDataSetChanged();
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        updateList();
        if(currentSelection != null) {
            currentSelection.setBackgroundColor(currentSelection.getResources().getColor(R.color.brown));
        }
        currentSelection = v;
        currentPosition = position;
        v.setBackgroundColor(v.getResources().getColor(R.color.off_brown));
    }
}
