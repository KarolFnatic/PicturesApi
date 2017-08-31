package com.example.rent.application1.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.rent.application1.DetailsFragment;
import com.example.rent.application1.models.Picture;

import java.util.List;


public class PicturePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Picture> picturesList;


    public PicturePagerAdapter(FragmentManager fragmentManager, List<Picture> picturesList) {
        super(fragmentManager);
        this.picturesList = picturesList;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return picturesList.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(picturesList.get(position));

    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
