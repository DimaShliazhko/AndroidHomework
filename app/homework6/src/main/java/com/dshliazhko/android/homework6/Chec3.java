package com.dshliazhko.android.homework6;


import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Chec3 implements OperationDB {

    private Disposable disposable;

    private RecyclerView recyclerView;
    private Context context;
    private RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter;
    private ListAdapter.OnContactClickListener onContactClickListener;
    private ContactTable contactTable;


    public Chec3(RecyclerView.Adapter<ListAdapter.ListViewHolder> adapter, Context context, ListAdapter.OnContactClickListener onContactClickListener, RecyclerView recyclerView) {

        this.adapter = adapter;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
        this.recyclerView = recyclerView;
    }


    public Chec3(ContactTable contactTable) {
        this.contactTable = contactTable;
    }


    @Override
    public void initDB() {
        Database db = App.getInstance().getDb();
        final MyDAO myDAO = db.getMyDAO();


        Observable.create(new ObservableOnSubscribe<List<ContactTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ContactTable>> emitter) {

                List<ContactTable> item = myDAO.getAll();
                emitter.onNext(item);
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<ContactTable>>() {
                    @Override
                    public void accept(List<ContactTable> item) {

                        adapter = new ListAdapter(context, (List<ContactTable>) item, onContactClickListener);
                        Log.d("Dima", "установка адаптера в rx" + adapter);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }

    @Override
    public void selectALL() {

    }

    @Override
    public void insert() {
        Log.d("Dima", "Реализация 3........");
        Database db = App.getInstance().getDb();
        final MyDAO myDAO = db.getMyDAO();


        Observable.create(new ObservableOnSubscribe<List<ContactTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ContactTable>> emitter) {
                myDAO.insert(contactTable);
                Log.d("Dima", "инсерт" + contactTable);
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<ContactTable>>() {
                    @Override
                    public void accept(List<ContactTable> item) {
                    }
                });
    }

    @Override
    public void delete() {
        Database db = App.getInstance().getDb();
        final MyDAO myDAO = db.getMyDAO();


        Observable.create(new ObservableOnSubscribe<List<ContactTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ContactTable>> emitter) {
                myDAO.delete(contactTable);
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<ContactTable>>() {
                    @Override
                    public void accept(List<ContactTable> item) {
                    }
                });
    }

    @Override
    public void update() {
        Database db = App.getInstance().getDb();
        final MyDAO myDAO = db.getMyDAO();


        Observable.create(new ObservableOnSubscribe<List<ContactTable>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ContactTable>> emitter) {
                myDAO.update(contactTable);
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<List<ContactTable>>() {
                    @Override
                    public void accept(List<ContactTable> item) {
                    }
                });
    }
}
