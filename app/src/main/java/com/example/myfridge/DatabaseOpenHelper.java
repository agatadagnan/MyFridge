package com.example.myfridge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UCharacterEnums;
import android.util.Log;
import android.widget.Spinner;

import java.util.Calendar;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Products";
    private static final String TABLE_PRODUCTS = "Products";
    private static final String TABLE_BEVERAGES = "Beverages";
    private static final String TABLE_DAIRY_PRODUCTS = "DairyProducts";
    private static final String TABLE_FRUITS = "FruitsAndVegetables";
    private static final String TABLE_GRAIN = "GrainProducts";
    private static final String TABLE_MEAT = "Meat";
    private static final String TABLE_SPICES = "Spices";
    private static final String TABLE_SWEETS = "Sweets";
    private static final String TABLE_REMOVED = "Removed";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_DATE_EXP = "ExpirationDate";
    private static final String COL_DATE_PUR = "DateOfPurchase";
    private static final String COL_DATE = "DateRemove";
    private static final String COL_CALORIES = "Calories";
    private static final String COL_CATEGORY = "Category";
    private static final String CREATE_TABLE_EXP = "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, " + COL_CATEGORY + " TEXT, " + COL_DATE_PUR + " DATE, " + COL_DATE_EXP + " DATE, "
            + COL_CALORIES + " INTEGER" + ")";
//    private static final String CREATE_TABLE_FRUITS = "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, "
//            + COL_DATE_PUR + " DATETIME, " + COL_CALORIES + " INTEGER" + ")";
    private static final String CREATE_TABLE_REMOVED = "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, "
            + COL_DATE + " DATE, " + COL_CATEGORY + " TEXT" + ")";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_PRODUCTS + CREATE_TABLE_EXP);
//        db.execSQL("CREATE TABLE " + TABLE_BEVERAGES + CREATE_TABLE_EXP);
//        db.execSQL("CREATE TABLE " + TABLE_DAIRY_PRODUCTS + CREATE_TABLE_EXP);
//        db.execSQL("CREATE TABLE " + TABLE_FRUITS + CREATE_TABLE_FRUITS);
//        db.execSQL("CREATE TABLE " + TABLE_GRAIN + CREATE_TABLE_EXP);
//        db.execSQL("CREATE TABLE " + TABLE_MEAT + CREATE_TABLE_EXP);
//        db.execSQL("CREATE TABLE " + TABLE_SPICES + CREATE_TABLE_EXP);
//        db.execSQL("CREATE TABLE " + TABLE_SWEETS + CREATE_TABLE_EXP);
        db.execSQL("CREATE TABLE " + TABLE_REMOVED + CREATE_TABLE_REMOVED);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BEVERAGES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAIRY_PRODUCTS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRUITS);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRAIN);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAT);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPICES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SWEETS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMOVED);

        onCreate(db);
    }

    public long insertProduct(ExampleProduct food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, food.getName());
        contentValues.put(COL_CATEGORY, food.getCategory());
        long result = -1;

        if(food.getDate() != null && food.getDate().equals("removed")){
            contentValues.put(COL_DATE, food.date());
//            contentValues.put(COL_CATEGORY, food.getCategory());
            result = db.insert(TABLE_REMOVED, null, contentValues);
        }else {
            contentValues.put(COL_CALORIES, food.getCalories());
            contentValues.put(COL_DATE_PUR, food.getPurchase());
            contentValues.put(COL_DATE_EXP, food.getExpiration());
            result = db.insert(TABLE_PRODUCTS, null, contentValues);
//            switch (food.getCategory()) {
//                case "Beverages":
//                    contentValues.put(COL_DATE_EXP, food.getExpiration());
//                    result = db.insert(TABLE_BEVERAGES, null, contentValues);
//                    break;
//                case "Dairy products":
//                    contentValues.put(COL_DATE_EXP, food.getExpiration());
//                    result = db.insert(TABLE_DAIRY_PRODUCTS, null, contentValues);
//                    break;
//                case "Fruits and Vegetables":
//                    result = db.insert(TABLE_FRUITS, null, contentValues);
//                    break;
//                case "Grain products":
//                    contentValues.put(COL_DATE_EXP, food.getExpiration());
//                    result = db.insert(TABLE_GRAIN, null, contentValues);
//                    break;
//                case "Meat":
//                    contentValues.put(COL_DATE_EXP, food.getExpiration());
//                    result = db.insert(TABLE_MEAT, null, contentValues);
//                    break;
//                case "Spices":
//                    contentValues.put(COL_DATE_EXP, food.getExpiration());
//                    result = db.insert(TABLE_SPICES, null, contentValues);
//                    break;
//                case "Sweets":
//                    contentValues.put(COL_DATE_EXP, food.getExpiration());
//                    result = db.insert(TABLE_SWEETS, null, contentValues);
//                    break;
//            }
        }

        return result;
    }

    public Cursor getTableData(Spinner category){
        SQLiteDatabase db = this.getWritableDatabase();

        // 1 tabele 10 kategorii
        // id name data_kupna data_przydatnosci kategoria
        // wszystkie beverage
        // select * from Products where Category = 'Beverage'
        // int ilosc dni od 1970
        // select * from Products where data_przydatnosci < data_dzisiaj

        Cursor res = db.rawQuery("select * from " + TABLE_PRODUCTS + " where Category like '" + category.getSelectedItem().toString() + "'", null);
//        switch (category.getSelectedItem().toString()){
//            case "Beverages":
//                res = db.rawQuery("select * from " + TABLE_BEVERAGES, null);
//                break;
//            case "Dairy products":
//                res = db.rawQuery("select * from " + TABLE_DAIRY_PRODUCTS, null);
//                break;
//            case "Fruits and Vegetables":
//                res = db.rawQuery("select * from " + TABLE_FRUITS, null);
//                break;
//            case "Grain products":
//                res = db.rawQuery("select * from " + TABLE_GRAIN, null);
//                break;
//            case "Meat":
//                res = db.rawQuery("select * from " + TABLE_MEAT, null);
//                break;
//            case "Spices":
//                res = db.rawQuery("select * from " + TABLE_SPICES, null);
//                break;
//            case "Sweets":
//                res = db.rawQuery("select * from " + TABLE_SWEETS, null);
//                break;
//        }
        return res;
    }

    public Cursor getSoonToExpire(Spinner category, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from " + TABLE_PRODUCTS +
                " where " + COL_DATE_EXP + " <= DATE('" + date + "')";
//        Log.d("ITEM", "QUERY: " + query);
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    public Cursor getRemovedData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_REMOVED, null);
        return res;
    }

    public Integer deleteRemoved(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TABLE_REMOVED, "ID = ?", new String[] {id});
        return result;
    }

    public Integer deleteFromDataBase(String id, String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer result = db.delete(TABLE_PRODUCTS, "ID = ?", new String[] {id});
//        switch (category){
//            case "Beverages":
//                result = db.delete(TABLE_BEVERAGES, "ID = ?", new String[] {id});
//                break;
//            case "Dairy products":
//                result = db.delete(TABLE_DAIRY_PRODUCTS, "ID = ?", new String[] {id});
//                break;
//            case "Fruits and Vegetables":
//                result = db.delete(TABLE_FRUITS, "ID = ?", new String[] {id});
//                break;
//            case "Grain products":
//                result = db.delete(TABLE_GRAIN, "ID = ?", new String[] {id});
//                break;
//            case "Meat":
//                result = db.delete(TABLE_MEAT, "ID = ?", new String[] {id});
//                break;
//            case "Spices":
//                result = db.delete(TABLE_SPICES, "ID = ?", new String[] {id});
//                break;
//            case "Sweets":
//                result = db.delete(TABLE_SWEETS, "ID = ?", new String[] {id});
//                break;
//        }
        return result;
    }

    public Integer deleteAndThrowOut(String id, String category, DatabaseOpenHelper productsDB, int position){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_PRODUCTS + " where Category like '" + category + "'", null);
//        switch (category){
//            case "Beverages":
//                res = db.rawQuery("select * from " + TABLE_BEVERAGES, null);
//                break;
//            case "Dairy products":
//                res = db.rawQuery("select * from " + TABLE_DAIRY_PRODUCTS, null);
//                break;
//            case "Fruits and Vegetables":
//                res = db.rawQuery("select * from " + TABLE_FRUITS, null);
//                break;
//            case "Grain products":
//                res = db.rawQuery("select * from " + TABLE_GRAIN, null);
//                break;
//            case "Meat":
//                res = db.rawQuery("select * from " + TABLE_MEAT, null);
//                break;
//            case "Spices":
//                res = db.rawQuery("select * from " + TABLE_SPICES, null);
//                break;
//            case "Sweets":
//                res = db.rawQuery("select * from " + TABLE_SWEETS, null);
//                break;
//        }
        res.moveToPosition(position);
        ExampleProduct food = new ExampleProduct(R.drawable.ic_drink, res.getString(1), category, "removed");
        Integer result = db.delete(TABLE_PRODUCTS, "ID = ?", new String[] {id});
        long added = productsDB.insertProduct(food);
        System.out.println(added);
//        switch (category){
//            case "Beverages":
//                result = db.delete(TABLE_BEVERAGES, "ID = ?", new String[] {id});
//                break;
//            case "Dairy products":
//                result = db.delete(TABLE_DAIRY_PRODUCTS, "ID = ?", new String[] {id});
//                break;
//            case "Fruits and Vegetables":
//                result = db.delete(TABLE_FRUITS, "ID = ?", new String[] {id});
//                break;
//            case "Grain products":
//                result = db.delete(TABLE_GRAIN, "ID = ?", new String[] {id});
//                break;
//            case "Meat":
//                result = db.delete(TABLE_MEAT, "ID = ?", new String[] {id});
//                break;
//            case "Spices":
//                result = db.delete(TABLE_SPICES, "ID = ?", new String[] {id});
//                break;
//            case "Sweets":
//                result = db.delete(TABLE_SWEETS, "ID = ?", new String[] {id});
//                break;
//        }
        return result;
    }
}
