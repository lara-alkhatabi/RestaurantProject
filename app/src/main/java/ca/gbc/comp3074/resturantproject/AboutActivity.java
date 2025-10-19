package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button goBackButton = findViewById(R.id.gobackbutton);
         goBackButton.setOnClickListener(v -> {
             Intent intent = new Intent(AboutActivity.this, RestListActivity.class);
             startActivity(intent);
         });
    }
}