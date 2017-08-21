package com.example.rent.application1.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rent.application1.DetailsFragment;
import com.example.rent.application1.R;
import com.example.rent.application1.models.Picture;
import com.squareup.picasso.Picasso;

import java.util.List;

import lombok.AllArgsConstructor;

import static com.example.rent.application1.models.Picture.getThumbnailUrl;

@AllArgsConstructor
public class PictureListAdapter extends RecyclerView.Adapter<PictureListAdapter.PictureViewHolder> {

    // TODO: 2017-08-21 OnClick in ViewHolder what will open DetailsFragment, also test out opening DetailsFragment

    private List<Picture> pictureList;
    private FragmentManager fragmentManager;

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
//                .centerCrop()
                .into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsFragment detailsFragment = DetailsFragment.newInstance();
                detailsFragment.show(fragmentManager,"");

            }
        });


    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView author;

        public PictureViewHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.picture_item_imageView);
            author = itemView.findViewById(R.id.picture_item_author);

        }


    }
}
//    private final LayoutInflater inflater;
////    private OnItemClicked onItemClicked;
//
//
//    public PictureListAdapter(Context context, List<Picture> pictureList) {
//        super(context, R.layout.picture_item);
//        inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.pictureList = pictureList;
////        this.onItemClicked = onItemClicked;
//    }
//
//    @Override
//    public int getCount() {
//        return pictureList.size();
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return pictureList.isEmpty();
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final Picture picture = pictureList.get(position);
//
//        View rowView = convertView;
//        if(rowView == null)
//            rowView = inflater.inflate(R.layout.picture_item, parent, false);
//
//        ViewHolder holder = (ViewHolder) rowView.getTag();
//        if(holder == null) {
//            new ViewHolder(rowView);
//            rowView.setTag(holder);
//        }
//        holder.authorText.setText(picture.getAuthor());
//        Picasso.with(getContext())
//                .load(picture.getThumbnailUrl())
//                .centerCrop()
//                .into(holder.pictureImage);
//
//
////        setOnRowClickedListener(picture, rowView);
//
//        return rowView;
//    }
//
////    private void setOnRowClickedListener(final Picture picture, View rowView) {
////        rowView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (onItemClicked != null) {
////                    onItemClicked.onRowClicked(picture);
////                }
////            }
////        });
////    }
//
//    public interface OnItemClicked{
//        void onRowClicked(Picture picture);
//    }
//
//
//
//    class ViewHolder{
//
//        @BindView(R.id.picture_item_author)
//        TextView authorText;
//
//        @BindView(R.id.picture_item_imageView)
//        ImageView pictureImage;
//
//        public ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//
//    }