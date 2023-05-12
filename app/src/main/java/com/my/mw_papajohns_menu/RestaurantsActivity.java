package com.my.mw_papajohns_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.my.mw_papajohns_menu.adapters.RestaurantsRecyclerAdapter;

public class RestaurantsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        recyclerView = findViewById(R.id.recycler_addresses);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RestaurantsRecyclerAdapter adapter = new RestaurantsRecyclerAdapter(this,
                R.layout.restaurant_item,
                MainActivity.restaurantList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem cart = menu.add(Menu.NONE, 1, Menu.NONE, "Cart");
        cart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        cart.setIcon(R.drawable.baseline_shopping_cart_24);
        cart.setOnMenuItemClickListener(menuItem -> {
            Intent cartIntent = new Intent(this, CartActivity.class);
            startActivity(cartIntent);
            return false;
        });
        return super.onCreateOptionsMenu(menu);
    }
}