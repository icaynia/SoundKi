package com.icaynia.pracler.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.icaynia.pracler.data.PlayListManager;
import com.icaynia.pracler.Global;
import com.icaynia.pracler.models.PlayList;
import com.icaynia.pracler.R;
import com.icaynia.pracler.adapters.PlayListAdapter;

public class PlayListActivity extends AppCompatActivity
{
    public Global global;
    public ListView mainListView;
    public PlayListAdapter adapter;
    private NestedScrollView scrollview;
    private PlayList playList;

    private String listname;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        */

        viewInitialize();
        global = (Global) this.getApplicationContext();

        Intent intent = getIntent();
        listname = intent.getStringExtra("list");
        onList();
    }

    public void viewInitialize()
    {
        mainListView = (ListView) findViewById(R.id.listview);
        scrollview = (NestedScrollView) findViewById(R.id.scrollview);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("PlayListFragment", i+"/"+l);
                global.playMusic(Integer.parseInt(playList.get(i)));
            }
        });
        mainListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                scrollview.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    public void onList()
    {
        // when 'listname' value is 0, it means show now playlist.
        if (listname.equals("0"))
        {
            this.getSupportActionBar().setTitle("현재 재생중 ー " + global.nowPlayingList.size() + "곡");
            this.playList = global.nowPlayingList;
            adapter = new PlayListAdapter(this, playList);
            mainListView.setAdapter(adapter);
        }
        else
        {
            PlayListManager plm = new PlayListManager(this);
            this.playList = plm.getPlayList(listname);
            this.getSupportActionBar().setTitle(playList.getName() +"" + global.nowPlayingList.size() + "곡");

            PlayListAdapter pla = new PlayListAdapter(this, playList);
            mainListView.setAdapter(pla);

            if (playList.size() == 0)
            {
                Toast.makeText(this, "플레이리스트가 비어있습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}


