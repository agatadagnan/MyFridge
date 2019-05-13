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
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);

        void onEatenClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView purchaseTextV;
        public TextView expirationText;
        public TextView dateTextView;
        public ImageView mDeleteImage;
        public ImageView mEatenImage;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.productName);
            purchaseTextV = itemView.findViewById(R.id.dateOfPurchase);
            expirationText = itemView.findViewById(R.id.expirationDate);
            dateTextView = itemView.findViewById(R.id.date);
            mDeleteImage = itemView.findViewById(R.id.image_delete);
            mEatenImage = itemView.findViewById(R.id.image_eaten);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

            mEatenImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEatenClick(position);
                        }
                    }
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<ExampleProduct> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_product, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int i) {
        ExampleProduct currentItem = mExampleList.get(i);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getName());
        holder.purchaseTextV.setText(currentItem.getPurchase());
        holder.expirationText.setText(currentItem.getExpiration());
        holder.dateTextView.setText(currentItem.getDate());
    }

    // ta funkcja update'uje mExampleList zanim powiadomi o zmianie zestawu danych
    // zestaw danych jest przypisywany tylko w momencie inicjalizacji
    public void updateAndNotify(ExampleProduct product) {
        mExampleList.add(product);
        this.notifyItemInserted(mExampleList.size() - 1);
    }

    public void removeAndNotify(int position) {
        mExampleList.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, mExampleList.size());
    }

    public void updateAndNotify(ArrayList<ExampleProduct> productList) {
        mExampleList = productList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
