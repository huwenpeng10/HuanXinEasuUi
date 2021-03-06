package com.example.merxu.huanxineasuui.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.merxu.huanxineasuui.R;
import com.example.merxu.huanxineasuui.fragment.SettingsFragment;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends EaseBaseActivity {
    private TextView unreadLabel;
    private Button[] mTabs;
    private EaseConversationListFragment huihuaFrameng;//会话
    private EaseContactListFragment contactListFragment;//通讯录
    private SettingsFragment settingFragment;//设置
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_main);

        unreadLabel = (TextView) findViewById(R.id.unread_msg_number);
        mTabs = new Button[3];
        mTabs[0] = (Button) findViewById(R.id.btn_conversation);
        mTabs[1] = (Button) findViewById(R.id.btn_address_list);
        mTabs[2] = (Button) findViewById(R.id.btn_setting);
        // set first tab as selected
        mTabs[0].setSelected(true);

        huihuaFrameng = new EaseConversationListFragment();
        contactListFragment = new EaseContactListFragment();
        settingFragment = new SettingsFragment();
        contactListFragment.setContactsMap(getContacts());
        huihuaFrameng.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
            }
        });
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
            }
        });
        fragments = new Fragment[] { huihuaFrameng, contactListFragment, settingFragment };
        // add and show first fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, huihuaFrameng)
                .add(R.id.fragment_container, contactListFragment).hide(contactListFragment).show(huihuaFrameng)
                .commit();
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        // configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        //设置阴影
        menu.setOffsetFadeDegree(0.4f);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.color.colorAccent);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.slidingmenu_list);
    }

    /**
     * onTabClicked
     *
     * @param view
     */
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_address_list:
                index = 1;
                break;
            case R.id.btn_setting:
                index = 2;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // set current tab as selected.
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    /**
     * prepared users, password is "123456"
     * you can use these user to test
     * @return
     */
    private Map<String, EaseUser> getContacts(){
        Map<String, EaseUser> contacts = new HashMap<String, EaseUser>();
//        for(int i = 1; i <= 10; i++){
//            EaseUser user = new EaseUser("easeuitest" + i);
//            contacts.put("easeuitest" + i, user);
//        }
        EaseUser user ;
        user = new EaseUser("jion");
        contacts.put("user"+0, user);
        user = new EaseUser("jack");
        contacts.put("user"+1, user);
        user = new EaseUser("qwer");
        contacts.put("user"+2, user);
        return contacts;
    }
}
