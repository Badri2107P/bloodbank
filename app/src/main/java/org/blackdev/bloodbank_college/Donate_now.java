package org.blackdev.bloodbank_college;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

import static org.blackdev.bloodbank_college.R.id.nameip;

public class Donate_now extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPhoneno,editTextAddress;
    RadioGroup radioGroupGender;
    Spinner bldgrp,districtip;
    boolean net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_layout);

        final Button regbutton=(Button)findViewById(R.id.btn_signup);
        editTextUsername = (EditText) findViewById(R.id.nameip);
        editTextEmail = (EditText) findViewById(R.id.emailip);
        editTextAddress=(EditText)findViewById(R.id.addressip);
        editTextPhoneno = (EditText) findViewById(R.id.phoneip);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);
        bldgrp=(Spinner)findViewById(R.id.bloodip);
        districtip=(Spinner)findViewById(R.id.districtip);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String phoneno = editTextPhoneno.getText().toString().trim();
        final String address = editTextAddress.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        final String bldgroup=bldgrp.getSelectedItem().toString();
        final String district=districtip.getSelectedItem().toString();
        //first we will do the validations

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            editTextAddress.setError("Please enter your address");
            editTextAddress.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phoneno) || phoneno.length()!= 10) {
            editTextPhoneno.setError("Enter a valid phone no");
            editTextPhoneno.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("address", address);
                params.put("district", district);
                params.put("blood_group", bldgroup);
                params.put("phone_number", phoneno);
                params.put("gender", gender);


                System.out.println(params);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        User user = new User(
                                userJson.getString("username"),
                                userJson.getString("email"),
                                userJson.getString("gender")
                        );

                        //starting the profile activity
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Mobile Number Already Exist or Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }
}
