package org.blackdev.bloodbank_college;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Balaji on 09-01-2018.
 */

public class Homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        final Button donatebutton=(Button)findViewById(R.id.bldoante);
        final Button needbutton=(Button)findViewById(R.id.bldneed);


        donatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, Donate_now.class);
                startActivity(intent);
            }

            });

        needbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Homepage.this,Blood_group_selection.class);
                        startActivity(intent);

                    }
                });




            }
}
