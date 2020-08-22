package com.dshliazhko.android.homeworkthree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context context;
    private OnContactClickListener onContactClickListener;
    private List<Contact> item = Store.getStore().getAll();
    private List<Contact> store;
    private Contact contact;


    public ListAdapter(Context context, OnContactClickListener onContactClickListener) {
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }

    public List<Contact> getItem() {
        return item;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Contact contact = Store.getStore().get(position);

        holder.bind(item.get(position), onContactClickListener);




/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int newPosition = holder.getAdapterPosition();

                item.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, item.size());


            }
        });
*/


    }

    private void removeItem(int position) {
        // int newPosition = holder.getAdapterPosition();
        int newPosition = position;
        item.remove(newPosition);
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, item.size());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView name_contact;
        private TextView number_contact;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            name_contact = itemView.findViewById(R.id.name_contact);
            number_contact = itemView.findViewById(R.id.number_contact);


        }


        public void bind(final Contact contact, final OnContactClickListener onContactClickListener) {
            String name_contact_ = contact.getEdit_name();
            String number_contact_ = contact.getEdit_contact();
            name_contact.setText(name_contact_);
            number_contact.setText(number_contact_);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onContactClickListener.onContactClick(contact);

                }
            });
        }

    }


}