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

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {
    private EditText editNameContact;
    private EditText editContact;
    private ImageButton save;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    MainActivity main = new MainActivity();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Log.d("DIMA", "Create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        editNameContact = findViewById(R.id.edit_name);
        editContact = findViewById(R.id.edit_contact);
        save = findViewById(R.id.save);

        //recyclerView = findViewById(R.id.recycleView);///////// для запуска в MAinActivity
        recyclerView = findViewById(R.id.recycleView2);/////////для запуска в addContactActivity
        recyclerView.setAdapter(new ListAdapter());

        // Для установки лэйаута в activity_main не раотает((
        //  recyclerView.setLayoutManager(new LinearLayoutManager((Context) getResources().getLayout(R.layout.activity_main), RecyclerView.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addContact();


                startMAinActivity();

            }
        });
    }

    public void addContact() {
        adapter = (ListAdapter) recyclerView.getAdapter();


        if (adapter != null) {
            adapter.addItem(new Contact(editNameContact.getText().toString(), editContact.getText().toString()));
        }

    }

    private void startMAinActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

        private List<Contact> item = new ArrayList<Contact>();


        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            holder.bind(item.get(position));
        }

        @Override
        public int getItemCount() {

            return item.size();
        }

        public void addItem(Contact contact) {
            item.add(contact);
            Log.d("DIMA", "" + item.size());

            notifyItemChanged(item.indexOf(contact));
            //  notifyDataSetChanged();

        }

        private static class ListViewHolder extends RecyclerView.ViewHolder {
            private TextView name_contact;
            private TextView number_contact;

            public ListViewHolder(@NonNull View itemView) {
                super(itemView);
                name_contact = itemView.findViewById(R.id.name_contact);
                number_contact = itemView.findViewById(R.id.number_contact);
            }

            public void bind(Contact contact) {
                String name_contact_ = contact.getEdit_name();
                String number_contact_ = contact.getEdit_contact();
                name_contact.setText(name_contact_);
                number_contact.setText(number_contact_);

            }

        }


    }

}
