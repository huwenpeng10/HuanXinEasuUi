package com.example.merxu.huanxineasuui.fragment;

import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseBaseFragment;

import java.util.Map;

/**
 * Created by MerXu on 2017/9/29.
 */

public class ContactListFragment extends EaseBaseFragment {

    private Map<String, EaseUser> contactsMap;

    @Override
    protected void initView() {

    }

    @Override
    protected void setUpView() {

    }

    public void setContactsMap(Map<String, EaseUser> contactsMap) {
        this.contactsMap = contactsMap;
    }
}
