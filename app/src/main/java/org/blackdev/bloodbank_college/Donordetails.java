package org.blackdev.bloodbank_college;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Balaji on 28-01-2018.
 */

public class Donordetails extends AppCompatActivity {

    ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_details);
        Bundle b = getIntent().getExtras();
        users = (ArrayList<User>) b.getSerializable("user_list");
        final ListView listView = (ListView) findViewById(R.id.listView);
        UserAdapter adapter = new UserAdapter(Donordetails.this, R.layout.listviewelements, users);
        listView.setAdapter(adapter);
System.out.println(users);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String pos = users.get(position).getphone_number();
                Toast.makeText(Donordetails.this, pos, Toast.LENGTH_SHORT).show();
            }
        });
    }
}