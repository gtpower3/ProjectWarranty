package com.example.projectwarranty;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//activity where new warranty details are inputted
//its meant to:
//-provide a way to input details of new warranty
//-check validity of input and provide feedback on whether input is valid or not
//-create and store new warranty in database
//-go to WarrantyActivity of newly created warranty

public class AddWarrantyActivity extends AppCompatActivity {
    EditText editTextName;

    Spinner productType;
    ArrayAdapter arrayAdapter;
    String[] productTypeArray = {"Mobile phone", "Tablet", "PC/Laptop", "Kitchen appliance", "Other"};

    DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwarranty);

        editTextName = findViewById(R.id.editTextName);

        productType = findViewById(R.id.spinnerType);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, productTypeArray);
        productType.setAdapter(arrayAdapter);

        datePicker = findViewById(R.id.datePickerStartDate);
        datePicker.setMaxDate(System.currentTimeMillis() - 1000);

        //Log.d("DATE", date);
    }

    public void addWarranty(View v){

    }
}
