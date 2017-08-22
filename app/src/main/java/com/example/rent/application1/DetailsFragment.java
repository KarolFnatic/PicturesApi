package com.example.rent.application1;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.application1.models.Picture;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rent.application1.models.Picture.getPhotoUrl;
import static com.example.rent.application1.models.Picture.getThumbnailUrl;

import butterknife.BindView;


public class DetailsFragment extends DialogFragment {

    @BindView(R.id.fragment_details_author)
    TextView author;

    @BindView(R.id.fragment_details_image)
    PhotoView image;


    public static DetailsFragment newInstance(Picture picture) {
        Bundle args = new Bundle();
        args.putParcelable("test", picture);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        Picture test = getArguments().getParcelable("test");
        author.setText(test.getId() + ", " + test.getAuthor() + ", " + test.getHeight() + "x" + test.getWidth());
        Picasso.with(getContext())
                .load(getPhotoUrl(test, 0.5f))
                .into(image);
        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        setFullScreenStyle();
    }

    private void setFullScreenStyle() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
