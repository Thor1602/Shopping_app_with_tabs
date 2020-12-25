package com.example.shoppingapp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class frag_2 extends Fragment {
    ListView totalItems;
    Button addItem;
    Button add_to_shoppinglist;
    EditText english, korean;
    Button delete;
    ListView shopping_list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2_layout, container, false);
        addItem = view.findViewById(R.id.addItem);
        add_to_shoppinglist = view.findViewById(R.id.addToShopping);
        korean = view.findViewById(R.id.korean);
        english = view.findViewById(R.id.english);
        totalItems = view.findViewById(R.id.totalItems);
        delete = view.findViewById(R.id.btn_delete);
        shopping_list = view.findViewById(R.id.shopping_list);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        updateList();
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Items item;
                try {
                    item = new Items(-1, korean.getText().toString(), english.getText().toString());

                }
                catch (Exception e){
                    item = new Items(-1, "에러", "error");

                }
                boolean addOne = databaseHelper.addOne(item);
                Toast.makeText(getActivity(), "Success: " + addOne, Toast.LENGTH_SHORT).show();
                updateList();
            }
        });
        totalItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Items clickedItem = (Items) parent.getItemAtPosition(position);
                // DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                // databaseHelper.delOne(clickedItem);
                // updateList();
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
     private void updateList() {
         DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
         ArrayAdapter itemAdapter = new ArrayAdapter<Items>(getActivity(), android.R.layout.simple_expandable_list_item_1, databaseHelper.getAllData());
         totalItems.setAdapter(itemAdapter);
    }

}

