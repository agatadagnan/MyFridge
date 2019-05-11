package com.example.myfridge;

/*
obsługa wyglądu produktów


!!!!!
tak naprawde to powinna być klasa Food



 */

import java.util.Calendar;

public class ExampleProduct {
    private long dbId;
    private int mImageResource;
    private String productName;
    private String productCategory;
    private String dateOfPurchase;
    private String expirationDate;
    private Integer caloriesPer100g;
    private String date;


    public ExampleProduct(int imageResource, String name, String category, String dateOfPurchase, String expirationDate) {
        mImageResource = imageResource;
        productName = name;
        productCategory = category;
        this.dateOfPurchase = dateOfPurchase;
        this.expirationDate = expirationDate;
        caloriesPer100g = null;
    }

    public ExampleProduct(int imageResource, String name, String category, String dateOfPurchase, Boolean noExpiration) {
        mImageResource = imageResource;
        productCategory = category;
        productName = name;
        this.dateOfPurchase = dateOfPurchase;
        expirationDate = null;
        caloriesPer100g = null;
    }

    public ExampleProduct(int imageResource, String name, String category, String date){
        mImageResource = imageResource;
        productCategory = category;
        productName = name;
        this.date = date;
    }

    public String date(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date1 = "When?: " + month + "/" + day + "/" + year;
        return date1;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
    }

    public long getDbId(){
        return dbId;
    }

    public int getImageResource(){
        return mImageResource;
    }

    public String getName() {
        return productName;
    }

    public String getPurchase() {
        return dateOfPurchase;
    }

    public String getExpiration() { return expirationDate; }

    public String getCategory() { return productCategory; }

    public Integer getCalories() { return caloriesPer100g; }

    public String getDate() { return date; }
}
