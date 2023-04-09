package com.sddev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String calc = "";
    TextView calcTV;
    double firstNum,secondNum,result = 0;
    char operator = ' ';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcTV = findViewById(R.id.calcTV);
    }

    public void clearCalc(View view){
        calc = "";
        calcTV.setText(calc);
    }

    public void numClicked(View view) {
        Button button = (Button) view;
        calc = calc.concat(button.getText().toString());
        calcTV.setText(calc);
    }

    public void operatorClicked(View view) {
        Button button = (Button) view;
        operator = button.getText().charAt(0);
        firstNum = Double.parseDouble(calc);
        calc = "";
        calcTV.setText(button.getText());
        //calcTV.setText();
    }

    public void equalClicked(View view){
        secondNum = Double.parseDouble(calc);
        switch (operator){
            case '+':
                result = firstNum + secondNum;
                calcTV.setText(String.valueOf(result));
                break;
            case '-':
                result = firstNum - secondNum;
                calcTV.setText(String.valueOf(result));
                break;
            case '/':
                result = firstNum / secondNum;
                calcTV.setText(String.valueOf(result));
                break;
            case 'X':
                result = firstNum * secondNum;
                calcTV.setText(String.valueOf(result));
                break;
            default:
                break;
        }
    }

    public void removeClicked(View view) {
        calc = calc.substring(0,calc.length()-1);
        calcTV.setText(calc);
    }
}