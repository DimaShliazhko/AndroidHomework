package com.dshliazhko.android.homework6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Edit_or_delete_activity extends AppCompatActivity {

    private ContactTable contact;
    private EditText name_edit;
    private EditText contact_edit;
    private Button delete;
    private Button edit;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private OperationDB operationDB;


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
            contact = (ContactTable) arguments.getSerializable("contact");
            name_edit.setText(contact.getEdit_name());
            contact_edit.setText(contact.getEdit_contact());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contact.setEdit_name(name_edit.getText().toString());
                contact.setEdit_contact(contact_edit.getText().toString());
                // Check1 check1 = new Check1(contact);
                //check1.update();

                //   Chek2 check2 = new Chek2(contact);
                // check2.update();

                //Chec3 chec3 = new Chec3(contact);
                //chec3.update();

                operationDB.update();

                setResult(RESULT_OK);
                finish();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check1 check1 = new Check1(contact);
                //check1.delete();

                // Chek2 check2 = new Chek2(contact);
                //check2.delete();

              //  Chec3 chec3 = new Chec3(contact);
               // chec3.delete();
                operationDB.delete();

                setResult(RESULT_OK);

                finish();

            }
        });

    }
}




