package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
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

        Button mapbutton = findViewById(R.id.mapbutton);
        String mapurl="https://maps.app.goo.gl/snqgunQb54ncKHb48";
        Uri mapuri = Uri.parse(mapurl);
        mapbutton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, mapuri);
            startActivity(intent);
        });
        Button sharebutton = findViewById(R.id.sharebutton);
        String shareurl="https://share.google/dvlzo0786qD36Cu3C";
        Uri shareuri = Uri.parse(shareurl);
        sharebutton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, shareuri);
            startActivity(intent);
        });


    }
}