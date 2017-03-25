package com.icaynia.soundki.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.icaynia.soundki.Activity.ProfileActivity;
import com.icaynia.soundki.Data.RemoteDatabaseManager;
import com.icaynia.soundki.Data.UserManager;
import com.icaynia.soundki.Global;
import com.icaynia.soundki.Model.User;
import com.icaynia.soundki.R;
import com.icaynia.soundki.View.ProfileMenuAdapter;
import com.icaynia.soundki.View.ProfileMenuHeader;
import com.icaynia.soundki.View.ProfileRow;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by icaynia on 2017. 2. 8..
 */

public class ProfileMenuFragment extends Fragment
{
    private Global global;
    private View v;
    private ListView listView;

    private Handler handler = new Handler();

    private String userPhotoUrl;

    private FirebaseUser firebaseUser;

    private ArrayList<String> list;

    private int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        global = (Global) getContext().getApplicationContext();

        firebaseUser = global.loginUser;
        Log.e("looog", firebaseUser.getDisplayName());
        Log.e("looog", firebaseUser.getPhotoUrl().toString());
        Log.e("looog", firebaseUser.getEmail());
        Log.e("looog", firebaseUser.getProviders().toString());

        listView = (ListView) v.findViewById(R.id.listview);
        prepare();
        return v;
    }

    private void onProfileActivity(String uid)
    {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra("targetUid", uid);
        startActivity(intent);
    }

    private void prepare()
    {
        global.userManager.getFollowingList(firebaseUser.getUid(), new UserManager.OnCompleteGetUserFollowingListener()
        {
            @Override
            public void onComplete(final ArrayList<String> followingList)
            {
                list = followingList;
                final ArrayList<User> userlist = new ArrayList<User>();
                for (i = 0; i < followingList.size(); i++)
                {
                    global.userManager.getUser(followingList.get(i), new UserManager.OnCompleteGetUserListener()
                    {
                        @Override
                        public void onComplete(User user)
                        {
                            userlist.add(user);
                            if (userlist.size() == followingList.size())
                            {
                                ProfileMenuAdapter profileMenuAdapter = new ProfileMenuAdapter(getContext(), userlist);
                                listView.setAdapter(profileMenuAdapter);
                            }
                        }
                    });
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                onProfileActivity(list.get(i));
            }
        });
    }
}
