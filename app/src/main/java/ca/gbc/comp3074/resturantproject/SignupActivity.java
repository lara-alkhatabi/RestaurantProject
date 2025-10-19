package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signupbutton = findViewById(R.id.signupbutton);
        signupbutton.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, RestListActivity.class);
            startActivity(intent);
        });

        TextView logintext = findViewById(R.id.loginlink);
        logintext.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}