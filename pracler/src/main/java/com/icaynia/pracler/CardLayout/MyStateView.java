package com.icaynia.pracler.CardLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icaynia.pracler.Global;
import com.icaynia.pracler.R;

/**
 * Created by icaynia on 16/03/2017.
 *
 * 메인 화면에서 사용하는 레이아웃 뷰.
 *
 * 데이터는 global에서 가져옵니다.
 */

public class MyStateView extends LinearLayout
{
    private View mainView;
    private LinearLayout contentBox;
    private Global global;

    private LayoutInflater inflater;

    private TextView playcount;
    private TextView mylikecount;

    public MyStateView(Context context)
    {
        super(context);
        viewInitialize();
    }

    public MyStateView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        viewInitialize();
    }

    private void viewInitialize()
    {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(R.layout.view_mystate, null);
        global = (Global) getContext().getApplicationContext();
        addView(mainView);
        playcount = (TextView) mainView.findViewById(R.id.text_playcount);
        mylikecount = (TextView) mainView.findViewById(R.id.text_mylikecount);
    }

    public void setPlayCount(int count)
    {
        playcount.setText(count+"");
    }

    public void setMylikecount(int likecount)
    {
        mylikecount.setText(likecount+"");
    }

}
