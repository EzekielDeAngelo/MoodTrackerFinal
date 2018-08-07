package com.moodtrackerfinal.view.adapter;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moodtrackerfinal.R;
import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.model.Mood;
import com.moodtrackerfinal.view.ui.HistoryActivity;

import java.util.List;
import java.util.Objects;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodHolder>
{
    private List<MoodEntity> moodList;
    private int height;
    private int width;
    private int screenWidth;
    List<? extends Mood> mMoodList;
    private HistoryActivity historyActivity;

    //@Nullable
    //private final MoodClickCallback mMoodClickCallback;
    /*
    public MoodAdapter(List<MoodEntity> moodList)
    {
        this.moodList = moodList;
    }
    */
    public MoodAdapter(List<MoodEntity> moodList)
    {
        this.moodList = moodList;
    }
    @Override
    public MoodHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_item, parent, false);
        historyActivity = new HistoryActivity();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)parent.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        width = screenWidth / 5;
        height = screenHeight / 7;
        return new MoodHolder(itemView);
    }
    //
    @Override
    public void onBindViewHolder(MoodHolder holder, int position)
    {
        MoodEntity currentMood = moodList.get(position);
        historyActivity.setRowsText(holder, position);
        String moodName = currentMood.getName();
        setRows(holder, moodName);
        // Logic to show or hide the button to show history notes and his onClick method
        boolean moodNote = currentMood.getNote().isEmpty();
        if (moodNote)
        {
            holder.showNote.setEnabled(false);
            holder.showNote.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.showNote.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Toast.makeText(view.getContext(), currentMood.getNote(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    //
    public void setRows(MoodHolder holder, String moodName)
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        switch (moodName)
        {
            case "sad":
                holder.moodColor.setBackgroundResource(R.color.sad);
                params.width = width;
                break;
            case "disappointed":
                holder.moodColor.setBackgroundResource(R.color.disappointed);
                params.width = width * 2;
                break;
            case "normal":
                holder.moodColor.setBackgroundResource(R.color.normal);
                params.width = width * 3;
                break;
            case "happy":
                holder.moodColor.setBackgroundResource(R.color.happy);
                params.width = width * 4;
                break;
            case "superhappy":
                holder.moodColor.setBackgroundResource(R.color.superhappy);
                params.width = width * 5;
                break;
        }
        params.height = height;
        holder.moodColor.setLayoutParams(params);
        holder.showNote.setPadding(0, 0, screenWidth - params.width, 0);
    }
    //
    public void setMoods(List<MoodEntity> moodList)
    {
        this.moodList = moodList;
        notifyDataSetChanged();
    }
    //
    @Override
    public int getItemCount()
    {
        return moodList.size();
    }
    //
    public class MoodHolder extends RecyclerView.ViewHolder
    {
        public TextView moodColor;
        public ImageButton showNote;
        //
        public MoodHolder(View itemView)
        {
            super(itemView);
            moodColor = (TextView) itemView.findViewById(R.id.mood_text);
            showNote = (ImageButton) itemView.findViewById(R.id.show_note);
        }
    }
}