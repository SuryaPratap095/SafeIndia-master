package com.example.surya.safeindia.Tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.surya.safeindia.R;

/**
 * Created by surya on 27/7/16.
 */
public class FriendsTab extends android.support.v4.app.Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.friends_tab_layout,container,false);
    }
}
