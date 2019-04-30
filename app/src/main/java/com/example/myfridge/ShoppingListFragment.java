package com.example.myfridge;

//Klasa do ekranu z listą zakupów? jeśli chcemy taką zrobić

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private ArrayList<String> shoppingList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Shopping list");
        ArrayAdapter<String> adapret = new ArrayAdapter<String>(getActivity(), R.layout.fragment_shopping_list, shoppingList);

    }
}
