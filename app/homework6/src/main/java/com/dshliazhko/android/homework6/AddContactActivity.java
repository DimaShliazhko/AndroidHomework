package com.dshliazhko.android.homework6;

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


    private ImageButton save;
    private EditText editNameContact;
    private EditText editContact;
    private String editNameContact_;
    private String editContact_;
    private RadioGroup radioGroup;
    private ImageView imageView;
    private RadioGroup mainRadioGroup;
    private OperationDB operationDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        final ContactTable contact;
        contact = new ContactTable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        save = findViewById(R.id.save);
        editNameContact = findViewById(R.id.edit_name);
        editContact = findViewById(R.id.edit_contact);
        contact.setImage_View(R.drawable.ic_baseline_contact_phone_24);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.setEdit_name(editNameContact.getText().toString());
                contact.setEdit_contact(editContact.getText().toString());

                // Check1 check1 = new Check1(contact);
//                check1.insert();

                //   Chek2 check2 = new Chek2(contact);
                // check2.insert();
                // Chec3 chec3 =new Chec3(contact);
                //chec3.insert();

                operationDB.insert();


                setResult(RESULT_OK);
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
