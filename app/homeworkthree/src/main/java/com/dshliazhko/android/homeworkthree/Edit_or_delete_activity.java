package com.dshliazhko.android.homeworkthree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Edit_or_delete_activity extends AppCompatActivity {

    private Contact contact;
    private EditText name_edit;
    private EditText contact_edit;
    private Button delete;
    private Button edit;
    private RecyclerView.Adapter adapter;
    // public static final String Contact_ID = "contactId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete);

        name_edit = findViewById(R.id.name_edit);
        contact_edit = findViewById(R.id.contact_edit);
        delete = findViewById(R.id.delete);
        edit = findViewById(R.id.edit);
        Bundle arguments = getIntent().getExtras();

        if (arguments != null) {
            contact = (Contact) arguments.getSerializable("contact");
            name_edit.setText(contact.getEdit_name());
            contact_edit.setText(contact.getEdit_contact());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Store.getStore().delete(0);
                adapter.notifyDataSetChanged();
                finish();

            }
        });

    }
}




