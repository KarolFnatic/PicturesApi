package com.example.rent.application1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.application1.R;
import com.example.rent.application1.models.Picture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RENT on 2017-08-18.
 */

public class PictureListAdapter extends ArrayAdapter<Picture> {

    private final List<Picture> pictureList;
    private final LayoutInflater inflater;
//    private OnItemClicked onItemClicked;


    public PictureListAdapter(Context context, List<Picture> pictureList) {
        super(context, R.layout.picture_item);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pictureList = pictureList;
//        this.onItemClicked = onItemClicked;
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }

    @Override
    public boolean isEmpty() {
        return pictureList.isEmpty();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Picture picture = pictureList.get(position);

        View rowView = convertView;
        if(rowView == null)
            rowView = inflater.inflate(R.layout.picture_item, parent, false);

        ViewHolder holder = (ViewHolder) rowView.getTag();
        if(holder == null) {
            new ViewHolder(rowView);
            rowView.setTag(holder);
        }
        holder.authorText.setText(picture.getAuthor());
        Picasso.with(getContext())
                .load(picture.getPhotoUrl())
                .centerCrop()
                .into(holder.pictureImage);


//        setOnRowClickedListener(picture, rowView);

        return rowView;
    }

//    private void setOnRowClickedListener(final Picture picture, View rowView) {
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onItemClicked != null) {
//                    onItemClicked.onRowClicked(picture);
//                }
//            }
//        });
//    }

    public interface OnItemClicked{
        void onRowClicked(Picture picture);
    }



    class ViewHolder{

        @BindView(R.id.picture_item_author)
        TextView authorText;

        @BindView(R.id.picture_item_imageView)
        ImageView pictureImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    }