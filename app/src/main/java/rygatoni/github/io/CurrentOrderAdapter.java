package rygatoni.github.io;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Sets the views for the RecyclerView.
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public class CurrentOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<Pizza> pizzas;
    private static CurrentOrderActivity activity;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Constructor for the CurrentOrderAdapter
     * @param context The current context
     * @param pizzas The list of pizzas
     * @param activity Parameter for the activity class
     */
    public CurrentOrderAdapter(Context context,ArrayList<Pizza> pizzas, CurrentOrderActivity activity) {
        this.context = context;
        this.pizzas = pizzas;
        this.activity = activity;
    }


    /**
     * Creates the view holder
     * @param parent The parent of the view
     * @param viewType Integer representing the position
     * @return currentOrderHolder
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_pizza, parent, false);
        CurrentOrderHolder currentOrderHolder = new CurrentOrderHolder(view);
        return currentOrderHolder;
    }

    /**
     * Ran when the views are being bound to the ViewHolder.
     * @param holder The holder which contains the view being bound
     * @param position The position of the view
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CurrentOrderHolder) {
           CurrentOrderHolder currentOrderHolder = (CurrentOrderHolder) holder;
           currentOrderHolder.pizza_main.setText(PizzaActivity.pizzaPrintLabel(pizzas.get(position)));
           currentOrderHolder.pizza_toppings.setText(PizzaActivity.pizzaPrintToppings(pizzas.get(position)));
           currentOrderHolder.pizzaTotal.setText("Unit Price: $" + df.format(pizzas.get(position).price()));
           currentOrderHolder.delete_pizza.setOnClickListener(view -> {
               AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
               alert.setTitle("Remove Pizza From Order");
               alert.setMessage("Are you sure you would like to remove this pizza from the order?");
               alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(view.getContext(),"Pizza has been removed.", Toast.LENGTH_SHORT).show();
                       MainActivity.getCurrentOrder().getPizzas().remove(position);
                       activity.updatePizzas();
                   }
               }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
               AlertDialog dialog = alert.create();
               dialog.show();
           });
        }
    }

    /**
     * Returns the amount of pizzas in the order.
     * @return the amount of pizzas in the order
     */
    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    /**
     * Static class for the ViewHolder.
     */
    public static class CurrentOrderHolder extends RecyclerView.ViewHolder {
        /**
         * TextView variables for modifying visuals.
         */
        TextView pizza_main, pizza_toppings, pizzaTotal;
        /**
         * Button for adding click listeners.
         */
        Button delete_pizza;

        /**
         * Constructor for CurrentOrderHolder
         * @param itemView The current view.
         */
        public CurrentOrderHolder(@NonNull View itemView) {
            super(itemView);
            pizza_main = itemView.findViewById(R.id.pizza_main);
            pizza_toppings = itemView.findViewById(R.id.pizza_toppings);
            delete_pizza = itemView.findViewById(R.id.delete_pizza);
            pizzaTotal = itemView.findViewById(R.id.pizzaTotal);
        }
    }
}
