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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    private ArrayList<Contact> item = (ArrayList<Contact>) Store.getStore().getAll();

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.recycleView);///////// для запуска в MAinActivity

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(new ListAdapter(this, item, new ListAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(Contact contact) {
                //    Toast.makeText(MainActivity.this, "user " + contact.getEdit_name(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, Edit_or_delete_activity.class);
                intent.putExtra("contact", contact);
                startActivityForResult(intent, 1000);


            }
        }));


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

