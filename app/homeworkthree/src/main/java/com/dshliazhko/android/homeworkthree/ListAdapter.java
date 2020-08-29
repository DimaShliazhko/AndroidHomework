package com.dshliazhko.android.homeworkthree;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> implements OnContactChange {

    private Context context;
    private OnContactClickListener onContactClickListener;

    private List<Contact> item;

    private Contact contact;
    private AddContactActivity addContactActivity;


    public ListAdapter(Context context, ArrayList<Contact> item, OnContactClickListener onContactClickListener) {
        this.context = context;
        this.onContactClickListener = onContactClickListener;
        this.item = item;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact2, parent, false);
        //     Edit_or_delete_activity edit_or_delete_activity = new Edit_or_delete_activity(this);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        holder.bind(item.get(position), onContactClickListener);
        //Edit_or_delete_activity edit_or_delete_activity = new Edit_or_delete_activity(this);
        //  Edit_or_delete_activity edit_or_delete_activity = new Edit_or_delete_activity(holder.getAdapterPosition());

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


    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onDeleteContact() {
        Log.d("Dima", "DELETE");
        //  item.remove(position);
        // notifyItemRemoved(position);
        //notifyItemRangeChanged(position, item.size());
    }

    @Override
    public void onEditContact() {
        Log.d("Dima", "EDIT");
    }


    public interface OnContactClickListener {
        void onContactClick(Contact contact);

    }


    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView name_contact;
        private TextView number_contact;
        private ImageView imageView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            name_contact = itemView.findViewById(R.id.name_contact);
            number_contact = itemView.findViewById(R.id.number_contact);
            imageView = itemView.findViewById(R.id.image_contact);


        }


        public void bind(final Contact contact, final OnContactClickListener onContactClickListener) {
            String name_contact_ = contact.getEdit_name();
            String number_contact_ = contact.getEdit_contact();
            int image_View_ = contact.getImage_View();

            name_contact.setText(name_contact_);
            number_contact.setText(number_contact_);
            imageView.setImageResource(image_View_);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onContactClickListener.onContactClick(contact);

                }
            });
        }

    }


}