package com.bvblogic.examplearbvb.mvp.manager;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.bvblogic.examplearbvb.R;
import com.bvblogic.examplearbvb.fragment.AuthFragment_;
import com.bvblogic.examplearbvb.fragment.ChatAdditionFragment_;
import com.bvblogic.examplearbvb.fragment.ChatsFragment_;
import com.bvblogic.examplearbvb.fragment.HistoryMessageFragment_;
import com.bvblogic.examplearbvb.fragment.NewMessageFragment_;
import com.bvblogic.examplearbvb.fragment.RegistrationFragment;
import com.bvblogic.examplearbvb.fragment.RegistrationFragment_;
import com.bvblogic.examplearbvb.mvp.core.FragmentData;
import com.bvblogic.examplearbvb.mvp.manager.core.BaseMainActivityManagerUI;


/**
 * Created by hanz on 7.05.18.
 */
public class MainActivityManagerUI extends BaseMainActivityManagerUI {

    public MainActivityManagerUI(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected int getIdFragmentsContainer() {
        return R.id.fragment_container;
    }

    @Override
    protected void initUI() {
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void changeFragmentTo(FragmentData fragment) {
        switch (fragment.getFragmentById()) {
            case CHAT_ADDITION: {
                addFragmentToContainer(ChatAdditionFragment_.builder().build(), true,
                        this.getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(0, android.R.anim.fade_in));
                break;
            }
            case NEW_MESSAGE_FRAGMENT: {
                // check if fragment has data to insert and it's length is 1 (One name)
                if(fragment.getObject() != null && fragment.getObject().length == 1){
                    // set args for fragment after before building
                    addFragmentToContainer(NewMessageFragment_.builder()
                                    .chatId((Integer)fragment.getObject()[0]).build(), true,
                            this.getActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .setCustomAnimations(0, android.R.anim.fade_in));
                }
                break;
            }
            case AUTH_FRAGMENT:{
                addFragmentToContainer(AuthFragment_.builder().build(), false,
                        this.getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(0, android.R.anim.fade_in));
                break;
            }

            case HISTORY_MESSAGE_FRAGMENT: {
                if(fragment.getObject() != null && fragment.getObject().length == 1){
                    addFragmentToContainer(HistoryMessageFragment_.builder()
                                    .chatId((Integer)fragment.getObject()[0]).build(), true,
                            this.getActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .setCustomAnimations(0, android.R.anim.fade_in));
                }
                break;
            }

            case CHATS_FRAGMENT: {
                addFragmentToContainer(ChatsFragment_.builder().build(), false,
                        this.getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(0,  android.R.anim.fade_in));
                break;
            }

            case REGISTRATION_FRAGMENT: {
                addFragmentToContainer(RegistrationFragment_.builder().build(), false,
                        this.getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(0,  android.R.anim.fade_in));
                break;
            }
        }
    }

    private void removeAnimFragment(Fragment fragment, int anim) {
        this.getActivity().getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(anim, anim).
                remove(fragment).
                commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private String[] fragments = {
            //SplashFragment_.class.getSimpleName()
    };

    @Override
    public boolean removeFragment() {
        for (String s : fragments) {
            Fragment fragment = this.getActivity().getSupportFragmentManager().findFragmentByTag(s);
            if (fragment != null) {
//                if (fragment.getClass().getSimpleName().equals(SplashFragment_.class.getSimpleName())) {
//                    BaseFragment.changeColorBar(getActivity(), BaseFragment.ColorBar.WHITE_DARK);
//                }
//                removeAnimFragment(fragment, R.anim.anim_exit);
                return true;
            }
        }
        return false;
    }
}
