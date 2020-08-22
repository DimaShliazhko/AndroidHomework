package com.dshliazhko.android.homeworkthree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

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

                Store.getStore().add(new Contact(editNameContact.getText().toString(), editContact.getText().toString()));

                finish();


            }


        });


    }

}


