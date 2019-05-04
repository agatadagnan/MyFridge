package com.example.myfridge;

//Klasa do ekranu z listą zakupów? jeśli chcemy taką zrobić

// inspiracja https://www.dev2qa.com/android-custom-listview-with-checkbox-example/
// druga inspiracja https://mobiletutors.wordpress.com/2015/07/24/simple-shopping-list-app/

// wywala się po wejściu w shopping list xd nie wiem czy jest sens nad tym siedzieć więc feel free

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import com.example.myfridge.ListView.ListViewItemCheckboxBaseAdapter;
import com.example.myfridge.ListView.ListViewItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListFragment extends ListFragment {

    private ListView listView;
    private ListViewItemCheckboxBaseAdapter listViewDataAdapter;
    private List<ListViewItemDTO> initItemList;
    private EditText mItemEdit;
    private Button mAddButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Shopping list");
        //ArrayAdapter<String> adapret = new ArrayAdapter<String>(getActivity(), R.layout.fragment_shopping_list, shoppingList);
        listView = (ListView) getActivity().findViewById(R.id.list_view_with_checkbox);
        mItemEdit = (EditText) getActivity().findViewById(R.id.item_editText);
        mAddButton = (Button) getActivity().findViewById(R.id.add_button);
        initItemList = this.getInitViewItemDtoList();
        listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getActivity(), initItemList);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listView.setAdapter(listViewDataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                //Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
            }
        });

        // Click this button to remove selected items from listview.
        Button selectRemoveButton = (Button) listView.findViewById(R.id.list_remove_selected_rows);
        selectRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setMessage("Are you sure to remove selected listview items?");

                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        int size = initItemList.size();
                        for(int i=0;i<size;i++)
                        {
                            ListViewItemDTO dto = initItemList.get(i);

                            if(dto.isChecked())
                            {
                                initItemList.remove(i);
                                i--;
                                size = initItemList.size();
                            }
                        }

                        listViewDataAdapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = mItemEdit.getText().toString();
                //listViewDataAdapter.add(item);
                listViewDataAdapter.notifyDataSetChanged();
                mItemEdit.setText("");
            }
        });
    }

    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList()
    {
        String itemTextArr[] = {"Android", "iOS", "Java", "JavaScript", "JDBC", "JSP", "Linux", "Python", "Servlet", "Windows"};

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        int length = itemTextArr.length;

        for(int i=0;i<length;i++)
        {
            String itemText = itemTextArr[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }

}
