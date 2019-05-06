package com.example.myfridge;

/*
Klasa do ekranu głównego z produktami w lodówce

pomysł jest taki żeby wyświetlać produkty kategoriami, więc jakąś opcję wyboru trzeba dodać u góry.
Zależnie od kategorii jedzenia będzie wybierana ikonka, jeszcze nie wiem jak to zrobić, myślałam o różnych listach do różnych kategorii
ale to pewnie wyjdzie później
*/

import android.database.Cursor;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements AddDialog.AddDialogListener {

    private ArrayList<ExampleProduct> exampleList;
    private ArrayList<ExampleProduct> productList;
    private FloatingActionButton addProductButton;
    private RecyclerView recyclerView;
    private View rootView;
    private ExampleAdapter exampleAdapter;
    private Spinner productCategoryF;
    private AddDialog.AddDialogListener listener;
    private DatabaseOpenHelper productsDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_fridge, container, false);

        createSpinner(rootView);
        getActivity().setTitle("My Fridge");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);  //because it probably doesn't
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsDB = new DatabaseOpenHelper(getActivity());
        //ExampleProduct food = new ExampleProduct(R.drawable.ic_drink, "dupa", "Beverages", "5/01/2019", "6/03/2019");
        //productsDB.insertProduct(food);

        //createList();
        viewAll();
        createAdapter(productList, recyclerView);
        //createAdapter(exampleList, recyclerView);

        addProductButton = (FloatingActionButton) rootView.findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
        productCategoryF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewAll();
                exampleAdapter.updateAndNotify(productList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }

    public void openDialogAdd(){
        AddDialog addDialog = new AddDialog();
        addDialog.setTargetFragment(this, 1);
        addDialog.show(getFragmentManager(), "AddDialog");
    }

    public void viewAll(){
        Cursor res = productsDB.getTableData(productCategoryF);
        String cat = productCategoryF.getSelectedItem().toString();
        productList = new ArrayList<>();
        if(res.getCount() == 0){
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
            return;
        }
        switch (cat) {
            case "Beverages":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_drink, res.getString(1), cat, res.getString(2), res.getString(3)));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
            case "Dairy products":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_egg, res.getString(1), cat, res.getString(2), res.getString(3)));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
            case "Fruits and Vegetables":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_apple, res.getString(1), cat, res.getString(2), true));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
            case "Grain products":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_bread, res.getString(1), cat, res.getString(2), res.getString(3)));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
            case "Meat":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_meat, res.getString(1), cat, res.getString(2), res.getString(3)));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
            case "Spices":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_salt, res.getString(1), cat, res.getString(2), res.getString(3)));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
            case "Sweets":
                while (res.moveToNext()) {
                    productList.add(new ExampleProduct(R.drawable.ic_sweets, res.getString(1), cat, res.getString(2), res.getString(3)));
                    productList.get(productList.size() - 1).setDbId(res.getInt(0));
                }
                break;
        }
    }

   /* public void createList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleProduct(R.drawable.ic_apple, "line 1", "", "line 2", "expD"));
        exampleList.add(new ExampleProduct(R.drawable.ic_bread, "line 3","", "line 4", "expD"));
        exampleList.add(new ExampleProduct(R.drawable.ic_egg, "line 5","", "line 6", "expD"));
    }*/

    public void createAdapter(ArrayList exampleList, RecyclerView recyclerView){
        exampleAdapter = new ExampleAdapter(exampleList);
        recyclerView.setAdapter(exampleAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        exampleAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //TODO: co chcemy zrobić jak się kliknie na produkt?
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                //ale też będzie dodawał do listy produktów usuniętych i nowej bazy danych???
                // i usuwał z bazy danych
            }

            @Override
            public void onEatenClick(int position) {
                removeItem(position);
            }
        });
    }

    public void removeItem(Integer position){
        ExampleProduct productToRemove = productList.get(position);
        Integer result = productsDB.deleteFromDataBase(String.valueOf(productToRemove.getDbId()), productToRemove.getCategory());
        if (result > 0){
            productList.remove((int)position);
            Toast.makeText(getContext(),"Data deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),"Data not deleted", Toast.LENGTH_LONG).show();
        }
        //exampleList.remove(position);
        exampleAdapter.removeAndNotify(position);
    }

    public void createSpinner(View rootView) {
        productCategoryF = rootView.findViewById(R.id.productCategoryF);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCategoryF.setAdapter(adapter);
    }
    
    //Todo: ogarnąć się z listami produktów i bazą danych
    @Override
    public void applyData(String name, String category, String dateOfPurchase, String expiration) {
        ExampleProduct food = null;
        long newId = 0;
        switch (category){
            case "Beverages":
                food = new ExampleProduct(R.drawable.ic_drink, name, category, dateOfPurchase, expiration);
                break;
            case "Dairy products":
                food = new ExampleProduct(R.drawable.ic_egg, name, category, dateOfPurchase, expiration);
                break;
            case "Grain products":
                food = new ExampleProduct(R.drawable.ic_bread, name, category, dateOfPurchase, expiration);
                break;
            case "Fruits and Vegetables":
                food = new ExampleProduct(R.drawable.ic_apple, name, category, dateOfPurchase, expiration);
                break;
            case "Meat":
                food = new ExampleProduct(R.drawable.ic_meat, name, category, dateOfPurchase, expiration);
                break;
            case "Spices":
                food = new ExampleProduct(R.drawable.ic_salt, name, category, dateOfPurchase, expiration);
                break;
            case "Sweets":
                food = new ExampleProduct(R.drawable.ic_sweets, name, category, dateOfPurchase, expiration);
                //wiem ze to useless ale kocham slodyczki :')
                //dlatego załużyły na oddzielną kategorię :)))
                break;
        }
        newId = productsDB.insertProduct(food);
        food.setDbId(newId);
        //productList.add(food);
        //exampleAdapter.updateAndNotify(productList.get(productList.size() - 1));
    }

    @Override
    public void applyData(String name, String category, String dateOfPurchase, boolean noExpiration) {
        ExampleProduct food = null;
        long newId = 0;
        switch (category){
            case "Beverages":
                food = new ExampleProduct(R.drawable.ic_drink, name, category, dateOfPurchase, noExpiration);
                break;
            case "Dairy products":
                food =  new ExampleProduct(R.drawable.ic_egg, name, category, dateOfPurchase, noExpiration);
                break;
            case "Fruits and Vegetables":
                food = new ExampleProduct(R.drawable.ic_drink, name, category, dateOfPurchase, noExpiration);
                break;
            case "Grain products":
                food = new ExampleProduct(R.drawable.ic_drink, name, category, dateOfPurchase, noExpiration);
                break;
            case "Meat":
                food = new ExampleProduct(R.drawable.ic_drink, name, category, dateOfPurchase, noExpiration);
                break;
            case "Spices":
                food = new ExampleProduct(R.drawable.ic_drink, name, category, dateOfPurchase, noExpiration);
                break;
            case "Sweets":
                food = new ExampleProduct(R.drawable.ic_sweets, name, category, dateOfPurchase, noExpiration);
                break;
        }
        newId = productsDB.insertProduct(food);
        food.setDbId(newId);


        //tego nie powinno tu być!!!
        //productList.add(food);
        //exampleAdapter.updateAndNotify(productList.get(productList.size() - 1));

    }
}
