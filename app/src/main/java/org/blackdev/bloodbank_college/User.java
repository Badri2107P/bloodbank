package org.blackdev.bloodbank_college;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 9/5/2017.
 */


//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User implements Serializable {

    private String username, email, phone_number;


    public User(String username, String email, String phone_number) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getphone_number() {
        return phone_number;
    }
}
