package com.dshliazhko.android.homeworkfive;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dshliazhko.android.homeworkthree.R;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    public static final String ContactID = "ContactID";

    private RecyclerView recyclerView;
    private EditText editNameContact;
    private EditText editContact;
    private EditText editNameContact1;
    private EditText editContact1;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter;
    private Button delete;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private List<ContactTable> item;
    private ListAdapter listAdapter;

    private EditText searchText;
    private MyDAO myDAO;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initDB();

        // Log.d("Dima", "обновляем адаптер" + myDAO.getAll());
        //adapter.notifyDataSetChanged();
        adapter = new ListAdapter(this, (List<ContactTable>) item, onContactClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.search_text);
        myDAO = initDB();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                if (item != null) {
                    String s1 = searchText.getText().toString();

                    Log.d("Dima", "поиск " + s1);
                    item = myDAO.search(s1);
                    Log.d("Dima", "поиск " + myDAO.search(s1));
                    setAdapter();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        onContactClickListener = new ListAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(ContactTable contactTable) {

                Intent intent = new Intent(MainActivity.this, Edit_or_delete_activity.class);
                intent.putExtra("contact", contactTable);

                startActivityForResult(intent, 1000);

            }
        };


        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        setAdapter();

        findViewById(R.id.add_contact_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startAddContactActivity();

            }
        });

    }


    private void startAddContactActivity() {

        Intent intent = new Intent(this, AddContactActivity.class);
        startActivityForResult(intent, 100);

    }

    private void setAdapter() {
        adapter = new ListAdapter(this, (List<ContactTable>) item, onContactClickListener);
        recyclerView.setAdapter(adapter);

    }

    public MyDAO initDB() {
        Database db = App.getInstance().getDb();
        final MyDAO myDAO = db.getMyDAO();
        item = myDAO.getAll();
        return myDAO;
    }
}



