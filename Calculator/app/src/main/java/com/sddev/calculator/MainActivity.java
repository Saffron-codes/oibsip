package com.sddev.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    String calc = "",calculations = "";
    TextView calcTV,resultsTV;
    double firstNum,secondNum,result = 0;
    char operator = ' ';
    boolean canAddOperator = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcTV = findViewById(R.id.calculationTV);
        resultsTV = findViewById(R.id.resultsTV);
    }

    public void clearCalc(View view){
        calc = "";
        calculations = calc;
        calcTV.setText(calculations);
        resultsTV.setText("");
    }

    public void numClicked(View view) {
        Button button = (Button) view;
        calc = calc.concat(button.getText().toString());
        calculations = calculations.concat(button.getText().toString());
        calcTV.setText(calculations);
        canAddOperator = true;
    }

    public void operatorClicked(View view) {
        Button button = (Button) view;
        if(canAddOperator){
            operator = button.getText().charAt(0);
            firstNum = Double.parseDouble(calc);
            calc = calc.concat(button.getText().toString());
            calculations = calc;
            calcTV.setText(calculations);
            canAddOperator = false;
            calc = "";
        }
    }

    public void equalClicked(View view){
        secondNum = Double.parseDouble(calc);

        switch (operator){
            case '+':
                result = firstNum + secondNum;
                resultsTV.setText(String.valueOf(result));
                break;
            case '-':
                result = firstNum - secondNum;
                resultsTV.setText(String.valueOf(result));
                break;
            case '/':
                result = firstNum / secondNum;
                resultsTV.setText(String.valueOf(result));
                break;
            case 'x':
                result = firstNum * secondNum;
                resultsTV.setText(String.valueOf(result));
                break;
        }

    }

    public void removeClicked(View view) {
        if(!calc.isEmpty()) {
            calc = calc.substring(0, calc.length() - 1);
            calculations = calc;
            calcTV.setText(calculations);
        }
    }
}