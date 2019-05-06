package com.example.myfridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Products";
    private static final String TABLE_BEVERAGES = "Beverages";
    private static final String TABLE_DAIRY_PRODUCTS = "DairyProducts";
    private static final String TABLE_FRUITS = "FruitsAndVegetables";
    private static final String TABLE_GRAIN = "GrainProducts";
    private static final String TABLE_MEAT = "Meat";
    private static final String TABLE_SPICES = "Spices";
    private static final String TABLE_SWEETS = "Sweets";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_DATE_EXP = "ExpirationDate";
    private static final String COL_DATE_PUR = "DateOfPurchase";
    private static final String COL_CALORIES = "Calories";
    private static final String CREATE_TABLE_EXP = "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, " + COL_DATE_PUR + " DATETIME, " + COL_DATE_EXP + " DATETIME, "
            + COL_CALORIES + " INTEGER" + ")";
    private static final String CREATE_TABLE_FRUITS = "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, "
            + COL_DATE_PUR + " DATETIME, " + COL_CALORIES + " INTEGER" + ")";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_BEVERAGES + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_DAIRY_PRODUCTS + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_FRUITS + CREATE_TABLE_FRUITS);
        db.execSQL("CREATE TABLE " + TABLE_GRAIN + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_MEAT + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_SPICES + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_SWEETS + CREATE_TABLE_EXP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEVERAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAIRY_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRUITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SWEETS);

        onCreate(db);
    }

    public long insertProduct(ExampleProduct food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, food.getName());
        contentValues.put(COL_DATE_PUR, food.getPurchase());
        contentValues.put(COL_DATE_EXP, food.getExpiration());
        contentValues.put(COL_CALORIES, food.getCalories());
        long result = -1;
        switch (food.getCategory()) {
            case "Beverages":
                result = db.insert(TABLE_BEVERAGES, null, contentValues);
                break;
            case "Dairy products":
                result = db.insert(TABLE_DAIRY_PRODUCTS, null, contentValues);
                break;
            case "Fruits and Vegetables":
                result = db.insert(TABLE_FRUITS, null, contentValues);
                break;
            case "Grain products":
                result = db.insert(TABLE_GRAIN, null, contentValues);
                break;
            case "Meat":
                result = db.insert(TABLE_MEAT, null, contentValues);
                break;
            case "Spices":
                result = db.insert(TABLE_SPICES, null, contentValues);
                break;
            case "Sweets":
                result = db.insert(TABLE_SWEETS, null, contentValues);
                break;
        }

        return result;
    }

    public Cursor getTableData(Spinner category){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_BEVERAGES, null);
        switch (category.getSelectedItem().toString()){
            case "Beverages":
                res = db.rawQuery("select * from " + TABLE_BEVERAGES, null);
                break;
            case "Dairy products":
                res = db.rawQuery("select * from " + TABLE_DAIRY_PRODUCTS, null);
                break;
            case "Fruits and Vegetables":
                res = db.rawQuery("select * from " + TABLE_FRUITS, null);
                break;
            case "Grain products":
                res = db.rawQuery("select * from " + TABLE_GRAIN, null);
                break;
            case "Meat":
                res = db.rawQuery("select * from " + TABLE_MEAT, null);
                break;
            case "Spices":
                res = db.rawQuery("select * from " + TABLE_SPICES, null);
                break;
            case "Sweets":
                res = db.rawQuery("select * from " + TABLE_SWEETS, null);
                break;
        }
        return res;
    }

    public Integer deleteFromDataBase(String id, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = 0;
        switch (category){
            case "Beverages":
                result = db.delete(TABLE_BEVERAGES, "ID = ?", new String[] {id});
                break;
            case "Dairy products":
                result = db.delete(TABLE_DAIRY_PRODUCTS, "ID = ?", new String[] {id});
                break;
            case "Fruits and Vegetables":
                result = db.delete(TABLE_FRUITS, "ID = ?", new String[] {id});
                break;
            case "Grain products":
                result = db.delete(TABLE_GRAIN, "ID = ?", new String[] {id});
                break;
            case "Meat":
                result = db.delete(TABLE_MEAT, "ID = ?", new String[] {id});
                break;
            case "Spices":
                result = db.delete(TABLE_SPICES, "ID = ?", new String[] {id});
                break;
            case "Sweets":
                result = db.delete(TABLE_SWEETS, "ID = ?", new String[] {id});
                break;
        }
        return result;
    }
}
