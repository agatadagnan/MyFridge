package com.example.myfridge;

//obsługga okienka do szybkiego dodawania produktów
//jeszcze nic nie robi jbc

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfridge.R;

import java.util.Calendar;
import java.util.Date;

public class AddDialog extends AppCompatDialogFragment {

    private EditText productName;
    private Spinner productCategory;
    private TextView expirationDate;
  //  private CheckBox checkBox;
    private AddDialogListener listener;
    private TextView dateOfPurchase;
    private DatePickerDialog.OnDateSetListener dateSetListener1;
    private DatePickerDialog.OnDateSetListener dateSetListener2;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AddDialogListener) getTargetFragment();
            Log.d("NATALKA", listener.toString());
        } catch (ClassCastException e) {
            throw new ClassCastException(this.toString() + "must implement AddDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add, null);

        productName = view.findViewById(R.id.productNameD);
        productCategory = view.findViewById(R.id.productCategoryD);
        expirationDate = view.findViewById(R.id.expirationDateD);
        dateOfPurchase = view.findViewById(R.id.dateOfPurchaseD);
       // checkBox = view.findViewById(R.id.checkBox);

        dateOfPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog1 = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener1,year, month, day); //????????
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
            }
        });

        expirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog2 = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener2,year, month, day); //????????
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();
            }
        });



        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month,  int dayOfMonth) {
                month = month + 1;
                String date1 = month + "/" + dayOfMonth + "/" + year;
                dateOfPurchase.setText(date1);
            }
        };

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month,  int dayOfMonth) {
                month = month + 1; //jeszcze nie wiem czy tego inaczej nie rozwiazac zeby sie nie psulo gdzies pozniej (o ile sie zepsuje heh)
                String date2 = month + "/" + dayOfMonth + "/" + year;
                expirationDate.setText(date2);
            }
        };

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
                    public void onClick(DialogInterface dialog1, int which) {

                    }
                })
                .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(productName.getText().toString().isEmpty()) {

                            CharSequence text = "No product name!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(getContext(), text, duration);
                            toast.show();

                        }else if(dateOfPurchase.getText().toString().isEmpty()){

                            CharSequence text = "No date of purchase!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(getContext(), text, duration);
                            toast.show();

                        }else if(expirationDate.getText().toString().isEmpty()) {

                            //boolean noExpiration = checkBox.isChecked();
                            boolean noExpiration = true;
                            String name = productName.getText().toString();
                            String category = productCategory.getSelectedItem().toString();
                            String purchase = dateOfPurchase.getText().toString();
                            //  expirationDate.setText("No expiration date");
                            listener.applyData(name, category, purchase, noExpiration);


                        }else{

                            String name = productName.getText().toString();
                            String category = productCategory.getSelectedItem().toString();
                            String expiration = expirationDate.getText().toString(); //moze to lepiej zrobic Calendarem hmmm ¯\_(ツ)_/¯
                            String purchase = dateOfPurchase.getText().toString();
                            listener.applyData(name, category, purchase, expiration);

                        }

                    }
                });

        return builder.create();
    }


    public interface AddDialogListener{
        void applyData(String name, String category, String dateOfPurchase, boolean noExpiration);
        void applyData(String name, String category, String dateOfPurchase, String expiration);
    }
}
