package com.example.surya.safeindia;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by surya on 27/7/16.
 */
public class ContactsTab extends android.support.v4.app.Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.contacts_tab_layout,container,false);
    }

}
