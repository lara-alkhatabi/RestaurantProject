package ca.gbc.comp3074.resturantproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RestListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Resturant> resturantList = new ArrayList<>();
    ResturantAdapter adapter;

    // 1. ADD THIS VARIABLE
    private int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);

        // 2. ADD THESE LINES TO GET AND VALIDATE THE USER ID
        loggedInUserId = getIntent().getIntExtra("LOGGED_IN_USER_ID", -1);
        if (loggedInUserId == -1) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return;
        }

        ImageView userIcon = findViewById(R.id.userIcon);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(RestListActivity.this, AboutActivity.class);
            // 3. ADD THE USER ID TO THE INTENT
            intent.putExtra("LOGGED_IN_USER_ID", loggedInUserId);
            startActivity(intent);
        });

        ImageView logoutIcon = findViewById(R.id.logoutIcon);
        logoutIcon.setOnClickListener(v -> {
            Intent intent = new Intent(RestListActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.restaurantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ResturantAdapter(this);
        recyclerView.setAdapter(adapter);

        TextInputEditText searchInput = findViewById(R.id.searchInput);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Load JSON from assets
        String jsonStr = loadJSONFromAsset("Resturants.json");
        if (jsonStr != null) {
            parseAndUseJSON(jsonStr);
        }
    }

    private String loadJSONFromAsset(String filename) {
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void parseAndUseJSON(String jsonStr) {
        try {
            List<Resturant> loadedList = new ArrayList<>();
            JSONArray restaurants = new JSONArray(jsonStr);

            for (int i = 0; i < restaurants.length(); i++) {
                JSONObject restaurant = restaurants.getJSONObject(i);
                String name = restaurant.getString("name");
                int stars = restaurant.getInt("stars");

                JSONObject contact = restaurant.getJSONObject("contact");
                String phone = contact.getString("phone");
                String email = contact.getString("email");

                JSONArray categoriesArray = restaurant.getJSONArray("categories");
                List<String> categories = new ArrayList<>();
                for (int j = 0; j < categoriesArray.length(); j++) {
                    categories.add(categoriesArray.getString(j));
                }

                JSONArray locationArray = contact.getJSONArray("location");
                List<Double> location = new ArrayList<>();
                for (int j = 0; j < locationArray.length(); j++) {
                    location.add(locationArray.getDouble(j));
                }

                loadedList.add(new Resturant(name, stars, phone, email, categories, location));

            }
            adapter.setData(loadedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
