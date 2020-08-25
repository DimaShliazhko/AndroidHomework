package com.dshliazhko.android.homeworkthree;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Edit_or_delete_activity extends AppCompatActivity {

    private Contact contact;
    private EditText name_edit;
    private EditText contact_edit;
    private Button delete;
    private Button edit;
    private RecyclerView recyclerView;
    // public static final String Contact_ID = "contactId";
    private  int id;

    public Button getDelete() {
        return delete;
    }

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
            id = contact.getId();
            Log.d("Dima","id"+id);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Store.getStore().delete(contact);
                //     recyclerView.getAdapter().notifyItemRemoved(0);
                //   recyclerView.getAdapter().notifyItemRangeChanged(0, Store.getStore().size());

                finish();

            }
        });

    }
}




