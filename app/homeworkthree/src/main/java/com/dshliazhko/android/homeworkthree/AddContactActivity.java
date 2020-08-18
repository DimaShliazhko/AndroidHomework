package com.dshliazhko.android.homeworkthree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity implements Serializable {

    private ImageButton save;
    private EditText editNameContact;
    private EditText editContact;
    private String editNameContact_;
    private String editContact_;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        save = findViewById(R.id.save);



        //Создаем данные для передачи:

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editNameContact = findViewById(R.id.edit_name);
                editContact = findViewById(R.id.edit_contact);

                Store.getStore().add( new Contact(editNameContact.getText().toString(),editContact.getText().toString()));

                  finish();


            }


        });


    }

    }


