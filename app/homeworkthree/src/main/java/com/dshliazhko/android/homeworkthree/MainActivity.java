package com.dshliazhko.android.homeworkthree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonAddContact;
    private TextView searchText;
    private RecyclerView recyclerView;
    //  private ListAdapter adapter;
    private EditText editNameContact;
    private EditText editContact;
    public static final String ContactID = "ContactID";

    private EditText editNameContact1;
    private EditText editContact1;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.recycleView);///////// для запуска в MAinActivity

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(new ListAdapter(this, new ListAdapter.OnContactClickListener() {
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

