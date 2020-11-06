package com.example.projectwarranty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

//activity where new warranty details are inputted
//its meant to:
//-provide a way to input details of new warranty
//-check validity of input and provide feedback on whether input is valid or not
//-create and store new warranty in database
//-go to WarrantyActivity of newly created warranty

public class AddWarrantyActivity extends AppCompatActivity {
    EditText editTextName;

    Spinner productType;
    Spinner warrantyLength;
    ArrayAdapter arrayAdapter;

    //ID                          0              1          2            3                    4
    String[] productTypeArray = {"Mobile phone", "Tablet", "PC/Laptop", "Kitchen appliance", "Other"};
    String[] warrantyLengthArray = {"1", "2", "3"};

    DatePicker datePicker;

    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwarranty);

        editTextName = findViewById(R.id.editTextName);

        productType = findViewById(R.id.spinnerType);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, productTypeArray);
        productType.setAdapter(arrayAdapter);

        warrantyLength = findViewById(R.id.spinnerWarrantyLength);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, warrantyLengthArray);
        warrantyLength.setAdapter(arrayAdapter);

        datePicker = findViewById(R.id.datePickerStartDate);
        datePicker.setMaxDate(System.currentTimeMillis() - 1000);

        db = new DBHelper(this);

        //Log.d("DATE", date);
    }

    public void validateInput(){

    }

    public void addWarranty(View v){
        validateInput();

        String productName = editTextName.getText().toString();
        int productTypePos = productType.getSelectedItemPosition();

        Product product = new Product(productName, productTypePos);
        int startDay = datePicker.getDayOfMonth(), startMonth = datePicker.getMonth(), startYear = datePicker.getYear();
        //Date date = new Date(startYear, startMonth, startDay);

        int warrantyLengthPos = warrantyLength.getSelectedItemPosition()+1;
        Warranty newWarranty = new Warranty(product, startDay, startMonth, startYear, warrantyLengthPos);

        //add to DB
        db.insertWarranty(newWarranty);

        Log.d("NEW WARRANTY", newWarranty.toString());

        Intent intent = new Intent();
        setResult(1, intent);
        finish();//finishing activity
    }
}
