package com.example.myfridge;

//najlepsza klasa świata, jak tylko wymyślimy co ma w niej być
// do usunięcia chyba

import android.widget.CheckBox;

public class Food {

    private String productName;
    private String productCategory;
    private String expirationDate;
    private String dateOfPurchase;
    private boolean noExpirationDate; //nie da się przeciążyć konstruktora tymi samymi typami zmiennych, może jakiś checkbox załątwi sprawę...
    private String codeEAN;  //kod kreskowy... ma być obowiązkowy? czy można go dodać później i się nie zepsuje?
    private Integer caloriesPer100g;  //same as kod kreskowy
    private Integer counter;  //żeby wyświetlać najczęściej kupowane, nie mam jeszcze pomysłu jak

    public Food(String productName, String productCategory, String dateOfPurchase, String expirationDate) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.dateOfPurchase = dateOfPurchase;
        this.expirationDate = expirationDate;
    }

    //konstruktor do produktów bez daty przydatności
    public Food(String productName, String productCategory, String dateOfPurchase, boolean noExpirationDate) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.dateOfPurchase = dateOfPurchase;
        this.noExpirationDate = noExpirationDate;
    }
}
