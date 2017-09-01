package com.example.rent.application1;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.application1.adapters.PicturePagerAdapter;
import com.example.rent.application1.models.Picture;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends DialogFragment {
    public static final String IMAGES_KEY = "images_key";

    @BindView(R.id.vpPager)
    ViewPager viewPager;

    // newInstance constructor for creating fragment with arguments
    public static ViewPagerFragment newInstance(ArrayList<Picture> imagesList) {
        ViewPagerFragment fragmentFirst = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGES_KEY, imagesList);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<Picture> imagesList = getArguments().getParcelableArrayList(IMAGES_KEY);
        PicturePagerAdapter adapterViewPager = new PicturePagerAdapter(getChildFragmentManager(), imagesList);
        viewPager.setAdapter(adapterViewPager);
    }
}