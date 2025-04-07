package com.example.madproject1_angel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText inputValue, resultValue;
    Spinner spinnerFrom, spinnerTo;
    Button btnConvert;

    String[] units = {"Feet", "Inches", "Centimetres", "Metres", "Yards"};
    Map<String, Double> unitToMeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        resultValue = findViewById(R.id.resultValue);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        unitToMeter = new HashMap<>();
        unitToMeter.put("Feet", 0.3048);
        unitToMeter.put("Inches", 0.0254);
        unitToMeter.put("Centimetres", 0.01);
        unitToMeter.put("Metres", 1.0);
        unitToMeter.put("Yards", 0.9144);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });
    }

    void convert() {
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();
        String input = inputValue.getText().toString();

        if(input.isEmpty()){
            Toast.makeText(this, "Enter value", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputVal = Double.parseDouble(input);
        double result = inputVal * unitToMeter.get(fromUnit) / unitToMeter.get(toUnit);

        resultValue.setText(String.format("%.4f", result));
    }
}
