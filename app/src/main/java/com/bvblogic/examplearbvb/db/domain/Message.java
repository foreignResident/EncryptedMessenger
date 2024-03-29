package com.bvblogic.examplearbvb.db.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.bvblogic.examplearbvb.db.datamanager.ChatDataManager;
import com.bvblogic.examplearbvb.db.datamanager.MessageDataManager;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "message")
public class Message {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "chat_id")
    private int chatId;

    @ColumnInfo(name = "user")
    private String userName;

    public Message() {

    }

    public Message(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
