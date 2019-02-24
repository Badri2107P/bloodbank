package org.blackdev.bloodbank_college;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Blood_group_selection extends AppCompatActivity {

    Spinner bldgrp,districtip;


    public ArrayList<User> contacts = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group_selection);

        bldgrp = (Spinner) findViewById(R.id.needbloodip);
        districtip= (Spinner) findViewById(R.id.needdistrictip);
        final Button donorbutton = (Button) findViewById(R.id.btn_needblood);
        donorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLogin();


            }
        });
    }

    private void userLogin() {
        //first getting the values
        final String blood_group =bldgrp.getSelectedItem().toString();
        final String district=districtip.getSelectedItem().toString();


        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);
                try {
                    //converting response to json object
                    JSONArray jsonArray = new JSONArray(s);
                    Toast.makeText(getApplicationContext(), jsonArray.toString().trim(), Toast.LENGTH_SHORT).show();
                    String[] heroes = new String[5];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        System.out.println("i"+i);
                        System.out.println(jsonArray.length());
                        JSONObject obj = jsonArray.getJSONObject(i);
                        System.out.println(i);
                        System.out.println(obj);
                        heroes[0] = obj.getString("username");
                        heroes[1] = obj.getString("email");
                        System.out.println("i"+i);
                        heroes[2] = obj.getString("phone_number");
                        System.out.println("i2"+i);
                        contacts.add(new User(heroes[0],heroes[1],heroes[2]));
                        System.out.println(contacts);
                    }
                    //starting the profile activity
                        finish();

                    System.out.println("donor1");
                    Intent intent = new Intent(Blood_group_selection.this, Donordetails.class);
                    intent.putExtra("user_list", contacts);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Blood_group_selection.this, "No Donors Available", Toast.LENGTH_SHORT).show();
                }
            }

                @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("blood_group", blood_group);
                    params.put("district", district);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GETDONORS, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();

    }

    }

