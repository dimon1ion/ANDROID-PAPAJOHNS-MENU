package com.my.mw_papajohns_menu.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.my.mw_papajohns_menu.ProductOrderActivity;
import com.my.mw_papajohns_menu.R;
import com.my.mw_papajohns_menu.models.CartEvent;
import com.my.mw_papajohns_menu.models.Product;

import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.ViewHolder> {

    private Context context;
    private int layoutTemplate;
    private List<Product> productList;
    private CartEvent cartEvent;

    public CartRecyclerAdapter(Context context, int layoutTemplate, List<Product> productList, CartEvent cartEvent) {
        this.context = context;
        this.layoutTemplate = layoutTemplate;
        this.productList = productList;
        this.cartEvent = cartEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutTemplate, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.image.setImageResource(product.getImageId());
        holder.name.setText(product.getName());
        holder.category.setText(product.getSelectedCategory().getCategoryName());
        holder.editNum.setText(Integer.toString(product.getCount()));
        holder.totalSum.setText(Float.toString(product.getCount() * product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView category;
        Button minus;
        Button plus;
        EditText editNum;
        TextView totalSum;
        ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_avatar);
            name = itemView.findViewById(R.id.text_name);
            category = itemView.findViewById(R.id.text_category);
            minus = itemView.findViewById(R.id.btn_minus);
            plus = itemView.findViewById(R.id.btn_plus);
            editNum = itemView.findViewById(R.id.edit_num);
            editNum.setFocusable(false);
            editNum.setEnabled(false);
            editNum.setCursorVisible(false);
            editNum.setKeyListener(null);
            editNum.setBackgroundColor(Color.TRANSPARENT);
            totalSum = itemView.findViewById(R.id.text_totalSum);
            deleteButton = itemView.findViewById(R.id.btn_delete);

            minus.setOnClickListener(view -> {
                if (editNum.getText().toString().isEmpty()){
                    return;
                }
                int result = Integer.parseInt(editNum.getText().toString()) - 1;
                if (result > 0){
                    productList.get(getAdapterPosition()).setCount(result);
                    editNum.setText(Integer.toString(result));
                }
            });
            plus.setOnClickListener(view -> {
                int result;
                if (editNum.getText().toString().isEmpty()){
                    result = 1;
                }
                else{
                    result = Integer.parseInt(editNum.getText().toString()) + 1;
                }
                productList.get(getAdapterPosition()).setCount(result);
                editNum.setText(Integer.toString(result));
            });

            editNum.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Product product = productList.get(getAdapterPosition());
                    float res = product.getPrice() * product.getCount();
                    totalSum.setText(Float.toString(res));
                    cartEvent.changeTotalSum();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            deleteButton.setOnClickListener(view -> {
                productList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                cartEvent.changeTotalSum();
            });



        }
    }
}
