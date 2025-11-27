package ca.gbc.comp3074.resturantproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "full_name")
    public String name;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    public String address;

    public String email;

    public String password;

    // Public no-arg constructor required by Room
    public User() {}

    // Constructor for creating new user instances
    public User(String name, String phoneNumber, String address, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}
