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

    @Override
    public int getCount() {
        return picturesList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(picturesList.get(position));
    }

}
