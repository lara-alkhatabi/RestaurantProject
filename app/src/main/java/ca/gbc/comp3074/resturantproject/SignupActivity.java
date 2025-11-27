package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, phoneEditText, addressEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private UserDao userDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get database instance
        userDao = AppDatabase.getInstance(this).userDao();

        // Initialize views
        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phonenumber);
        addressEditText = findViewById(R.id.address);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmpassword);

        Button signupButton = findViewById(R.id.signupbutton);
        signupButton.setOnClickListener(v -> handleSignup());

        TextView loginText = findViewById(R.id.loginlink);
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void handleSignup() {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        // Basic validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Name, Email, and Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Run database operations on a background thread
        executorService.execute(() -> {
            // Check if user already exists
            User existingUser = userDao.findByEmail(email);
            if (existingUser != null) {
                // Run on UI thread to show Toast
                runOnUiThread(() -> Toast.makeText(SignupActivity.this, "Email already registered", Toast.LENGTH_SHORT).show());
            } else {
                // Create and insert new user
                User newUser = new User(name, phone, address, email, password);
                userDao.insert(newUser);

                // Navigate to LoginActivity on success
                runOnUiThread(() -> {
                    Toast.makeText(SignupActivity.this, "Signup successful! Please login.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // Close signup activity
                });
            }
        });
    }
}
