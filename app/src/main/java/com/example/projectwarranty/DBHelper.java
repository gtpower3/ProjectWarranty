package com.example.projectwarranty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    /*
    String productName = editTextName.getText().toString();
        int productTypePos = productType.getSelectedItemPosition();

        Product product = new Product(productName, productTypePos);
        int startDay = datePicker.getDayOfMonth(), startMonth = datePicker.getMonth(), startYear = datePicker.getYear();
        int warrantyLengthPos = warrantyLength.getSelectedItemPosition()+1;
        Warranty newWarranty = new Warranty(product, startDay, startMonth, startYear, warrantyLengthPos);
     */

    public static final String DATABASE_NAME = "WarrantyDB.db";
    public static final String WARRANTY_TABLE_NAME = "Warranties";
    public static final String WARRANTY_COLUMN_ID = "id";
    public static final String WARRANTY_COLUMN_PRODUCTNAME = "productName";
    public static final String WARRANTY_COLUMN_PRODUCTTYPE = "productType";
    public static final String WARRANTY_COLUMN_STARTDATE = "startDate";
    public static final String WARRANTY_COLUMN_LENGTH = "length";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + WARRANTY_TABLE_NAME + "("
                        + WARRANTY_COLUMN_ID + " integer primary key, "
                        + WARRANTY_COLUMN_PRODUCTNAME + " text, "
                        + WARRANTY_COLUMN_PRODUCTTYPE + " text, "
                        + WARRANTY_COLUMN_STARTDATE + " text, "
                        + WARRANTY_COLUMN_LENGTH + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + WARRANTY_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertWarranty (String productName, String productType, String startDate, int length) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WARRANTY_COLUMN_PRODUCTNAME, productName);
        contentValues.put(WARRANTY_COLUMN_PRODUCTTYPE, productType);
        contentValues.put(WARRANTY_COLUMN_STARTDATE, startDate);
        contentValues.put(WARRANTY_COLUMN_LENGTH, length);
        db.insert(WARRANTY_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertWarranty (Warranty warranty) {
        Log.d("SQL INSERT", "ADDED " + warranty.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WARRANTY_COLUMN_PRODUCTNAME, warranty.getProduct().getName());
        contentValues.put(WARRANTY_COLUMN_PRODUCTTYPE, warranty.getProduct().getType());
        contentValues.put(WARRANTY_COLUMN_STARTDATE, warranty.toDate().toString());
        contentValues.put(WARRANTY_COLUMN_LENGTH, warranty.getWarrantyLength());
        db.insert(WARRANTY_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + WARRANTY_TABLE_NAME + " where id=" + id +"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, WARRANTY_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Warranty> getAllWarranties() {
        ArrayList<Warranty> warrantyList = new ArrayList<Warranty>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + WARRANTY_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Warranty warranty = new Warranty();
                warranty.setProduct(new Product(cursor.getString(1), cursor.getInt(2)));
                warranty.setEpochDate(new Date(1,1,1));
                //warranty.setEpochDate(new Date(cursor.getString(3)));
                warranty.setWarrantyLength(cursor.getInt(4));
                // Adding contact to list
                warrantyList.add(warranty);
            } while (cursor.moveToNext());
        }

        // return contact list
        return warrantyList;
    }
}

