package com.dshliazhko.android.homeworkthree;

import java.util.ArrayList;
import java.util.List;


public class Store {
    private static final Store INST = new Store();

    private final List<Contact> items = new ArrayList<Contact>();

    private Store() {
    }

    public static Store getStore() {
        return INST;
    }

    public void add(int i, Contact contact) {
        this.items.add(i, contact);
    }

    public void add(Contact contact) {
        this.items.add(contact);
    }

    public void delete(int index) {
        this.items.remove(index);
    }

    public void delete(Contact contact) {
        this.items.remove(contact);
    }

    public List<Contact> getAll() {
        return this.items;
    }

    public int size() {
        return this.items.size();
    }

    public Contact get(int index) {
        return this.items.get(index);
    }
}

