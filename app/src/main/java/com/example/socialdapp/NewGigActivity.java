package com.example.socialdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.socialdapp.custom.Gig;
import com.example.socialdapp.network.InsertGigsUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewGigActivity extends AppCompatActivity {
    private EditText title;
    private EditText desc;
    private Spinner priceSpinner;
    private String priceString;
    private int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_gig);

        title = findViewById(R.id.editText_title);
        desc = findViewById(R.id.editText_desc);
        priceSpinner = findViewById(R.id.price_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.prices_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(adapter);
        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priceString = (String) parent.getItemAtPosition(position);
                if(position > 0)
                    price = Integer.parseInt(priceString.substring(0, 2));
                else
                    price = 5;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        FloatingActionButton fab = findViewById(R.id.newGig_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("price", String.valueOf(price));
                new InsertGigsUtils(title.getText().toString(), desc.getText().toString(), LoginActivity.userID, price).execute();
                finish();
            }
        });
    }
}