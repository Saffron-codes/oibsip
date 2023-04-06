package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner fromOptions = findViewById(R.id.from);
        Spinner toOptions = findViewById(R.id.to);

        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this,R.array.convert_options, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this,R.array.convert_options, android.R.layout.simple_spinner_dropdown_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromOptions.setAdapter(fromAdapter);
        toOptions.setAdapter(toAdapter);
    }
}