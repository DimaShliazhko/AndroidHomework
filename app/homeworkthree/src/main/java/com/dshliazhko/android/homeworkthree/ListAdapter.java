package com.dshliazhko.android.homeworkthree;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;
    private final OnContactClickListener onContactClickListener;


    public ListAdapter(Context context, OnContactClickListener onContactClickListener) {
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }


    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    // private List<Contact> item = new ArrayList<Contact>();


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        //  Contact contact = Store.getStore().get(position);
        holder.bind(Store.getStore().get(position));




    }

    @Override
    public int getItemCount() {
        return Store.getStore().size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView name_contact;
        private TextView number_contact;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            name_contact = itemView.findViewById(R.id.name_contact);
            number_contact = itemView.findViewById(R.id.number_contact);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast toast = Toast.makeText(context, " " + getAdapterPosition(), Toast.LENGTH_LONG);
                    toast.show();
                    //  startEditActivity();
                }
            });

        }


        public void bind(final Contact contact, final OnContactClickListener onContactClickListener) {
            String name_contact_ = contact.getEdit_name();
            String number_contact_ = contact.getEdit_contact();
            name_contact.setText(name_contact_);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onContactClickListener.onContactClick(contact);
                }
            });
        }

    }


}