package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        ImageView userIcon = findViewById(R.id.userIcon);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, AboutActivity.class);
            startActivity(intent);
        });
        ImageView logoutIcon = findViewById(R.id.logoutIcon);
        logoutIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        LinearLayout restcard1 = findViewById(R.id.restcard1);
        restcard1.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);
            startActivity(intent);
        });

        LinearLayout restcard2 = findViewById(R.id.restcard2);
        restcard2.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);
            startActivity(intent);
        });

        LinearLayout restcard3 = findViewById(R.id.restcard3);
        restcard3.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);
            startActivity(intent);
        });

        LinearLayout restcard4 = findViewById(R.id.restcard4);
        restcard4.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);
            startActivity(intent);
        });

        LinearLayout restcard5 = findViewById(R.id.restcard5);
        restcard5.setOnClickListener(v -> {
            Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);
            startActivity(intent);
        });



    }
}