package ca.gbc.comp3074.resturantproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.ResturantViewHolder> {

    private List<Resturant> resturantList;

    public ResturantAdapter(List<Resturant> resturantList) {
        this.resturantList = resturantList;
    }

    @NonNull
    @Override
    public ResturantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
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
    }

    @Override
    public int getItemCount() {
        return resturantList.size();
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