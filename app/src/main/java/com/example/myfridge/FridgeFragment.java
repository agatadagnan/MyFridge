package com.example.myfridge;

/*
Klasa do ekranu głównego z produktami w lodówce

pomysł jest taki żeby wyświetlać produkty kategoriami, więc jakąś opcję wyboru trzeba dodać u góry.
Zależnie od kategorii jedzenia będzie wybierana ikonka, jeszcze nie wiem jak to zrobić, myślałam o różnych listach do różnych kategorii
ale to pewnie wyjdzie później
*/

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements AddDialog.AddDialogListener {

    private ArrayList<ExampleProduct> exampleList;
    private FloatingActionButton addProductButton;
    private RecyclerView recyclerView;
    private View rootView;
    private ExampleAdapter exampleAdapter;
    private Spinner productCategoryF;
    private AddDialog.AddDialogListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_fridge, container, false);

        createSpinner(rootView);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);  //because it probably doesn't
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        createList();
        createAdapter(exampleList, recyclerView);

        addProductButton = (FloatingActionButton) rootView.findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
        return rootView;
    }

    public void openDialogAdd(){
        AddDialog addDialog = new AddDialog();
        addDialog.show(getFragmentManager(), "AddDialog");
    }

    public void createList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleProduct(R.drawable.ic_apple, "line 1", "line 2"));
        exampleList.add(new ExampleProduct(R.drawable.ic_bread, "line 3", "line 4"));
        exampleList.add(new ExampleProduct(R.drawable.ic_egg, "line 5", "line 6"));
    }

    public void createAdapter(ArrayList exampleList, RecyclerView recyclerView){
        exampleAdapter = new ExampleAdapter(exampleList);
        recyclerView.setAdapter(exampleAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void createSpinner(View rootView) {
        productCategoryF = rootView.findViewById(R.id.productCategoryF);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCategoryF.setAdapter(adapter);
    }

    /*
    hmmm, to nie będzie raczej na jednej liście tylko chyba różne listy do różnych kategorii, oprócz dodawania produktów do listy
    trzeba tworzyć obiekt food
     */

    //Todo: wali się przy zaakceptowaniu w AddDialog!!!!
    //Todo: ogarnąć się z listami produktów i bazą danych
    @Override
    public void applyData(String name, String category, String expiration) {
        switch (category){
            case "Beverages":
                exampleList.add(exampleList.size(), new ExampleProduct(R.drawable.ic_drink, name, "some calories"));
            case "Diary products":
                exampleList.add(exampleList.size(), new ExampleProduct(R.drawable.ic_bread, name, "some calories"));
        }
    }

    @Override
    public void applyData(String name, String category, String dateOfPurchase, boolean noExpiration) {
        switch (category){
            case "Beverages":
                exampleList.add(exampleList.size(), new ExampleProduct(R.drawable.ic_drink, name, "some calories"));
            case "Diary products":
                exampleList.add(exampleList.size(), new ExampleProduct(R.drawable.ic_bread, name, "some calories"));
        }
    }
}
