package com.example.rent.application1;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.rent.application1.adapters.PicturePagerAdapter;
import com.example.rent.application1.models.Picture;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerFragment extends DialogFragment {
    private static final String IMAGES_KEY = "images_key";
    private static final String ITEM_POSITION_KEY = "itemposition_key";

    @BindView(R.id.vpPager)
    ViewPager viewPager;

    public static ViewPagerFragment newInstance(ArrayList<Picture> imagesList, int itemPosition) {
        ViewPagerFragment fragmentFirst = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGES_KEY, imagesList);
        args.putInt(ITEM_POSITION_KEY, itemPosition);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

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
        ArrayList<Picture> pictureArrayList = getArguments().getParcelableArrayList(IMAGES_KEY);
        int itemPosition = getArguments().getInt(ITEM_POSITION_KEY);
        PicturePagerAdapter adapterViewPager = new PicturePagerAdapter(getChildFragmentManager(), pictureArrayList);
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(itemPosition);
        setFullScreenStyle();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    private void setFullScreenStyle() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        }
    }
}