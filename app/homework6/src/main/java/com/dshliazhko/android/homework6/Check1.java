package com.dshliazhko.android.homework6;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessController;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Check1 implements OperationDB {

  private   Handler handler;
  private Context context;
    private RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private ContactTable contactTable;


    public Check1(Handler handler,RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter,Context context,ListAdapter.OnContactClickListener onContactClickListener ) {
        this.handler = handler;
        this.adapter = adapter;
        this.context = context;
        this.onContactClickListener=  onContactClickListener;
    }
    public Check1(ContactTable contactTable){
        this.contactTable = contactTable;
    }
    ThreadPoolExecutor executors = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

//  Handler handler =  new Handler(Looper.getMainLooper());
    @Override
    public void initDB() {

        executors.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Dima", "Запущен поток 2........");
                Database db = App.getInstance().getDb();
                final MyDAO myDAO = db.getMyDAO();
               List<ContactTable> item = myDAO.getAll();

               adapter = new ListAdapter(context, (List<ContactTable>) item, onContactClickListener);
              Message msg =  handler.obtainMessage(0, item);
                handler.sendMessage(msg);
                Message msg2 =  handler.obtainMessage(1, adapter);
                handler.sendMessage(msg2);
                Log.d("Dima", "отправка"+item);

            }

        });


    }

    @Override
    public void selectALL() {
        executors.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void insert() {
        Log.d("Dima", "Реализация 1........");
        executors.execute(new Runnable() {
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
        executors.execute(new Runnable() {
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
        executors.execute(new Runnable() {
            @Override
            public void run() {
                Database db = App.getInstance().getDb();
                final MyDAO myDAO = db.getMyDAO();
                myDAO.update(contactTable);
            }
        });
    }
}
