package com.icaynia.soundki.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.icaynia.soundki.R;

/**
 * Created by icaynia on 16/03/2017.
 */

public class PlayListsFragment extends Fragment
{
    private View v;
    private ListView playlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        viewInitialize();
        return v;
    }

    public void viewInitialize()
    {

    }



}
