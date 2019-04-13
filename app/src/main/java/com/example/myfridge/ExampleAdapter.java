package com.example.myfridge;

//to jest do tworzenia list produktów jakoś, nie do końca ogarniam, albo chyba do łączenia produktu z wyglądem...

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleProduct> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.productName);
            mTextView2 = itemView.findViewById(R.id.dateOfPurchase);
            mTextView3 = itemView.findViewById(R.id.expirationDate);
        }
    }

    public ExampleAdapter(ArrayList<ExampleProduct> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_product, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int i) {
        ExampleProduct currentItem = mExampleList.get(i);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getPurchase());
        holder.mTextView3.setText(currentItem.getExpiration());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
