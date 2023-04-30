package com.example.unitconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ConvertUnit extends AppCompatActivity {
    String fromSelectedOption = "";
    String toSelectedOption = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        actionBar.setTitle(title.concat(" Conversion"));
        setContentView(R.layout.activity_convert_unit);
        Spinner fromOptions = findViewById(R.id.from);
        Spinner toOptions = findViewById(R.id.to);
        Button convertBtn = findViewById(R.id.converbtn);
        EditText valueET = findViewById(R.id.lengthET);
        TextView ansTV = findViewById(R.id.ansTV);


        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this,setDropDownAdapters(title), android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this,setDropDownAdapters(title), android.R.layout.simple_spinner_dropdown_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromOptions.setAdapter(fromAdapter);
        toOptions.setAdapter(toAdapter);


        fromOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = adapterView.getItemAtPosition(position).toString();
                fromSelectedOption = selectedOption;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        toOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedOption = adapterView.getItemAtPosition(position).toString();
                toSelectedOption = selectedOption;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        convertBtn.setOnClickListener(view -> {
            double length = convertValue(title,Double.parseDouble(valueET.getText().toString()));
            ansTV.setText(String.valueOf(length).concat(toSelectedOption));
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    int setDropDownAdapters(String title) {
        if(Objects.equals(title, "Length")){
            return R.array.convert_options_length;
        }
        else if(Objects.equals(title, "Weight")){
            return R.array.convert_options_weight;
        }
        else {
            return R.array.convert_options_length;
        }
    }

    double convertValue(String title,double value){
        if(Objects.equals(title, "Length")){
            return convertLength(value,fromSelectedOption,toSelectedOption);
        }
        else if(title.equals("Weight")){
            return convertWeight(value,fromSelectedOption,toSelectedOption);
        }
        return 0.0;
    }


    public static double convertLength(double length, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "m":
                switch (toUnit) {
                    case "cm":
                        return length * 100;
                    case "mm":
                        return length * 1000;
                    case "km":
                        return length / 1000;
                    default:
                        return length;
                }
            case "cm":
                switch (toUnit) {
                    case "m":
                        return length / 100;
                    case "mm":
                        return length * 10;
                    case "km":
                        return length / 100000;
                    default:
                        return length;
                }
            case "mm":
                switch (toUnit) {
                    case "m":
                        return length / 1000;
                    case "cm":
                        return length / 10;
                    case "km":
                        return length / 1000000;
                    default:
                        return length;
                }
            case "km":
                switch (toUnit) {
                    case "m":
                        return length * 1000;
                    case "cm":
                        return length * 100000;
                    case "mm":
                        return length * 1000000;
                    default:
                        return length;
                }
            default:
                return length;
        }
    }

    public static double convertWeight(double value, String unitFrom, String unitTo) {
        switch(unitFrom) {
            case "kg":
                switch(unitTo) {
                    case "kg":
                        return value;
                    case "g":
                        return value * 1000;
                    case "mg":
                        return value * 1000000;
                }
                break;
            case "g":
                switch(unitTo) {
                    case "kg":
                        return value / 1000;
                    case "g":
                        return value;
                    case "mg":
                        return value * 1000;
                }
                break;
            case "mg":
                switch(unitTo) {
                    case "kg":
                        return value / 1000000;
                    case "g":
                        return value / 1000;
                    case "mg":
                        return value;
                }
                break;
        }
        return 0;
    }


}