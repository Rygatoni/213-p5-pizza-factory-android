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

    private Context context;
    private ArrayList<Topping> toppings;
    private ArrayList<Topping> currentToppings = new ArrayList<>();
    private static PizzaActivity activity;

    private static final boolean CHICAGO_STYLE = false;
    private static final boolean NEWYORK_STYLE = true;

    private static final int DELUXE = 0;
    private static final int BBQCHICKEN = 1;
    private static final int MEATZZA = 2;
    private static final int BUILDYOUROWN = 3;

    private ArrayList<ToppingHolder> toppingHolders = new ArrayList<>();
    private ArrayList<Topping> filledToppings = new ArrayList<>(
            Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION,
                    Topping.MUSHROOM, Topping.BBQ_CHICKEN, Topping.PROVOLONE, Topping.CHEDDAR,
                    Topping.BEEF, Topping.HAM, Topping.PINEAPPLE, Topping.BUFFALO_CHICKEN, Topping.MEATBALL));

    public PizzaAdapter(Context context, ArrayList<Topping> toppings, ArrayList<Topping> currentToppings, PizzaActivity activity) {
        this.context = context;
        this.toppings = toppings;
        this.currentToppings = currentToppings;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        System.out.println("Test" + viewType);
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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return toppings.size() + 1;
    }

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

    public static class ToppingHolder extends RecyclerView.ViewHolder {
        ImageView topping_image;
        TextView topping_label;
        Switch topping_switch;
        CardView topping_view;

        public ToppingHolder(@NonNull View itemView) {
            super(itemView);
            topping_image = itemView.findViewById(R.id.topping_image);
            topping_label = itemView.findViewById(R.id.pizza_main);
            topping_switch = itemView.findViewById(R.id.topping_switch);
            topping_view = itemView.findViewById(R.id.topping_view);
        }
    }

    public static class PaddingHolder extends RecyclerView.ViewHolder {

        public PaddingHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
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
