package com.example.myfridge;

//Klasa do ekranu z listą zakupów? jeśli chcemy taką zrobić

// inspiracja https://www.dev2qa.com/android-custom-listview-with-checkbox-example/
// druga inspiracja https://mobiletutors.wordpress.com/2015/07/24/simple-shopping-list-app/

// wywala się po wejściu w shopping list xd nie wiem czy jest sens nad tym siedzieć więc feel free

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false);
    }

}
