package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginbutton = findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        TextView signuptext = findViewById(R.id.signuplink);
        signuptext.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });


    }
}