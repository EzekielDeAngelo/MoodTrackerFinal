package com.moodtrackerfinal.view.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.moodtrackerfinal.R;
import com.moodtrackerfinal.view.adapter.MoodAdapter;
import com.moodtrackerfinal.viewmodel.MoodListViewModel;

public class HistoryActivity extends MainActivity
{
    private MoodListViewModel viewModel;
    private MoodAdapter adapter;
    private RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        adapter = new MoodAdapter(moodList);
        recyclerView = (RecyclerView) findViewById(R.id.mood_list);
        recyclerView.setAdapter(adapter);
    }
    public void setRowsText(MoodAdapter.MoodHolder holder, int position)
    {
        switch (position)
        {
            case 0:
                holder.moodColor.setText("One week ago");
                break;
            case 1:
                holder.moodColor.setText("Six days ago");
                break;
            case 2:
                holder.moodColor.setText("Five days ago");
                break;
            case 3:
                holder.moodColor.setText("Four days ago");
                break;
            case 4:
                holder.moodColor.setText("Three days ago");
                break;
            case 5:
                holder.moodColor.setText("Two days ago");
                break;
            case 6:
                holder.moodColor.setText("One day ago");
                break;
        }
    }
    private void initUI()
    {


    }
}
