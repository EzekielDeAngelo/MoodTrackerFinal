package com.moodtrackerfinal.db;

import com.moodtrackerfinal.db.entity.MoodEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates data to pre-populate the database
 */
public class DataGenerator
{

    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine"};
    private static final String[] COMMENTS = new String[]{
            "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6"};

    public static List<MoodEntity> generateProducts()
    {
        List<MoodEntity> products = new ArrayList<>(FIRST.length * SECOND.length);
        for (int i = 0; i < FIRST.length; i++) {
            for (int j = 0; j < SECOND.length; j++) {
                MoodEntity mood = new MoodEntity();
                mood.setName(FIRST[i] + " " + SECOND[j]);
                mood.setNote(mood.getName() + " " + DESCRIPTION[j]);
                mood.setId(FIRST.length * i + j + 1);
                products.add(mood);
            }
        }
        return products;
    }
}
