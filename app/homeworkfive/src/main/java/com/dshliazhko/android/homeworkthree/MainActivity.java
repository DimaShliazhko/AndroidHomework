package com.dshliazhko.android.homeworkthree;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    public static final String ContactID = "ContactID";
    private Button buttonAddContact;
    private SearchView searchText;
    private RecyclerView recyclerView;
    private EditText editNameContact;
    private EditText editContact;
    private EditText editNameContact1;
    private EditText editContact1;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private RecyclerView.Adapter adapter;
    private Button delete;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private ArrayList<Contact> item;
    private ListAdapter listAdapter;
    private OnContactChange onContactChange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_text);
        searchText.setIconifiedByDefault(false);
        searchText.setQueryHint("Поиск");
        // searchText.setOnSearchClickListener();


        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        Bundle arguments = getIntent().getExtras();
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

    private void startAddContactActivity() {

        Intent intent = new Intent(this, AddContactActivity.class);

        startActivity(intent);
    }
}



