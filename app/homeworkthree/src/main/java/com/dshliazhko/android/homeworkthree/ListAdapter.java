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


class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> /*implements OnContactChange */ {

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
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        item.get(position).setId(holder.getAdapterPosition());
        //Log.d("dima", "позиция" + holder.getAdapterPosition());
        holder.bind(item.get(position), holder.getAdapterPosition(), onContactClickListener);
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
        return Store.getStore().size();
    }

    public void filter() {
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


        public void bind(final Contact contact, int adapterPosition, final OnContactClickListener onContactClickListener) {
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