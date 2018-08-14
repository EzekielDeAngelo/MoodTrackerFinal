package com.moodtrackerfinal.view.adapter;
/****/
import android.app.Activity;
import android.content.Context;
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
import com.moodtrackerfinal.view.ui.HistoryActivity;

import java.util.List;
/****/
public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodHolder>
{
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
    private int height;
    private int width;
    private int screenWidth;
    private List<? extends MoodEntity> mMoods;  //Cached copy of moods
    //
    public MoodAdapter() {}
    //
    @Override
    public MoodHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_item, parent, false);
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

        if (mMoods != null)
        {
            MoodEntity current = mMoods.get(position);
            setMoodItemColor(holder, current.getName());
            setMoodItemText(holder, position);
            // Logic to show or hide the button to show history notes and his onClick method
            boolean noteExist = current.getNote().isEmpty();
            String note = current.getNote();
            if (noteExist)
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
                        Toast.makeText(view.getContext(), note, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        else
        {
            holder.moodColor.setText("No data");
        }
        if (position == 7)
        {
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)holder.itemView.getLayoutParams();
            holder.itemView.setVisibility(View.GONE);
            param.height = 0;
        }

    }
    //
    public void setMoodItemColor(MoodHolder holder, int name)
    {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        switch (name)
        {
            case 1:
                holder.moodColor.setBackgroundResource(R.color.sad);
                params.width = width;
                break;
            case 2:
                holder.moodColor.setBackgroundResource(R.color.disappointed);
                params.width = width * 2;
                break;
            case 3:
                holder.moodColor.setBackgroundResource(R.color.normal);
                params.width = width * 3;
                break;
            case 4:
                holder.moodColor.setBackgroundResource(R.color.happy);
                params.width = width * 4;
                break;
            case 5:
                holder.moodColor.setBackgroundResource(R.color.superhappy);
                params.width = width * 5;
                break;
        }
        params.height = height;
        holder.moodColor.setLayoutParams(params);
        holder.showNote.setPadding(0, 0, screenWidth - params.width, 0);
    }
    //
    public void setMoodItemText(MoodAdapter.MoodHolder holder, int position)
    {
        switch (position)
        {
            case 6:
                holder.moodColor.setText("One week ago");
                break;
            case 5:
                holder.moodColor.setText("Six days ago");
                break;
            case 4:
                holder.moodColor.setText("Five days ago");
                break;
            case 3:
                holder.moodColor.setText("Four days ago");
                break;
            case 2:
                holder.moodColor.setText("Three days ago");
                break;
            case 1:
                holder.moodColor.setText("Two days ago");
                break;
            case 0:
                holder.moodColor.setText("One day ago");
                break;
        }
    }
    //
    public void setMoods(List<MoodEntity> moods)
    {
        mMoods = moods;
        notifyDataSetChanged();
    }
    //
    @Override
    public int getItemCount()
    {
        if (mMoods != null)
        return mMoods.size();
        else return 0;
    }
}