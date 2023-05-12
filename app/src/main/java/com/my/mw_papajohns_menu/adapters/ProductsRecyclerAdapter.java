package com.my.mw_papajohns_menu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.my.mw_papajohns_menu.ProductOrderActivity;
import com.my.mw_papajohns_menu.R;
import com.my.mw_papajohns_menu.models.Category;
import com.my.mw_papajohns_menu.models.Product;

import java.util.List;

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<ProductsRecyclerAdapter.ViewHolder> {

    private Context context;
    private int layoutTemplate;
    private List<Product> productList;

    public ProductsRecyclerAdapter(Context context, int layoutTemplate, List<Product> productList) {
        this.context = context;
        this.layoutTemplate = layoutTemplate;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutTemplate, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product category = productList.get(position);
        holder.image.setImageResource(category.getImageId());
        holder.name.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_category);
            name = itemView.findViewById(R.id.name_category);
            card = itemView.findViewById(R.id.card);

            card.setOnClickListener(view -> {
                Intent productOrderActivity = new Intent(context, ProductOrderActivity.class);
                productOrderActivity.putExtra("product", productList.get(getAdapterPosition()));
                context.startActivity(productOrderActivity);
            });

        }
    }
}
