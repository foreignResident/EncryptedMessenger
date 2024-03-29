package com.bvblogic.examplearbvb.fragment;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.bean.coding.CodingPresenter;

import com.bvblogic.examplearbvb.bean.io.KeysTask;
import com.bvblogic.examplearbvb.bean.io.core.Keys;
import com.bvblogic.examplearbvb.db.domain.Chat;
import com.bvblogic.examplearbvb.db.domain.Message;
import com.bvblogic.examplearbvb.db.presenter.UserChatPresenter;
import com.bvblogic.examplearbvb.fragment.core.BaseFragment;
import com.bvblogic.examplearbvb.mvp.core.FragmentById;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;


@EFragment(R.layout.fragment_new_message)
public class NewMessageFragment extends BaseFragment {

    // This arg is injected when this fragment is created. It will be set in TextView
    @FragmentArg("chatId")
    public int chatId;

    @Bean
    UserChatPresenter userPresenter;

    @ViewById(R.id.messageField)
    EditText messageField;

    @ViewById(R.id.enter_file_password)
    MaterialEditText enter_pass;

    @ViewById(R.id.user_name)
    TextView chatName;

    @ViewById(R.id.chat_type)
    TextView chatType;

    @Click(R.id.btnBack)
    public void back(){
        popBackStack();
    }

    @Click(R.id.btnJournal)
    public void goToJournal(){
        changeFragmentTo(new FragmentData(FragmentById.HISTORY_MESSAGE_FRAGMENT, chatId));
    }

    @Click(R.id.btnSend)
    public void send() {
        Log.d("FRAG", "hello");
//        codingPresenter.code(new Message(editText.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Message message = new Message();
        message.setChatId(chatId);
        message.setText(messageField.getText().toString());
        message.setTime(formatter.format(new Date()));
        message.setType("sent");
        userPresenter.sendMessage(message);

        popBackStack();

    }

    @AfterViews
    public void init(){
        userPresenter.setFragment(this);
        userPresenter.getChat(chatId);
        // request permissions
    }

    public String getChatType() {
        return chatType.getText().toString();
    }

    public void setUserName(String chatName) {
        this.chatName.setText(chatName);
    }

    public void setChatType(String sendAction) {
        this.chatType.setText(sendAction);
    }
}