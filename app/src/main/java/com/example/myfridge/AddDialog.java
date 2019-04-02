package com.example.myfridge;

//obsługga okienka do szybkiego dodawania produktów
//jeszcze nic nie robi jbc

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myfridge.R;

public class AddDialog extends AppCompatDialogFragment {

    private EditText productName;
    private Spinner productCategory;
    private EditText expirationDate;
    private EditText dateOfPurchase;
    private CheckBox checkBox;
    private AddDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add, null);

        productName = view.findViewById(R.id.productNameD);
        productCategory = view.findViewById(R.id.productCategoryD);
        expirationDate = view.findViewById(R.id.expirationDateD);
        dateOfPurchase = view.findViewById(R.id.dateOfPurchaseD);
        checkBox = view.findViewById(R.id.checkBox);

        //dodanie kategorii do spinnera
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCategory.setAdapter(adapter);

        //przy kliknięciu accept się wali... "Attempt to invoke interface method (...) on a null object reference at(...)" linia 73

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
                        if(checkBox.isChecked()) {
                            String name = productName.getText().toString();
                            String category = productCategory.getSelectedItem().toString();
                            String purchase = dateOfPurchase.getText().toString();
                            boolean noExpiration = checkBox.isChecked();
                            listener.applyData(name, category, purchase, noExpiration);

                        }else{
                            String name = productName.getText().toString();
                            String category = productCategory.getSelectedItem().toString();
                            String expiration = expirationDate.getText().toString();
                            listener.applyData(name, category, expiration);
                        }

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            listener = (AddDialogListener) childFragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(childFragment.toString() + "must implement AddDialogListener");
        }
    }

    public interface AddDialogListener{
        void applyData(String name, String category, String dateOfPurchase, boolean noExpiration);
        void applyData(String name, String category, String expiration);
    }
}
