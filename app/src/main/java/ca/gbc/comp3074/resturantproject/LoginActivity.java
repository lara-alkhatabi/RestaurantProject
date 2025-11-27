package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private UserDao userDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get database instance
        userDao = AppDatabase.getInstance(this).userDao();

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(v -> handleLogin());

        TextView signupText = findViewById(R.id.signuplink);
        signupText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void handleLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Run database query on a background thread
        executorService.execute(() -> {
            User user = userDao.login(email, password);
            runOnUiThread(() -> {
                if (user != null) {
                    // Login successful
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, RestListActivity.class);
                    // Pass the logged-in user's ID to the next activity
                    intent.putExtra("LOGGED_IN_USER_ID", user.id);
                    startActivity(intent);
                    finish(); // Close login activity
                } else {
                    // Login failed
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
