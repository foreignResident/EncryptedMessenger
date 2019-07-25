package com.bvblogic.examplearbvb.db.datamanager;

import android.annotation.SuppressLint;
import android.util.Log;

import com.bvblogic.examplearbvb.db.core.AppDatabase;
import com.bvblogic.examplearbvb.db.datamanager.core.DBView;
import com.bvblogic.examplearbvb.db.datamanager.core.DataManager;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.domain.Message;
import com.bvblogic.examplearbvb.db.domain.SendAction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ChatDataManager extends DataManager {

    public void getAllChats(AppDatabase appDatabase, DBView<List<Chat>> listDBView) {
        listDBView.showWait();
        appDatabase.chatDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Chat>>() {
                    @Override
                    public void onSuccess(List<Chat> chats) {
                        listDBView.onSuccess(chats);
                        listDBView.hideWait();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listDBView.onError(e);
                        listDBView.hideWait();
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void getByTypeAndRecipient(AppDatabase appDatabase, String recipient, SendAction action, String text){
        appDatabase.chatDao().getByTypeAndRecipient(recipient, action.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Chat>() {
                    @Override
                    public void onSuccess(Chat chat) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                        Message temp = new Message();
                        temp.setText(text);
                        temp.setTime(formatter.format(new Date()));
                        temp.setChatId(chat.getId());
                        temp.setType("received");
                        temp.setUserName(recipient);
                        appDatabase.messageDao().insert(temp);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void addChat(AppDatabase appDatabase, Chat chat) {
        appDatabase.chatDao().add(chat);
    }

    public void updateChat(AppDatabase appDatabase, Chat chat) {
        appDatabase.chatDao().update(chat);
    }

    public void getById(int id, AppDatabase database, DBView<Chat> listDBView) {
        listDBView.showWait();
         database.chatDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Chat>() {
                    @Override
                    public void onSuccess(Chat data) {
                        listDBView.onSuccess(data);
                        listDBView.hideWait();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listDBView.onError(e);
                        listDBView.hideWait();
                    }
                });
    }

    public Completable updatePassword(int id, String password, AppDatabase database) {
        return Completable.fromAction(
                () -> database.chatDao().update(password, id)
        );
    }
}
