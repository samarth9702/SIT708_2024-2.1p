package com.example.convertor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        firstDropDown();
        secondDropDown();

    }

    public void firstDropDown() {
        Spinner spinnerConversionType = findViewById(R.id.spinner_conversionType);
        String[] convertArr = getResources().getStringArray(R.array.conversionType);
        //noinspection rawtypes,unchecked
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, convertArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionType.setAdapter(adapter);

        spinnerConversionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                secondDropDown();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void secondDropDown() {
        Spinner preSpinnerConversionType = findViewById(R.id.spinner_conversionType);
        String text = preSpinnerConversionType.getSelectedItem().toString();

        String[] convertArr = getResources().getStringArray(R.array.lengthConversion);

        Spinner spinnerConversionType = findViewById(R.id.spinner2);
        Spinner spinnerConversionType2 = findViewById(R.id.spinner3);

        switch (text) {
            case "Length":
                convertArr = getResources().getStringArray(R.array.lengthConversion);
                break;
            case "Temperature":
                convertArr = getResources().getStringArray(R.array.temperatureConversion);
                break;
            case "Weight":
                convertArr = getResources().getStringArray(R.array.weightConversion);
                break;
        }

        //noinspection rawtypes,unchecked
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, convertArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionType.setAdapter(adapter);

        //noinspection rawtypes,unchecked
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, convertArr);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionType2.setAdapter(adapter2);
    }

    public void convertLogic() {
        EditText et = findViewById(R.id.editTextText);
        TextView tv = findViewById(R.id.answer);

        if (et.getText().toString().isEmpty()) {
            et.setError("Please Enter Value");
            return;
        }

        Double a;
        a = Double.parseDouble(String.valueOf(et.getText()));

        Spinner spinner2 = findViewById(R.id.spinner2);
        String s2 = spinner2.getSelectedItem().toString();

        Spinner spinner3 = findViewById(R.id.spinner3);
        String s3 = spinner3.getSelectedItem().toString();


        if (s2.equals("Inch") && s3.equals("Foot")) {
            tv.setText("Answer: " + (a / 12) + " " + s3);
        } else if (s2.equals("Inch") && s3.equals("Yard")) {
            tv.setText("Answer: " + (a / 36) + " " + s3);
        } else if (s2.equals("Inch") && s3.equals("Mile")) {
            tv.setText("Answer: " + (a / 63360) + " " + s3);
        } else if (s2.equals("Foot") && s3.equals("Inch")) {
            tv.setText("Answer: " + (a * 12) + " " + s3);
        } else if (s2.equals("Foot") && s3.equals("Yard")) {
            tv.setText("Answer: " + (a / 3) + " " + s3);
        } else if (s2.equals("Foot") && s3.equals("Mile")) {
            tv.setText("Answer: " + (a / 5280) + " " + s3);
        } else if (s2.equals("Yard") && s3.equals("Inch")) {
            tv.setText("Answer: " + (a * 36) + " " + s3);
        } else if (s2.equals("Yard") && s3.equals("Foot")) {
            tv.setText("Answer: " + (a * 3) + " " + s3);
        } else if (s2.equals("Yard") && s3.equals("Mile")) {
            tv.setText("Answer: " + (a / 1760) + " " + s3);
        } else if (s2.equals("Mile") && s3.equals("Inch")) {
            tv.setText("Answer: " + (a * 63360) + " " + s3);
        } else if (s2.equals("Mile") && s3.equals("Foot")) {
            tv.setText("Answer: " + (a * 5280) + " " + s3);
        } else if (s2.equals("Mile") && s3.equals("Yard")) {
            tv.setText("Answer: " + (a * 1760) + " " + s3);
        } else if(s2.equals(s3)) {
            showErrorToast();
        }

        if (s2.equals("Pound") && s3.equals("Ounce")) {
            tv.setText("Answer: " + (a * 16) + " " + s3);

        } else if (s2.equals("Pound") && s3.equals("Ton")) {
            tv.setText("Answer: " + (a / 2000) + " " + s3);

        } else if (s2.equals("Ounce") && s3.equals("Pound")) {

            tv.setText("Answer: " + (a / 16) + " " + s3);

        } else if (s2.equals("Ounce") && s3.equals("Ton")) {

            tv.setText("Answer: " + (a / 32000) + " " + s3);

        } else if (s2.equals("Ton") && s3.equals("Pound")) {

            tv.setText("Answer: " + (a * 2000) + " " + s3);

        } else if (s2.equals("Ton") && s3.equals("Ounce")) {
            tv.setText("Answer: " + (a * 32000) + " " + s3);
        } else if(s2.equals(s3)) {
            showErrorToast();
        }

        if (s2.equals("Celsius") && s3.equals("Fahrenheit")) {
            double b = a * 9 / 5 + 32;
            tv.setText("Answer: " + b + " " + s3);
        } else if (s2.equals("Fahrenheit") && s3.equals("Celsius")) {
            double b = a - 32;
            double c = b * 5 / 9;
            tv.setText("Answer: " + c + " " + s3);
        } else if (s2.equals("Kelvin") && s3.equals("Celsius")) {
            tv.setText("Answer: " + (a - 273.15) + " " + s3);
        } else if (s2.equals("Celsius") && s3.equals("Kelvin")) {
            tv.setText("Answer: " + (a + 273.15) + " " + s3);
        } else if (s2.equals("Fahrenheit") && s3.equals("Kelvin")) {
            double b = a - 32;
            double c = b * 5 / 9;
            tv.setText("Answer: " + (c + 273.15) + " " + s3);
        } else if (s2.equals("Kelvin") && s3.equals("Fahrenheit")) {
            double b = (a - 273.15) * 9 / 5 + 32;
            tv.setText("Answer: " + b + " " + s3);
        } else if(s2.equals(s3)) {
            showErrorToast();
        }
    }

    public void showErrorToast() {
        Toast toast = Toast.makeText(this, "Unit can't be same", Toast.LENGTH_SHORT);
        toast.show();
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        convertLogic();
    }
}