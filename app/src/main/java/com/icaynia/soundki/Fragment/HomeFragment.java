package com.icaynia.soundki.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.icaynia.soundki.Data.PlayListManager;
import com.icaynia.soundki.R;
import com.icaynia.soundki.View.PlayListsAdapter;

import java.util.ArrayList;

/**
 * Created by icaynia on 2017. 2. 8..
 */

public class HomeFragment extends Fragment
{
    private View v;

    private ListView playListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        viewInitialize();

        prepare();

        return v;
    }

    private void viewInitialize()
    {
        playListView = (ListView) v.findViewById(R.id.playlistview);
    }

    private void prepare()
    {
        PlayListManager plm = new PlayListManager(getContext());
        ArrayList<String> arrayList = plm.getPlayListList();
        PlayListsAdapter playListsAdapter = new PlayListsAdapter(getContext(), arrayList);
        playListView.setAdapter(playListsAdapter);
    }


}
