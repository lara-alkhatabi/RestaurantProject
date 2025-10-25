package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailsActivity extends AppCompatActivity {
    TextView tvName, tvPhone, tvEmail;
    RatingBar rating;

    LinearLayout categoriesLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
        rating = findViewById(R.id.rating);
        categoriesLayout = findViewById(R.id.categories);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String email = getIntent().getStringExtra("email");
        int stars = getIntent().getIntExtra("stars", 0);
        ArrayList<String> categories = getIntent().getStringArrayListExtra("categories");
        double lat = getIntent().getDoubleExtra("lat", 0);
        double lng = getIntent().getDoubleExtra("lng", 0);


        tvName.setText(name);
        tvPhone.setText(phone);
        rating.setRating(stars);
        tvEmail.setText(email);


        if (categories != null) {
            categoriesLayout.removeAllViews();
            for (String category : categories) {
                TextView tag = new TextView(this);
                tag.setText(category);
                tag.setBackgroundResource(R.drawable.tag_background);
                tag.setPadding(16, 8, 16, 8);
                tag.setTextColor(getResources().getColor(R.color.black));
                tag.setTextSize(15);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, 0, 8, 0);
                tag.setLayoutParams(params);
                categoriesLayout.addView(tag);
            }
        }

        String mapurl="https://www.google.com/maps";
        String shareurl;
        if (lat != 0 && lng != 0){
            mapurl="https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng;
            if (name != null){
                shareurl = "https://maps.google.com/?q=" + lat + "," + lng + "(" + Uri.encode(name) + ")";
            }
            else{
                shareurl = "https://maps.google.com/?q=" + lat + "," + lng;
            }
        } else {
            shareurl = "https://share.google.com/";
        }

        ImageView backIcon = findViewById(R.id.backicon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, RestListActivity.class);
            startActivity(intent);
        });

        Button mapbutton = findViewById(R.id.mapbutton);
        Uri mapuri = Uri.parse(mapurl);
        mapbutton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, mapuri);
            startActivity(intent);
        });
        Button sharebutton = findViewById(R.id.sharebutton);
        Uri shareuri = Uri.parse(shareurl);
        sharebutton.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check this location: " + shareurl);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });


    }
}