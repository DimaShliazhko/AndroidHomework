package com.dshliazhko.android.homeworkthree;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class Contact {

    private String edit_name;
    private String edit_contact;

    public Contact(String edit_name, String edit_contact) {
        this.edit_name = edit_name;
        this.edit_contact = edit_contact;
    }


    public String getEdit_name() {
        return edit_name;
    }

    public String getEdit_contact() {
        return edit_contact;
    }

    public void setEdit_name(String edit_name) {
        this.edit_name = edit_name;
    }

    public void setEdit_contact(String edit_contact) {
        this.edit_contact = edit_contact;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Contact) {
            Contact contact = (Contact) obj;
            return Objects.equals(edit_name, contact.edit_name) && Objects.equals(edit_contact, contact.edit_contact);
        }
        return false;
    }
}