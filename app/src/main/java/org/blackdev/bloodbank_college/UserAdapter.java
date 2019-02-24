package org.blackdev.bloodbank_college;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import java.util.List;

/**
 * Created by Balaji on 28-01-2018.
 */

public class UserAdapter  extends ArrayAdapter<User> {

        int resource;
        String response;
        Context context;

        public UserAdapter(Context context, int resource, List<User> items) {
            super(context, resource, items);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout usersView;
            User contact = getItem(position);
            if (convertView == null) {
                usersView = new LinearLayout(getContext());
                String inflater = Context.LAYOUT_INFLATER_SERVICE;
                LayoutInflater vi;
                vi = (LayoutInflater) getContext().getSystemService(inflater);
                vi.inflate(resource, usersView, true);
            } else {
                usersView = (LinearLayout) convertView;
            }
            TextView contactName = (TextView) usersView.findViewById(R.id.username);
            contactName.setTypeface(null, Typeface.BOLD);
            contactName.setTextSize(TypedValue.COMPLEX_UNIT_PX, 75);
            TextView contactmail = (TextView) usersView.findViewById(R.id.usermail);
            contactmail.setTextSize(TypedValue.COMPLEX_UNIT_PX, 70);
            TextView contactPhone = (TextView) usersView.findViewById(R.id.userphone);
            contactPhone.setTextSize(TypedValue.COMPLEX_UNIT_PX, 60);
            contactName.setText(contact.getUsername());
            contactmail.setText(contact.getEmail());
            contactPhone.setText(contact.getphone_number());
            return usersView;
        }
    }

