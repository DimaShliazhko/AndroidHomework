package com.dshliazhko.android.homeworkthree;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    public static final String ContactID = "ContactID";
    private Button buttonAddContact;
    // private SearchView searchText;
    private RecyclerView recyclerView;
    private EditText editNameContact;
    private EditText editContact;
    private EditText editNameContact1;
    private EditText editContact1;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter;
    private Button delete;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private ArrayList<Contact> item;
    private ListAdapter listAdapter;
    private OnContactChange onContactChange;
    private EditText searchText;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // searchText = findViewById(R.id.search_text);
        //searchText.setIconifiedByDefault(false);
        //searchText.setQueryHint("Поиск");
        // searchText.setOnSearchClickListener();
        searchText = findViewById(R.id.search_text);

        init();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (item != null) {
                    int ii;
                    for (ii = 0; ii < item.size(); ii++) {
                        if (!item.get(ii).getEdit_name().contains(s)) {

                            item.remove(ii);
                            Log.d("Dima", "Поиск " + Store.getStore().get(ii).getEdit_name());

                        }

                    }

                    adapter.notifyDataSetChanged();
                    if (searchText.getText().toString().length() == 0) {
                        Log.d("Dima", "размер" + searchText.getText().toString().length());
                        Log.d("Dima", "размер сторе" + Store.getStore().getAll());
                        item = (ArrayList<Contact>) Store.getStore().getAll();

                        adapter.notifyDataSetChanged();

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        item = (ArrayList<Contact>) Store.getStore().getAll();


        onContactClickListener = new ListAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(Contact contact) {
                //recyclerView.invalidate();

                Intent intent = new Intent(MainActivity.this, Edit_or_delete_activity.class);
                intent.putExtra("contact", contact);

                startActivityForResult(intent, 1000);

            }
        };


        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ListAdapter(this, item, onContactClickListener);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.add_contact_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAddContactActivity();

            }
        });

    }

    private void init() {
    }

    private void startAddContactActivity() {

        Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, 100);

    }
}



