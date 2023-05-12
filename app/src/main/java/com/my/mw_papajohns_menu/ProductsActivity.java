package com.my.mw_papajohns_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.my.mw_papajohns_menu.adapters.ProductsRecyclerAdapter;
import com.my.mw_papajohns_menu.models.Category;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_products);
        Intent parent = getIntent();
        if (parent == null){
            finish();
            return;
        }
        Category category = (Category) parent.getSerializableExtra("category");
        getSupportActionBar().setTitle(category.getName());
        recyclerView.setHasFixedSize(true);
        ProductsRecyclerAdapter adapter = new ProductsRecyclerAdapter(this, R.layout.short_info_product, category.getProducts());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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
}