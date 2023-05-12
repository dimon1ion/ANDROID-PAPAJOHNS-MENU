package com.my.mw_papajohns_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.my.mw_papajohns_menu.adapters.CategoriesRecyclerAdapter;
import com.my.mw_papajohns_menu.models.Category;
import com.my.mw_papajohns_menu.models.Product;
import com.my.mw_papajohns_menu.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Category> categoryList;
    static public List<Restaurant> restaurantList;
    static public List<Product> cart;
    RecyclerView recyclerView;
    CategoriesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cart = new ArrayList<>();
        categoryList = getData();
        restaurantList = getDataRestaurants();
        recyclerView = findViewById(R.id.recycler_categories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new CategoriesRecyclerAdapter(this, R.layout.short_info_item, categoryList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        MenuItem cart = menu.add(Menu.NONE, 1, Menu.NONE, "Cart");
        cart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        cart.setIcon(R.drawable.baseline_shopping_cart_24);
        cart.setOnMenuItemClickListener(menuItem -> {
            Intent cartIntent = new Intent(this, CartActivity.class);
            startActivity(cartIntent);
            return false;
        });
        MenuItem restaurants = menu.findItem(R.id.action_restaurants);
        restaurants.setOnMenuItemClickListener(menuItem -> {
            Intent restaurantIntent = new Intent(this, RestaurantsActivity.class);
            startActivity(restaurantIntent);
            return true;
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public List<Category> getData(){
        List<Category> categories = new ArrayList<>();
        // Pizza
        {
            Category pizza;
            List<Product> pizzaList = new ArrayList<>();
            Product pizzaCheese = new Product("Cheese Pizza",
                    "Pizza dough, tomato sauce, and cheese", R.drawable.cheese_pizza, new ArrayList<>());
            pizzaCheese.addCategory("Small, 23cm", 10);
            pizzaList.add(pizzaCheese);
            Product veggiePizza = new Product("Veggie Pizza",
                    "Green bell pepper, red onion, mushrooms, tomato, and olives are the classic veggie pizza toppings", R.drawable.veggie_pizza, new ArrayList<>());
            veggiePizza.addCategory("Small, 23cm", 15);
            veggiePizza.addCategory("Medium, 30cm", 20);
            veggiePizza.addCategory("Large, 35cm", 30);
            pizzaList.add(veggiePizza);
            Product pepperoniPizza = new Product("Pepperoni Pizza",
                    "pizza sauce, mozzarella cheese, and pepperoni", R.drawable.pepperoni_pizza, new ArrayList<>());
            pepperoniPizza.addCategory("Medium, 30cm", 23);
            pepperoniPizza.addCategory("Large, 35cm", 32);
            pizzaList.add(pepperoniPizza);
            Product meatPizza = new Product("Meat Pizza",
                    "pepperoni, savory sausage, real beef, hickory-smoked bacon, and julienne-cut Canadian bacon", R.drawable.meat_pizza, new ArrayList<>());
            meatPizza.addCategory("Small, 23cm", 15);
            meatPizza.addCategory("Medium, 30cm", 22);
            meatPizza.addCategory("Large, 35cm", 29);
            pizzaList.add(meatPizza);
            pizza = new Category("Pizzas", R.drawable.pizza_category, pizzaList);
            categories.add(pizza);
        }
        {
            Category pasta;
            List<Product> pastaList = new ArrayList<>();
            Product pastaFarfalle = new Product("Bow Tie Pasta (Farfalle)",
                    "semolina (wheat), durum wheat flour", R.drawable.pasta_farfalle, new ArrayList<>());
            pastaFarfalle.addCategory("Small", 10);
            pastaList.add(pastaFarfalle);
            Product pastaBucatini = new Product("Bucatini Pasta",
                    "These long, hollow spaghetti-like tubes (aka perciatelli) are unusual and fun! Try them in casseroles or Asian stir-fries, or tossed with a fresh tomato sauce.",
                    R.drawable.pasta_bucatini, new ArrayList<>());
            pastaBucatini.addCategory("Small", 15);
            pastaBucatini.addCategory("Medium", 20);
            pastaBucatini.addCategory("Large", 30);
            pastaList.add(pastaBucatini);
            Product pastaDitalini = new Product("Ditalini Pasta",
                    "Like most short pasta shapes, ditalini are excellent when used in soups, pasta salads, and to stand up to chunky sauces",
                    R.drawable.pasta_ditalini, new ArrayList<>());
            pastaDitalini.addCategory("Medium", 23);
            pastaDitalini.addCategory("Large", 32);
            pastaList.add(pastaDitalini);
            Product pastaFusilli = new Product("Fusilli Pasta",
                    "This long, thick, spiral-shaped pasta adds an unexpected twist to any recipe that calls for spaghetti. Its crevices are perfect for carrying thick sauces, but it's often also used in pasta salads.",
                    R.drawable.pasta_fusilli, new ArrayList<>());
            pastaFusilli.addCategory("Small", 15);
            pastaFusilli.addCategory("Medium", 22);
            pastaFusilli.addCategory("Large", 29);
            pastaList.add(pastaFusilli);
            pasta = new Category("Pastas", R.drawable.pasta_category, pastaList);
            categories.add(pasta);
        }

        return categories;
    }

    public List<Restaurant> getDataRestaurants(){
        List<Restaurant> restaurants = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            restaurants.add(new Restaurant("Address " + (i+1), "9:00 - 19:00", R.drawable.restaurant));
        }
        return restaurants;
    }
}