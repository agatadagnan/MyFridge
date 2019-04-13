package com.example.myfridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Products";
    private static final String TABLE_BEVERAGES = "Beverages";
    private static final String TABLE_DIARY_PRODUCTS = "DiaryProducts";
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
    private static final String CREATE_TABLE_EXP = "(" + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_NAME + " TEXT, " + COL_DATE_PUR + " DATETIME, " + COL_DATE_EXP + " DATETIME, "
            + COL_CALORIES + " INTEGER" + ")";
    private static final String CREATE_TABLE_FRUITS = "(" + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT, "
            + COL_DATE_PUR + " DATETIME, " + COL_CALORIES + " INTEGER" + ")";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_BEVERAGES + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_DIARY_PRODUCTS + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_FRUITS + CREATE_TABLE_FRUITS);
        db.execSQL("CREATE TABLE " + TABLE_GRAIN + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_MEAT + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_SPICES + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_SWEETS + CREATE_TABLE_EXP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEVERAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRUITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRAIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SWEETS);

        onCreate(db);
    }

    public boolean insertProduct(ExampleProduct food){
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
            case "Diary products":
                result = db.insert(TABLE_DIARY_PRODUCTS, null, contentValues);
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
            case "Species":
                result = db.insert(TABLE_SPICES, null, contentValues);
                break;
            case "Sweets":
                result = db.insert(TABLE_SWEETS, null, contentValues);
                break;
        }

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

}
