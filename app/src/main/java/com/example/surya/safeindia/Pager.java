package com.example.surya.safeindia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by surya on 27/7/16.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm,int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
              //  ContactsTab contactsTab = new ContactsTab();
              //  return contactsTab;
            case 1:
                FriendsTab friendsTab = new FriendsTab();
                return friendsTab;
           case 2:
                MapTab mapTab=new MapTab();
              // Fragment mapFragment=(Fragment)mapTab;
                return mapTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
