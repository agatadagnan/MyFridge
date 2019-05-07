package com.example.myfridge;
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
import android.widget.AdapterView;
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


public class AddSLDialog extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {
    private EditText itemName;
    private AddSLDialogListener listener;
    private Spinner unit;
    private EditText quantity;
   // private DatePickerDialog.OnDateSetListener dateSetListener1;
   // private DatePickerDialog.OnDateSetListener dateSetListener2;


    @Override
   public void onAttach(Context context) {
       super.onAttach(context);
       try {
           listener = (AddSLDialogListener) getTargetFragment();

       } catch (ClassCastException e) {
           throw new ClassCastException(this.toString() + "must implement AddDialogListener");
       }
   }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_sl, null);

        itemName = view.findViewById(R.id.itemNameD);
        unit = view.findViewById(R.id.unitD);
        quantity = view.findViewById(R.id.quantityD);




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.units,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit.setAdapter(adapter);
        unit.setOnItemSelectedListener(this);

        builder.setView(view).setTitle("Add Product To Shopping List").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        return builder.create();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String unitText = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface AddSLDialogListener{
       void addToSL(String item, String quantity, String unit);
    }
}
