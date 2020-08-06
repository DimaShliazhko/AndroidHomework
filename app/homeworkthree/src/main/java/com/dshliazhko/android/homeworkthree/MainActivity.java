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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonAddContact;
    private TextView searchText;
    private RecyclerView recyclerView;
  //  private ListAdapter adapter;
    private EditText editNameContact;
    private EditText editContact;


    private EditText editNameContact1;
    private EditText editContact1;
    private final RecyclerView.Adapter adapter = new ListAdapter(this, new ListAdapter.OnContactClickListener() {
        @Override
        public void onContactClick(Contact contact) {

                Intent intent = new Intent(this,Edit_or_delete_activity.class);
                startActivity(intent);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.recycleView);///////// для запуска в MAinActivity

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


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

