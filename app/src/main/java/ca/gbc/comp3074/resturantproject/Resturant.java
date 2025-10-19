package ca.gbc.comp3074.resturantproject;

import java.util.List;

public class Resturant {
    public String name;
    public int stars;
    public String phone;
    public String email;
    public List<String> categories;

    public Resturant(String name, int stars, String phone, String email, List<String> categories) {
        this.name = name;
        this.stars = stars;
        this.phone = phone;
        this.email = email;
        this.categories = categories;
    }
}