package com.example.myfridge;

//obsługga okienka do szybkiego dodawania produktów
//jeszcze nic nie robi jbc

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.myfridge.R;

public class AddDialog extends AppCompatDialogFragment {

    private EditText productName;
    private EditText productCategory;
    private EditText expirationDate;
    private EditText dateOfPurchase;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add, null);

        productName = view.findViewById(R.id.productNameD);
        productCategory = view.findViewById(R.id.productCategoryD);
        expirationDate = view.findViewById(R.id.expirationDateD);
        dateOfPurchase = view.findViewById(R.id.dateOfPurchaseD);

        builder.setView(view).
                setTitle("Add Product").
                setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
