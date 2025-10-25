package ca.gbc.comp3074.resturantproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.ResturantViewHolder> {

    private List<Resturant> resturantList;
    private List<Resturant> fullList;
    private Context context;


    public ResturantAdapter(Context context) {
        this.context = context;
        this.resturantList = new ArrayList<>();
        this.fullList = new ArrayList<>();
    }

    public void setData(List<Resturant> data) {
        resturantList.clear();
        resturantList.addAll(data);

        fullList.clear();
        fullList.addAll(data);

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ResturantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_resturant_card, parent, false);
        return new ResturantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantViewHolder holder, int position) {
        Resturant r = resturantList.get(position);
        holder.tvName.setText(r.name);
        holder.tvStars.setText("Stars: " + r.stars);
        holder.tvCategories.setText("Categories: " + String.join(", ", r.categories));
        holder.tvContact.setText("Phone: " + r.phone + " | Email: " + r.email);

        // Set click listener for the item view")
        holder.itemView.setOnClickListener(v -> {
            // Handle item click here
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", r.name);
            intent.putExtra("stars", r.stars);
            intent.putExtra("phone", r.phone);
            intent.putExtra("email", r.email);
            intent.putStringArrayListExtra("categories", new ArrayList<>(r.categories));
//            intent.putExtra("location", new ArrayList<>(r.location));
            double lng = r.location.get(0);
            double lat = r.location.get(1);
            intent.putExtra("lat", lat);
            intent.putExtra("lng",lng);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return resturantList.size();
    }

    public void filter(String text){
        resturantList.clear();
        if(text.isEmpty()) {
            resturantList.addAll(fullList);
        } else {
            text = text.toLowerCase();
            for (Resturant r : fullList) {
                if (r.name.toLowerCase().contains(text)) {
                    resturantList.add(r);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ResturantViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStars, tvCategories, tvContact;

        public ResturantViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvStars = itemView.findViewById(R.id.tvStars);
            tvCategories = itemView.findViewById(R.id.tvCategories);
            tvContact = itemView.findViewById(R.id.tvContact);
        }
    }
}