package com.moodtrackerfinal.view.ui;
/****/
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.moodtrackerfinal.R;
import com.moodtrackerfinal.db.entity.MoodEntity;
import com.moodtrackerfinal.view.adapter.MoodAdapter;
import com.moodtrackerfinal.viewmodel.MoodListViewModel;

import java.util.List;
/****/
public class HistoryActivity extends MainActivity
{
    private MoodListViewModel mViewModel;
    private RecyclerView recyclerView;
    //
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final MoodAdapter adapter = new MoodAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        mViewModel = ViewModelProviders.of(this).get(MoodListViewModel.class);
        mViewModel.getAllMoods().observe(this, new Observer<List<MoodEntity>>()
        {
            @Override
            public void onChanged(@Nullable List<MoodEntity> moods)
            {
                if (moods != null)
                {
                    adapter.setMoods(moods);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
