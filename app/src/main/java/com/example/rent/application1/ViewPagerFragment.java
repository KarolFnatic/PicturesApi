package com.example.rent.application1;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.application1.adapters.PicturePagerAdapter;

import butterknife.BindView;

public class ViewPagerFragment extends DialogFragment {
    // Store instance variables
    private String title;
    private int page;
    private PicturePagerAdapter adapterViewPager;

    @BindView(R.id.vpPager)
    ViewPager vpPager;

    // newInstance constructor for creating fragment with arguments
    public static ViewPagerFragment newInstance(int page, String title) {
        ViewPagerFragment fragmentFirst = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        adapterViewPager = new PicturePagerAdapter(getSupportFragmentManager(), picturesList);
        vpPager.setAdapter(adapterViewPager);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        return view;
    }
}