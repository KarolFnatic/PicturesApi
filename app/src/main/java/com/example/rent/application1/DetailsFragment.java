package com.example.rent.application1;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rent.application1.models.Picture;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rent.application1.models.Picture.getPhotoUrl;


public class DetailsFragment extends DialogFragment {

    public static final String PICTURE_DATA = "PICTURE_DATA";
    public static final float PICTURE_SCALE = 0.5f;

    @BindView(R.id.fragment_details_author)
    TextView details_textView;

    @BindView(R.id.fragment_details_image)
    PhotoView details_image;

    public static DetailsFragment newInstance(Picture picture) {
        Bundle args = new Bundle();
        args.putParcelable(PICTURE_DATA, picture);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        Picture picture = getArguments().getParcelable(PICTURE_DATA);
        details_textView.setText(String.format("%s, %s, %dx%d", picture.getId(), picture.getAuthor(), picture.getHeight(), picture.getWidth()));
        Picasso.with(getContext())
                .load(getPhotoUrl(picture, PICTURE_SCALE))
                .into(details_image);
        return view;
    }

}
