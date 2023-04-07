package com.example.unitconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Objects;

public class ConvertUnit extends AppCompatActivity {

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

        ArrayAdapter<CharSequence> fromAdapter = ArrayAdapter.createFromResource(this,setDropDownAdapters(title), android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> toAdapter = ArrayAdapter.createFromResource(this,setDropDownAdapters(title), android.R.layout.simple_spinner_dropdown_item);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromOptions.setAdapter(fromAdapter);
        toOptions.setAdapter(toAdapter);
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
}