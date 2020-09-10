package com.dshliazhko.android.homeworkfive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.dshliazhko.android.homeworkthree.R;

public class Edit_or_delete_activity extends AppCompatActivity {

    private ContactTable contact;
    private EditText name_edit;
    private EditText contact_edit;
    private Button delete;
    private Button edit;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    // public static final String Contact_ID = "contactId";
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete);

        name_edit = findViewById(R.id.name_edit);
        contact_edit = findViewById(R.id.contact_edit);
        delete = findViewById(R.id.delete);
        edit = findViewById(R.id.edit);
        Bundle arguments = getIntent().getExtras();
        Database db = App.getInstance().getDb();
        final MyDAO myDAO = db.getMyDAO();

        if (arguments != null) {
            contact = (ContactTable) arguments.getSerializable("contact");
            name_edit.setText(contact.getEdit_name());
            contact_edit.setText(contact.getEdit_contact());
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Store.getStore().delete(contact.getId());
                contact.setEdit_name(name_edit.getText().toString());
                contact.setEdit_contact(contact_edit.getText().toString());
                //Store.getStore().add(contact.getId(), contact);
                myDAO.update(contact);
                setResult(RESULT_OK);
                finish();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Dima", "клик едит" + contact.getId());
                // Store.getStore().delete(contact.getId());
                myDAO.delete(contact);
                setResult(RESULT_OK);
                finish();

            }
        });

    }
}




