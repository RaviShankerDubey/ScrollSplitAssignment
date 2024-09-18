package com.assignment.scrollsplitassignment.view.adaptor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.assignment.scrollsplitassignment.R;
import com.assignment.scrollsplitassignment.view.autoimageslider.SliderViewAdapter;
import com.assignment.scrollsplitassignment.viewmodel.model.ItemsModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class DashboardSliderAdapter extends SliderViewAdapter<DashboardSliderAdapter.SliderAdapterVH> {

    private Context context;
    private View.OnClickListener onClickListener;
    private List<ItemsModel> mSliderItems = new ArrayList<>();

    public DashboardSliderAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public void renewItems(List<ItemsModel> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(ItemsModel sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    public List<ItemsModel> getSliderList() {
        return mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image_slider_layout, null);

        int width = parent.getWidth();
        inflate.setMinimumWidth(width);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        ItemsModel itemsModel = mSliderItems.get(position);

        String imageUrl = itemsModel.getImage();
        viewHolder.textViewDescription.setText(itemsModel.getTitle());
        Glide.with(viewHolder.itemView).load(imageUrl).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                viewHolder.imageSliderBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                viewHolder.imageSliderBar.setVisibility(View.GONE);
                return false;
            }
        }).into(viewHolder.bannerSliderImg);

        viewHolder.bannerLayout.setTag(position);
        viewHolder.bannerLayout.setOnClickListener(onClickListener);
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private View itemView;
        private ProgressBar imageSliderBar;
        private ImageView bannerSliderImg;
        private ImageView imageGifContainer;
        private TextView textViewDescription;
        private FrameLayout bannerLayout;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageSliderBar = itemView.findViewById(R.id.imageSliderBar);
            bannerLayout = itemView.findViewById(R.id.bannerLayout);
            imageGifContainer = itemView.findViewById(R.id.bannerGifContainerImg);
            bannerSliderImg = itemView.findViewById(R.id.bannerSliderImg);
            textViewDescription = itemView.findViewById(R.id.bannerDescriptionTxt);
            this.itemView = itemView;
        }
    }

}
