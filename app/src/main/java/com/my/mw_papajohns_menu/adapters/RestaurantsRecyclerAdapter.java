package com.my.mw_papajohns_menu.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.my.mw_papajohns_menu.ProductOrderActivity;
import com.my.mw_papajohns_menu.R;
import com.my.mw_papajohns_menu.models.Product;
import com.my.mw_papajohns_menu.models.Restaurant;

import java.util.List;

public class RestaurantsRecyclerAdapter extends RecyclerView.Adapter<RestaurantsRecyclerAdapter.ViewHolder> {

    private Context context;
    private int layoutTemplate;
    private List<Restaurant> restaurants;

    public RestaurantsRecyclerAdapter(Context context, int layoutTemplate, List<Restaurant> restaurants) {
        this.context = context;
        this.layoutTemplate = layoutTemplate;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutTemplate, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.image.setImageResource(restaurant.getImageId());
        holder.address.setText(restaurant.getAddress());
        holder.time.setText(restaurant.getTime());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView address;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_restaurant);
            address = itemView.findViewById(R.id.text_address);
            time = itemView.findViewById(R.id.text_time);

        }
    }
}
