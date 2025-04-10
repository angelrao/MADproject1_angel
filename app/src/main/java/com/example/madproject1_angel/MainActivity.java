package com.example.madproject1_angel;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.madproject1_angel.R;

public class MainActivity extends AppCompatActivity {

    // Declare UI Components
    EditText inputValue;           // For entering the value to convert
    Spinner fromUnitSpinner;       // Dropdown for selecting source unit
    Spinner toUnitSpinner;         // Dropdown for selecting target unit
    Button convertButton;          // Button to perform conversion
    TextView resultText;           // To display the result
    String[] units = {"Metre", "Centimetre", "Inch", "Foot", "Yard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Linking UI components with their IDs from XML
        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);
// Creating Adapter for spinners using units array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);
// Perform conversion when Convert button is clicked
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }
    /*
         Method to convert the entered value from one unit to another
         */
    private void convert() {
        String inputStr = inputValue.getText().toString().trim();
// Check if input is empty
        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        double input = Double.parseDouble(inputStr);
        String fromUnit = fromUnitSpinner.getSelectedItem().toString();
        String toUnit = toUnitSpinner.getSelectedItem().toString();

        double metreValue = toMetres(input, fromUnit);
        double result = fromMetres(metreValue, toUnit);
// Display final result with 4 decimal places
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

