package com.bvblogic.examplearbvb.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.domain.SendAction;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class ChatDao implements BaseDao<Chat> {

    // TODO: CHAT BY SENDER


    @Query("SELECT * FROM chat")
    public abstract Single<List<Chat>> getAll();

    @Query("SELECT * FROM chat WHERE id=:id")
    public abstract Single<Chat> getById(int id);

    @Query("SELECT * FROM chat WHERE id=:chatId") // TODO: WITH ROOMS!
    public abstract Chat getByMessage(int chatId);

    @Query("SELECT * FROM chat WHERE user_name=:recipient AND type=:type")
    public abstract Single<Chat> getByTypeAndRecipient(String recipient, String type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<Chat> chats);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    public abstract void add(Chat chat);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public abstract void update(Chat chat);

    @Query("UPDATE chat SET file_password = :password WHERE id = :id")
    public abstract void update(String password, int id);
}
