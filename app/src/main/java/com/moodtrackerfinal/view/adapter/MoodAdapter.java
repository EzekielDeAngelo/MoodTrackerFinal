package com.moodtrackerfinal.view.adapter;
/** Adapter class **/
import android.app.Activity;
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

import java.util.List;
/** Creates views for items and replaces content on items updates **/
public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodHolder>
{
    // Holder class - Provide a reference to the views for each data item
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
    private List<? extends MoodEntity> mMoods;
    public MoodAdapter() {}
    // Override onCreateViewHolder method - Creates new views (invoked by the layout manager)
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
    // Override onBindViewHolder method - Replace the contents of a view
    @Override
    public void onBindViewHolder(MoodHolder holder, int position)
    {
        if (mMoods != null)
        {
            MoodEntity current = mMoods.get(position);
            setViewHolder(holder, current.getName());
            setViewHolderText(holder, position);
            String note = current.getNote();
            boolean noteExist = note.isEmpty();
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
    // Set view holder method - Modify views color and size according to the associated mood
    public void setViewHolder(MoodHolder holder, int name)
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
    // Set view holder text method - Adds text content to views
    public void setViewHolderText(MoodAdapter.MoodHolder holder, int position)
    {
        switch (position)
        {
            case 0:
                holder.moodColor.setText("One day ago");
                break;
            case 1:
                holder.moodColor.setText("Two days ago");
                break;
            case 2:
                holder.moodColor.setText("Three days ago");
                break;
            case 3:
                holder.moodColor.setText("Four days ago");
                break;
            case 4:
                holder.moodColor.setText("Five days ago");
                break;
            case 5:
                holder.moodColor.setText("Six days ago");
                break;
            case 6:
                holder.moodColor.setText("One week ago");
                break;
        }
    }
    // Set moods method - Update views with new moods
    public void setMoods(List<MoodEntity> moods)
    {
        mMoods = moods;
        notifyDataSetChanged();
    }
    // Override getItemCount method - Avoids to return null when mMoods has not been updated
    @Override
    public int getItemCount()
    {
        if (mMoods != null)
        {
            return mMoods.size();
        }
        else
        {
            return 0;
        }
    }
}