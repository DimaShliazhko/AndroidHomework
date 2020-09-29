package com.dshliazhko.android.homework6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    public static final String ContactID = "ContactID";
    private RecyclerView recyclerView;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter;
    private RadioGroup radioGroup;
    private List<ContactTable> item;
    private ListAdapter listAdapter;

    private EditText searchText;
    private MyDAO myDAO;
    private Check1 check1;
    private Chek2 check2;
    private Handler handler;
    private Context context = MainActivity.this;

    public OperationDB getOperationDB() {
        return operationDB;
    }

    private OperationDB operationDB;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check1 = new Check1(handler, adapter, context, onContactClickListener);
        //check1.initDB();
        //initRecycle();

        //  check2 = new Chek2(adapter, context, onContactClickListener,recyclerView);
        // check2.initDB();
        Chec3 chec3 = new Chec3(adapter, context, onContactClickListener, recyclerView);
        chec3.initDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.search_text);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                if (message.what == 0) {
                    Log.d("Dima", "Перенос инфы в главный поток....");
                    item = (List<ContactTable>) message.obj;
                    Log.d("Dima", "Получаем" + (List<ContactTable>) message.obj);
                } else if (message.what == 1) {
                    recyclerView.setAdapter((RecyclerView.Adapter) message.obj);

                }
                return false;
            }
        });

        radioGroup = findViewById(R.id.mainRadioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.mainRadioButton1:
                        operationDB = new Check1(handler, adapter, context, onContactClickListener);
                        //     check1 = new Check1(handler, adapter, context, onContactClickListener);
                       // operationDB.initDB();
                        //  initRecycle();
                        break;
                    case R.id.mainRadioButton2:
                        operationDB = new Chek2(adapter, context, onContactClickListener,recyclerView);
                        //   check2 = new Chek2(adapter, context, onContactClickListener,recyclerView);
                        //   check2.initDB();

                        break;
                    case R.id.mainRadioButton3:
                        operationDB =  new Chec3(adapter, context, onContactClickListener, recyclerView);
                       // Chec3 chec3 = new Chec3(adapter, context, onContactClickListener, recyclerView);
                        //chec3.initDB();
                        break;
                }
                //  initRecycle();
                operationDB.initDB();
            }
        });

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

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        onContactClickListener = new ListAdapter.OnContactClickListener() {
            @Override
            public void onContactClick(ContactTable contactTable) {
                Log.d("Dima", "запуск новой активити");
                Intent intent = new Intent(MainActivity.this, Edit_or_delete_activity.class);
                intent.putExtra("contact", contactTable);

                startActivityForResult(intent, 1000);

            }
        };

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


    private void initRecycle() {

        adapter = new ListAdapter(this, (List<ContactTable>) item, onContactClickListener);
        Log.d("Dima", "Адаптер");
        //  recyclerView.setAdapter(adapter);
    }
}



