package rygatoni.github.io;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class PizzaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * Current Context
     */
    private Context context;
    /**
     * List of Toppings
     */
    private ArrayList<Topping> toppings;
    /**
     * List of current Toppings
     */
    private ArrayList<Topping> currentToppings = new ArrayList<>();
    /**
     * Current Activity
     */
    private static PizzaActivity activity;
    /**
     * Initial chicago style toggle
     */
    private static final boolean CHICAGO_STYLE = false;
    /**
     * Initial NY style toggle
     */
    private static final boolean NEWYORK_STYLE = true;
    /**
     * Representation of Deluxe pizza
     */

    private static final int DELUXE = 0;
    /**
     * Representation of BBQ Chicken pizza
     */
    private static final int BBQCHICKEN = 1;
    /**
     * Representation of Meatzza pizza
     */
    private static final int MEATZZA = 2;
    /**
     * Representation of Build Your Own pizza
     */
    private static final int BUILDYOUROWN = 3;

    /**
     * Holder for list of Toppings
     */

    private ArrayList<ToppingHolder> toppingHolders = new ArrayList<>();
    /**
     * List of filled toppings
     */
    private ArrayList<Topping> filledToppings = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION,
                    Topping.MUSHROOM, Topping.BBQ_CHICKEN, Topping.PROVOLONE, Topping.CHEDDAR,
                    Topping.BEEF, Topping.HAM, Topping.PINEAPPLE, Topping.BUFFALO_CHICKEN, Topping.MEATBALL));

    /**
     * Sets context, toppings list, current toppings list, and pizza activity
     * based on arguments
     * @param context Context that is to be set
     * @param toppings Toppings that is to be set
     * @param currentToppings Current toppings that is to be set
     * @param activity Pizza activity that is to be set
     */

    public PizzaAdapter(Context context, ArrayList<Topping> toppings, ArrayList<Topping> currentToppings, PizzaActivity activity) {
        this.context = context;
        this.toppings = toppings;
        this.currentToppings = currentToppings;
        this.activity = activity;
    }

    /**
     * Creates the view holder.
     * @param parent The parent of the view
     * @param viewType Integer representing the position
     * @return ViewHolder
     */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        if(viewType == 0) {
            view = inflater.inflate(R.layout.row_pizzafactory, parent, false);
            return new PizzaHolder(view);
        } else if (viewType == getItemCount() - 1) {
            view = inflater.inflate(R.layout.row_padding, parent, false);
            return new PaddingHolder(view);
        }
        view = inflater.inflate(R.layout.row_toppings, parent, false);
        ToppingHolder toppingHolder = new ToppingHolder(view);
        toppingHolders.add(toppingHolder);
        return toppingHolder;
    }

    /**
     * Ran when the views are being bound to the ViewHolder.
     * @param holder The holder which contains the view being bound
     * @param position The position of the view
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ToppingHolder) {
            ToppingHolder toppingHolder = (ToppingHolder) holder;
            toppingHolder.topping_image.setImageResource(toppings.get(position - 1).getImage());
            toppingHolder.topping_label.setText(toppings.get(position - 1).toString().replace("_", " "));
            if(currentToppings.size() > 6) {
                if(currentToppings.contains(toppings.get(position - 1))) {
                    toppingHolder.topping_view.setCardBackgroundColor(toppingHolder.topping_view.getResources().getColor(R.color.offer_brown));
                    toppingHolder.topping_switch.setVisibility(View.VISIBLE);
                } else {
                    toppingHolder.topping_view.setCardBackgroundColor(toppingHolder.topping_view.getResources().getColor(R.color.light_brown));
                    toppingHolder.topping_switch.setVisibility(View.INVISIBLE);
                }
            } else {
                if(currentToppings.contains(toppings.get(position - 1))) {
                    toppingHolder.topping_view.setCardBackgroundColor(toppingHolder.topping_view.getResources().getColor(R.color.offer_brown));
                } else {
                    toppingHolder.topping_view.setCardBackgroundColor(toppingHolder.topping_view.getResources().getColor(R.color.off_brown));
                    toppingHolder.topping_switch.setVisibility(View.VISIBLE);
                }
            }
            toppingHolder.topping_switch.setOnClickListener(view -> {
                activity.updateList();
                if(toppingHolder.topping_switch.isChecked()) {
                    if(currentToppings.size() < 6) {
                        addTopping(currentToppings, filledToppings.get(position - 1));
                    } else {
                        addTopping(currentToppings, filledToppings.get(position - 1));
                    }
                } else {
                    if(currentToppings.size() > 6) {
                        currentToppings.remove(filledToppings.get(position - 1));
                    } else {
                        currentToppings.remove(filledToppings.get(position - 1));
                    }
                }
                activity.updateTotal();
            });
        }
    }

    /**
     * Adds a topping to the current topping list.
     * @param currentToppings The arraylist of current toppings.
     * @param topping The topping to add.
     */
    private void addTopping(ArrayList<Topping> currentToppings, Topping topping) {
        if(currentToppings.size() == 0) {
            currentToppings.add(topping);
            return;
        }
        for(int i = 0; i < currentToppings.size(); i++) {
            if(topping.getId() < currentToppings.get(i).getId()) {
                currentToppings.add(i, topping);
                return;
            }
        }
        currentToppings.add(topping);
    }

    /**
     * Returns the position of the view.
     * @param position integer
     * @return The position
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Returns the amount of pizzas in the order.
     * @return the amount of pizzas in the order
     */
    @Override
    public int getItemCount() {
        return toppings.size() + 1;
    }

    /**
     * Contains all of the views for PizzaActivity
     */
    public static class PizzaHolder extends RecyclerView.ViewHolder {
        private ImageView pizzaView;
        private Switch style_switch;
        private Spinner size_spinner;
        private CardView deluxe, bbqChicken, meatzza, byo;
        private TextView deluxe_desc, bbqchicken_desc, meatzza_desc, byo_desc;
        private Button add_order_btn;
        public PizzaHolder(@NonNull View itemView) {
            super(itemView);
            pizzaView = itemView.findViewById(R.id.pizzaView);

            style_switch = itemView.findViewById(R.id.style_switch);

            size_spinner = itemView.findViewById(R.id.sizeSpinner);
            spinnerSetup(itemView, size_spinner);

            deluxe = itemView.findViewById(R.id.deluxe_view);
            bbqChicken = itemView.findViewById(R.id.bbqchicken_view);
            meatzza = itemView.findViewById(R.id.meatzza_view);
            byo = itemView.findViewById(R.id.byo_view);

            deluxe_desc = itemView.findViewById(R.id.deluxe_desc);
            bbqchicken_desc = itemView.findViewById(R.id.bbqchicken_desc);
            meatzza_desc = itemView.findViewById(R.id.meatzza_desc);
            byo_desc = itemView.findViewById(R.id.byo_desc);
            CardView[] flavors = {deluxe, bbqChicken, meatzza, byo};
            deluxeSetup(itemView, deluxe, flavors);
            bbqSetup(itemView, bbqChicken, flavors);
            meatzzaSetup(itemView, meatzza, flavors);
            byoSetup(itemView, byo, flavors);
            switchSetup(itemView, style_switch, deluxe_desc, bbqchicken_desc, meatzza_desc, byo_desc, pizzaView);
        }
    }

    /**
     * ViewHolder for row_toppings.xml
     */
    public static class ToppingHolder extends RecyclerView.ViewHolder {
        ImageView topping_image;
        TextView topping_label;
        Switch topping_switch;
        CardView topping_view;

        /**
         * Constructor for ToppingHolder
         * @param itemView The view containing the items
         */
        public ToppingHolder(@NonNull View itemView) {
            super(itemView);
            topping_image = itemView.findViewById(R.id.topping_image);
            topping_label = itemView.findViewById(R.id.pizza_main);
            topping_switch = itemView.findViewById(R.id.topping_switch);
            topping_view = itemView.findViewById(R.id.topping_view);
        }
    }

    /**
     * ViewHolder for empty white space.
     */
    public static class PaddingHolder extends RecyclerView.ViewHolder {
        /**
         * Constructor for PaddingHolder
         * @param itemView The view containing the items
         */
        public PaddingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * Changes descriptions and images when the switch is toggled.
     * @param itemView The view containing the items.
     * @param style_switch The switch that toggles the pizza style.
     * @param deluxe View for Deluxe pizza
     * @param bbqChicken View for BBQ Chicken pizza
     * @param meatzza View for Meatzza pizza
     * @param byo View for Build Your Own pizza
     * @param pizzaView The pizza preview image
     */
    private static void switchSetup(View itemView, Switch style_switch, TextView deluxe, TextView bbqChicken, TextView meatzza, TextView byo, ImageView pizzaView) {
        style_switch.setOnClickListener(view -> {
            if(style_switch.isChecked()) {
                activity.setStyle(NEWYORK_STYLE);
                deluxe.setText(R.string.deluxenewyork);
                bbqChicken.setText(R.string.bbqchickennewyork);
                meatzza.setText(R.string.meatzzanewyork);
                byo.setText(R.string.byonewyork);
                pizzaView.setImageResource(R.drawable.newyork);
            } else {
                activity.setStyle(CHICAGO_STYLE);
                deluxe.setText(R.string.deluxechicago);
                bbqChicken.setText(R.string.bbqchickenchicago);
                meatzza.setText(R.string.meatzzachicago);
                byo.setText(R.string.byochicago);
                pizzaView.setImageResource(R.drawable.chicago);
            }
        });
    }

    /**
     * Sets up the spinner for choosing the size.
     * @param itemView The view that contains all of the items.
     * @param size_spinner The spinner which lets you choose the size
     */
    private static void spinnerSetup(View itemView, Spinner size_spinner) {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(itemView.getContext(),
                R.array.size_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        size_spinner.setAdapter(spinnerAdapter);
        size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(size_spinner.getSelectedItem() == null) {
                    return;
                }
                if(size_spinner.getSelectedItem().equals("SMALL")) {
                    activity.setSize(Size.SMALL);
                } else if(size_spinner.getSelectedItem().equals("MEDIUM")) {
                    activity.setSize(Size.MEDIUM);
                } else {
                    activity.setSize(Size.LARGE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                if(size_spinner.getSelectedItem() == "SMALL") {
                    activity.setSize(Size.SMALL);
                } else if(size_spinner.getSelectedItem() == "MEDIUM") {
                    activity.setSize(Size.MEDIUM);
                } else {
                    activity.setSize(Size.LARGE);
                }
            }
        });
    }

    /**
     * Setup for Deluxe flavored pizza.
     * @param itemView The view which contains all the items.
     * @param deluxe The view for Deluxe flavored pizza
     * @param flavors The list of flavors
     */
    public static void deluxeSetup(View itemView, CardView deluxe, CardView[] flavors) {
        deluxe.setOnClickListener(view -> {
            for(int i = 0; i < flavors.length; i++) {
                flavors[i].setCardBackgroundColor(itemView.getResources().getColor(R.color.off_brown));
            }
            deluxe.setCardBackgroundColor(itemView.getResources().getColor(R.color.offer_brown));
            activity.setFlavor(DELUXE);
            activity.byoToggle(false);
        });
    }

    /**
     * Setup for BBQChicken flavored pizza.
     * @param itemView The view which contains all the items.
     * @param bbqChicken The view for BBQChicken flavored pizza
     * @param flavors The list of flavors
     */
    public static void bbqSetup(View itemView, CardView bbqChicken, CardView[] flavors) {
        bbqChicken.setOnClickListener(view -> {
            for(int i = 0; i < flavors.length; i++) {
                flavors[i].setCardBackgroundColor(itemView.getResources().getColor(R.color.off_brown));
            }
            bbqChicken.setCardBackgroundColor(itemView.getResources().getColor(R.color.offer_brown));
            activity.setFlavor(BBQCHICKEN);
            activity.byoToggle(false);
        });
    }

    /**
     * Setup for Meatzza flavored pizza.
     * @param itemView The view which contains all the items.
     * @param meatzza The view for Meatzza flavored pizza
     * @param flavors The list of flavors
     */
    public static void meatzzaSetup(View itemView, CardView meatzza, CardView[] flavors) {
        meatzza.setOnClickListener(view -> {
            for(int i = 0; i < flavors.length; i++) {
                flavors[i].setCardBackgroundColor(itemView.getResources().getColor(R.color.off_brown));
            }
            meatzza.setCardBackgroundColor(itemView.getResources().getColor(R.color.offer_brown));
            activity.setFlavor(MEATZZA);
            activity.byoToggle(false);
        });
    }

    /**
     * Setup for BuildYourOwn flavored pizza.
     * @param itemView The view which contains all the items.
     * @param byo The view for Build Your Own flavored pizza
     * @param flavors The list of flavors
     */
    public static void byoSetup(View itemView, CardView byo, CardView[] flavors) {
        byo.setOnClickListener(view -> {
            for(int i = 0; i < flavors.length; i++) {
                flavors[i].setCardBackgroundColor(itemView.getResources().getColor(R.color.off_brown));
            }
            byo.setCardBackgroundColor(itemView.getResources().getColor(R.color.offer_brown));
            activity.setFlavor(BUILDYOUROWN);
            activity.byoToggle(true);
        });
    }
}
