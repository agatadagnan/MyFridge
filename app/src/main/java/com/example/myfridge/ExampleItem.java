package com.example.myfridge;

public class ExampleItem {
    private long dbId;

    private String itemName;


    public ExampleItem(String name, String quantity, String unit){

        itemName = name.concat("").concat(quantity).concat(unit);

    }
}
