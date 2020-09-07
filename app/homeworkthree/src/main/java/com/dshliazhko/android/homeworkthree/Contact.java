package com.dshliazhko.android.homeworkthree;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private int id;
    private String edit_name;
    private String edit_contact;
    private int image_View;

    public Contact(String edit_name, String edit_contact, int image_View) {
        this.edit_name = edit_name;
        this.edit_contact = edit_contact;
        this.image_View = image_View;

    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact() {
    }

    public Contact(String edit_name, String edit_contact) {
        this.edit_name = edit_name;
        this.edit_contact = edit_contact;
    }

    public int getImage_View() {
        return image_View;
    }

    public void setImage_View(int image_View) {
        this.image_View = image_View;
    }

    public int getId() {
        return id;
    }

    public String getEdit_name() {
        return edit_name;
    }

    public void setEdit_name(String edit_name) {
        this.edit_name = edit_name;
    }

    public String getEdit_contact() {
        return edit_contact;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
