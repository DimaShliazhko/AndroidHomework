package com.dshliazhko.android.homeworkfive;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ContactTable implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String edit_name;
    private String edit_contact;

    private int image_View;


    public ContactTable(String edit_name, String edit_contact, int image_View) {
        this.edit_name = edit_name;
        this.edit_contact = edit_contact;
        this.image_View = image_View;

    }


    public ContactTable() {
    }

    public ContactTable(String edit_name, String edit_contact) {
        this.edit_name = edit_name;
        this.edit_contact = edit_contact;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getImage_View() {
        return image_View;
    }

    public void setImage_View(int image_View) {
        this.image_View = image_View;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactTable)) return false;
        ContactTable that = (ContactTable) o;
        return getId() == that.getId() &&
                getImage_View() == that.getImage_View() &&
                Objects.equals(getEdit_name(), that.getEdit_name()) &&
                Objects.equals(getEdit_contact(), that.getEdit_contact());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEdit_name(), getEdit_contact(), getImage_View());
    }
}
