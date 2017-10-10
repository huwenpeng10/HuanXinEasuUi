package com.hyphenate.easeui.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MerXu on 2017/10/9.
 */

public class EaseAddUserActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //参数为要添加的好友的username和添加理由
        try {
            EMClient.getInstance().contactManager().addContact("jion", "I'm jack");
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

        initListener();
    }

    private void initListener() {
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {

            @Override
            public void onContactInvited(String username, String reason) {
                //收到好友邀请
                try {
                    EMClient.getInstance().contactManager().acceptInvitation(username);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFriendRequestAccepted(String s) {
                EaseContactListFragment easeContactListFragment = new EaseContactListFragment();
                easeContactListFragment.setContactsMap(getContacts(s));
            }

            @Override
            public void onFriendRequestDeclined(String s) {

            }

            @Override
            public void onContactDeleted(String username) {
                //被删除时回调此方法
            }


            @Override
            public void onContactAdded(String username) {
                //增加了联系人时回调此方法
            }
        });
    }

    private Map<String, EaseUser> getContacts(String s){
        Map<String, EaseUser> contacts = new HashMap<String, EaseUser>();
        EaseUser user = new EaseUser(s);
//        for(int i = 1; i <= 10; i++){
//            EaseUser user = new EaseUser("easeuitest" + i);
//            contacts.put("easeuitest" + i, user);
//        }
        contacts.put("adduser_name",user);
        return contacts;
    }
}
