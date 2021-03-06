package com.example.myfridge;

//Klasa do ekranu z wyrzuconymi produktami !!tu java spada z rowerka!!

import android.arch.lifecycle.Lifecycle;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RemovedFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private ArrayList<ExampleProduct> productList;
    private ExampleAdapter exampleAdapter;
    private DatabaseOpenHelper productsDB;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_fridge, container, false);
        getActivity().setTitle("Thrown out");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);  //because it probably doesn't
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsDB = new DatabaseOpenHelper(getActivity());

        //createList();
        viewAll();
        createAdapter(productList, recyclerView);

        return rootView;
    }

    public void createList() {
        productList = new ArrayList<>();
        productList.add(new ExampleProduct(R.drawable.ic_apple, "line 1", "", "line 2", "expD"));
        productList.add(new ExampleProduct(R.drawable.ic_bread, "line 3","", "line 4", "expD"));
        productList.add(new ExampleProduct(R.drawable.ic_egg, "line 5","", "line 6", "expD"));
    }

    public void createAdapter(ArrayList exampleList, RecyclerView recyclerView){
        exampleAdapter = new ExampleAdapter(exampleList);
        recyclerView.setAdapter(exampleAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        exampleAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
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

    public void viewAll() {
        Cursor res = productsDB.getRemovedData();
        productList = new ArrayList<>();
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
            return;
        }else {
            while(res.moveToNext()){
                switch (res.getString(3)) {
                    case "Beverages":
                        productList.add(new ExampleProduct(R.drawable.ic_drink, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                    case "Dairy products":
                        productList.add(new ExampleProduct(R.drawable.ic_egg, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                    case "Fruits and Vegetables":
                        productList.add(new ExampleProduct(R.drawable.ic_apple, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                    case "Grain products":
                        productList.add(new ExampleProduct(R.drawable.ic_bread, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                    case "Meat":
                        productList.add(new ExampleProduct(R.drawable.ic_meat, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                    case "Spices":
                        productList.add(new ExampleProduct(R.drawable.ic_salt, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                    case "Sweets":
                        productList.add(new ExampleProduct(R.drawable.ic_sweets, res.getString(1), res.getString(3), res.getString(2)));
                        productList.get(productList.size() - 1).setDbId(res.getInt(0));
                        break;
                }
            }
        }
    }

    // a konkretniej to tutaj java spada z rowerka tylko że nie ma żadnego błędu ale no pieprzy się....
    public void removeItem(Integer position){
        //productList.remove((int)position);
        Integer result = productsDB.deleteRemoved(position.toString());
        if (result > 0){
            Toast.makeText(getContext(),"Data deleted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),"Data not deleted", Toast.LENGTH_LONG).show();
        }
        exampleAdapter.removeAndNotify(position);
        //exampleAdapter.notifyItemRemoved(position);
        //exampleAdapter.notifyItemRangeChanged(position, productList.size());
    }
}
