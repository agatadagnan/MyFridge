package com.example.myfridge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Products";
    private static final String TABLE_BEVERAGES = "Beverages";
    private static final String TABLE_DIARY_PRODUCTS = "Diary Products";
    private static final String TABLE_FRUITS = "Fruits and Vegetables";
    private static final String TABLE_GRAIN = "Grain products";
    private static final String TABLE_MEAT = "Meat";
    private static final String TABLE_SPICES = "Spices";
    private static final String TABLE_SWEETS = "Sweets";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_DATE_EXP = "Expiration Date";
    private static final String COL_DATE_PUR = "Date of purchase";
    private static final String COL_CALORIES = "Calories";
    private static final String CREATE_TABLE_EXP = "(" + COL_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, " + COL_DATE_PUR + " DATETIME, " + COL_DATE_EXP + " DATETIME, "
            + COL_CALORIES + " INTEGER" + ")";
    private static final String CREATE_TABLE_FRUITS = "CREATE TABLE " + TABLE_BEVERAGES
            + "(" + COL_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, "
            + COL_DATE_PUR + " DATETIME, " + COL_CALORIES + " INTEGER" + ")";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
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
}
