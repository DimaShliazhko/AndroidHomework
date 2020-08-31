package com.dshliazhko.android.homeworkthree;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class AddContactActivity extends AppCompatActivity implements Serializable {

    private static int id = 0;
    private ImageButton save;
    private EditText editNameContact;
    private EditText editContact;
    private String editNameContact_;
    private String editContact_;
    private RadioGroup radioGroup;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        final Contact contact;
        contact = new Contact();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        save = findViewById(R.id.save);
        editNameContact = findViewById(R.id.edit_name);
        editContact = findViewById(R.id.edit_contact);
        contact.setImage_View(R.drawable.ic_baseline_contact_phone_24);

        //Создаем данные для передачи:

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setEdit_name(editNameContact.getText().toString());
                contact.setEdit_contact(editContact.getText().toString());

                Store.getStore().add(contact);
                finish();


            }


        });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioButton1:
                        editContact.setHint("Contact");
                        contact.setImage_View(R.drawable.ic_baseline_contact_phone_24);


                        break;
                    case R.id.radioButton2:
                        editContact.setHint("Email");
                        contact.setImage_View(R.drawable.ic_baseline_contact_mail_24);
                        break;
                }

            }
        });


    }

}


