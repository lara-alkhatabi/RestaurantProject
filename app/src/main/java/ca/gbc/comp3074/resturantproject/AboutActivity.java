package ca.gbc.comp3074.resturantproject;

import android.os.Bundle;
import android.widget.Button;import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AboutActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, phoneEditText, addressEditText, emailEditText;
    private Button saveButton, goBackButton;

    private UserDao userDao;
    private ExecutorService executorService;
    private int loggedInUserId = -1;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize Database, Executor, and Views
        userDao = AppDatabase.getInstance(this).userDao();
        executorService = Executors.newSingleThreadExecutor();

        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phonenumber);
        addressEditText = findViewById(R.id.address);
        emailEditText = findViewById(R.id.email);
        saveButton = findViewById(R.id.savechangesbutton);
        goBackButton = findViewById(R.id.gobackbutton);

        // Get the user ID from the intent
        loggedInUserId = getIntent().getIntExtra("LOGGED_IN_USER_ID", -1);

        if (loggedInUserId != -1) {
            loadUserData();
        } else {
            Toast.makeText(this, "Error: User ID not found.", Toast.LENGTH_LONG).show();
            finish(); // Close activity if no user ID
        }

        goBackButton.setOnClickListener(v -> finish()); // Simply closes the current activity
        saveButton.setOnClickListener(v -> saveUserData());
    }

    private void loadUserData() {
        executorService.execute(() -> {
            currentUser = userDao.findById(loggedInUserId);
            runOnUiThread(() -> {
                if (currentUser != null) {
                    nameEditText.setText(currentUser.name);
                    phoneEditText.setText(currentUser.phoneNumber);
                    addressEditText.setText(currentUser.address);
                    emailEditText.setText(currentUser.email);
                }
            });
        });
    }

    private void saveUserData() {
        if (currentUser == null) {
            Toast.makeText(this, "Cannot save, user data not loaded.", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Name and Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the current user object with new details
        currentUser.name = name;
        currentUser.phoneNumber = phone;
        currentUser.address = address;
        currentUser.email = email;

        executorService.execute(() -> {
            userDao.update(currentUser);
            runOnUiThread(() -> {
                Toast.makeText(AboutActivity.this, "Details updated successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Go back after saving
            });
        });
    }
}
