package com.my.mw_papajohns_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.my.mw_papajohns_menu.adapters.CartRecyclerAdapter;
import com.my.mw_papajohns_menu.models.CartEvent;
import com.my.mw_papajohns_menu.models.Product;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    TextView totalOrderSum;
    List<Product> productList;
    RecyclerView recyclerView;
    ImageButton orderButton;

    CartEvent cartEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalOrderSum = findViewById(R.id.text_totalSum);
        recyclerView = findViewById(R.id.recycler_products);
        orderButton = findViewById(R.id.btn_order);

        productList = MainActivity.cart;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getSupportActionBar().setTitle("Cart");

        cartEvent = () -> {
            float sum = 0;
            for(Product product : productList){
                sum += product.getCount() * product.getPrice();
            }
            Log.i("app", "Sum: " + sum);
            totalOrderSum.setText(Float.toString(sum));
        };

        CartRecyclerAdapter adapter = new CartRecyclerAdapter(
                this, R.layout.cart_item, productList, cartEvent);

        recyclerView.setAdapter(adapter);

        orderButton.setOnClickListener(view -> {
            if (Float.parseFloat(totalOrderSum.getText().toString()) > 0){
                productList.clear();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (productList.size() > 0){
            MenuItem cart = menu.add(Menu.NONE, 1, Menu.NONE, "Cart");
            cart.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            cart.setIcon(R.drawable.baseline_remove_shopping_cart_24);
            cart.setOnMenuItemClickListener(menuItem -> {
                productList.clear();
                finish();
                return false;
            });
        }

        return super.onCreateOptionsMenu(menu);
    }
}