package com.example.madproject1_angel;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madproject1_angel.R;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnitSpinner, toUnitSpinner;
    Button convertButton;
    TextView resultText;
//array of available units
    String[] units = {"Metre", "Centimetre", "Inch", "Foot", "Yard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    private void convert() {
        String inputStr = inputValue.getText().toString().trim();
        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        double input = Double.parseDouble(inputStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double metreValue = toMetres(input, fromUnit);
        double result = fromMetres(metreValue, toUnit);

        resultText.setText("Result : " + String.format("%.4f", result) + " " + toUnit);
    }

    private double toMetres(double value, String unit) {
        switch (unit) {
            case "Centimetre": return value / 100;
            case "Inch": return value * 0.0254;
            case "Foot": return value * 0.3048;
            case "Yard": return value * 0.9144;
            case "Metre":
            default: return value;
        }
    }

    private double fromMetres(double value, String unit) {
        switch (unit) {
            case "Centimetre": return value * 100;
            case "Inch": return value / 0.0254;
            case "Foot": return value / 0.3048;
            case "Yard": return value / 0.9144;
            case "Metre":
            default: return value;
        }
    }
}
