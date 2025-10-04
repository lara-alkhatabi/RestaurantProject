package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView backIcon = findViewById(R.id.backicon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, ListingActivity.class);
            startActivity(intent);
        });

    }
}