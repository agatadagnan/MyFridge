package com.example.myfridge;

//Klasa do ekranu z listą zakupów? jeśli chcemy taką zrobić

// inspiracja https://www.dev2qa.com/android-custom-listview-with-checkbox-example/
// druga inspiracja https://mobiletutors.wordpress.com/2015/07/24/simple-shopping-list-app/

// wywala się po wejściu w shopping list xd nie wiem czy jest sens nad tym siedzieć więc feel free

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class ShoppingListFragment extends Fragment implements AddSLDialog.AddSLDialogListener {

    private ArrayList<ExampleItem> shoppingList;
   // private ArrayList<ExampleProduct> productList;
    private FloatingActionButton addItemButton;
    private RecyclerView recyclerViewSL;
    private View rootViewSL;
    private ExampleAdapter exampleAdapter;
   // private Spinner productCategoryF;
    private AddSLDialog.AddSLDialogListener listener;
    private DatabaseOpenHelper productsDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootViewSL = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        createSpinner(rootViewSL);
        getActivity().setTitle("Shopping List");
       // final SwipeRefreshLayout pullToRefreshSL = rootViewSL.findViewById(R.id.pullToRefreshSL);
      //  pullToRefreshSL.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           // @Override
           // public void onRefresh() {
         //       refreshData(); // your code
        //        pullToRefreshSL.setRefreshing(false);
          //  }
       // });
        recyclerViewSL = (RecyclerView) rootViewSL.findViewById(R.id.recyclerViewSL);
        recyclerViewSL.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsDB = new DatabaseOpenHelper(getActivity());
//        productsDB.onUpgrade(productsDB.getWritableDatabase(), productsDB.getWritableDatabase().getVersion(), productsDB.getWritableDatabase().getVersion());

        //createList();
       // viewAll();
        //createAdapter(productList, recyclerView);
        //createAdapter(exampleList, recyclerView);
        addItemButton = (FloatingActionButton) rootViewSL.findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAddSL();
            }
        });
     /*   productCategoryF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewAll();
                exampleAdapter.updateAndNotify(productList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        return rootViewSL;
    }

    private void openDialogAddSL() {
        AddSLDialog addSLDialog = new AddSLDialog();
        addSLDialog.setTargetFragment(this, 1);
        addSLDialog.show(getFragmentManager(), "AddSLDialog");
    }

   // private void refreshData() {
    //}

    private void createSpinner(View rootView) {
    }


    @Override
    public void addToSL(String item, String quantity, String unit) {
        ExampleItem food = new ExampleItem(item,quantity,unit);
        //long newId = 0;
        }

//    public void viewSL(){
//        shoppingList = new ArrayList<>();
//
//
//    }
}
