package com.my.mw_papajohns_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.my.mw_papajohns_menu.models.Product;
import com.my.mw_papajohns_menu.utils.MinMaxFilter;

public class ProductOrderActivity extends AppCompatActivity {

    Product product;
    ImageView productImage;
    TextView productName;
    TextView productInfo;
    Spinner productType;
    Button minus;
    Button plus;
    EditText numEditor;
    TextView totalSum;
    Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent parent = getIntent();
        if (parent == null){
            finish();
            return;
        }
        product = (Product)parent.getSerializableExtra("product");
        getSupportActionBar().setTitle(product.getName());

        productImage = findViewById(R.id.image_product);
        productName = findViewById(R.id.product_name);
        productInfo = findViewById(R.id.product_info);
        productType = findViewById(R.id.spinner_category);
        minus = findViewById(R.id.btn_minus);
        plus = findViewById(R.id.btn_plus);

        numEditor = findViewById(R.id.edit_num);
        product.setCount(1);
        numEditor.setText("1");

        numEditor.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "99" )}) ;

        totalSum = findViewById(R.id.text_totalSum);
        totalSum.setText("0.0");

        addToCart = findViewById(R.id.btn_add_to_cart);

        productImage.setImageResource(product.getImageId());

        productName.setText(product.getName());

        productInfo.setText(product.getInfo());

        ArrayAdapter<Product.ProductCategory> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                product.getProductCategoryList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        productType.setAdapter(adapter);

//        if (productType.getCount() > 0){
//            product.setSelectedCategory(product.getProductCategoryList().get(0));
//            setTotalSum();
//        }

        productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                product.setSelectedCategory(product.getProductCategoryList().get(position));
                setTotalSum();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        minus.setOnClickListener(view -> {
            if (numEditor.getText().toString().isEmpty()){
                return;
            }
            int result = Integer.parseInt(numEditor.getText().toString()) - 1;
            if (result > 0){
                product.setCount(result);
                numEditor.setText(Integer.toString(result));
            }
        });
        plus.setOnClickListener(view -> {
            int result;
            if (numEditor.getText().toString().isEmpty()){
                result = 1;
            }
            else{
                result = Integer.parseInt(numEditor.getText().toString()) + 1;
            }
            product.setCount(result);
            numEditor.setText(Integer.toString(result));
        });

        addToCart.setOnClickListener(view -> {
            if (product.getPrice() > 0 && product.getCount() > 0){
                for (int i = 0; i < MainActivity.cart.size(); i++ ){
                    if (MainActivity.cart.get(i).equals(product)){
                        MainActivity.cart.get(i).setCount(MainActivity.cart.get(i).getCount() + product.getCount());
                        finish();
                        return;
                    }
                }
                MainActivity.cart.add(product);
                finish();
            }
        });

        numEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setTotalSum();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


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

    private void setTotalSum(){
        if (product.getSelectedCategory() == null || numEditor.getText().toString().isEmpty()){
            product.setPrice(0);
            totalSum.setText("0.0");
        }
        else {
            int count = Integer.parseInt(numEditor.getText().toString());
            product.setPrice(product.getSelectedCategory().getPrice());
            float result = product.getSelectedCategory().getPrice() * count;
            totalSum.setText(Float.toString(result));
        }
        if (product.getPrice() <= 0 && product.getCount() > 0){
            addToCart.setEnabled(false);
        }
        else{
            addToCart.setEnabled(true);
        }
    }
}