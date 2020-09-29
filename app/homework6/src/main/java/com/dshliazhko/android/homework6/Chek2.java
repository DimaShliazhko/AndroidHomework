package com.dshliazhko.android.homework6;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Chek2 implements OperationDB {
    ThreadPoolExecutor executors = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    private RecyclerView recyclerView;
    private Context context;
    private RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private ContactTable contactTable;


    public Chek2(RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter, Context context, ListAdapter.OnContactClickListener onContactClickListener, RecyclerView recyclerView) {

        this.adapter = adapter;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
        this.recyclerView = recyclerView;
    }


    public Chek2(ContactTable contactTable) {
        this.contactTable = contactTable;
    }

    @Override
    public void initDB() {
        CompletableFuture future1 = new CompletableFuture();
        future1 = CompletableFuture.supplyAsync(new Supplier() {
            @Override
            public List<ContactTable> get() {

                Log.d("Dima", "Запущен поток 22........");
                Database db = App.getInstance().getDb();
                final MyDAO myDAO = db.getMyDAO();
                List<ContactTable> item = myDAO.getAll();

                //adapter = new ListAdapter(context, (List<ContactTable>) item, onContactClickListener);

                Log.d("Dima", "отправка" + item);
                return item;
            }
        }, executors).thenAcceptAsync(new Consumer<List<ContactTable>>() {
            @Override
            public void accept(List<ContactTable> item) {
                adapter = new ListAdapter(context, (List<ContactTable>) item, onContactClickListener);
                Log.d("Dima", "установка адаптера" + adapter);
                recyclerView.setAdapter(adapter);
            }
        });


    }

    @Override
    public void selectALL() {

    }

    @Override
    public void insert() {
        Log.d("Dima", "Реализация 2........");
        CompletableFuture future1 = new CompletableFuture();
        future1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                Database db = App.getInstance().getDb();
                final MyDAO myDAO = db.getMyDAO();
                myDAO.insert(contactTable);

            }
        });

    }

    @Override
    public void delete() {
        CompletableFuture future1 = new CompletableFuture();
        future1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                Database db = App.getInstance().getDb();
                final MyDAO myDAO = db.getMyDAO();
                myDAO.delete(contactTable);

            }
        });
    }

    @Override
    public void update() {

        CompletableFuture future1 = new CompletableFuture();
        future1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                Database db = App.getInstance().getDb();
                final MyDAO myDAO = db.getMyDAO();
                myDAO.update(contactTable);

            }
        });


    }
}
