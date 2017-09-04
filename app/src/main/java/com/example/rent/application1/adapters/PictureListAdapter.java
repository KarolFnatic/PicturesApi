package com.example.rent.application1.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.application1.R;
import com.example.rent.application1.models.Picture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.AllArgsConstructor;

import static com.example.rent.application1.models.Picture.getThumbnailUrl;

@AllArgsConstructor
public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.PictureViewHolder> {

    private List<Picture> pictureList;
    private FragmentManager fragmentManager;
    private View.OnClickListener onPictureItemClickListener;

    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, null);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {
        final Picture picture = pictureList.get(position);
        holder.author.setText(picture.getAuthor());
        Picasso.with(holder.icon.getContext())
                .load(getThumbnailUrl(picture))
                .into(holder.icon);
        holder.itemView.setOnClickListener(onPictureItemClickListener);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.picture_item_imageView)
        ImageView icon;

        @BindView(R.id.picture_item_author)
        TextView author;

        public PictureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

}