package com.bestarmedia.widget.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bestarmedia.widget.data.BannerItem;
import com.bestarmedia.widget.util.BnsImageLoader;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import java.util.Arrays;
import java.util.List;


public class KBanner extends com.youth.banner.Banner {

    public KBanner(@NonNull Context context) {
        super(context);
    }

    public KBanner(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
    }

    public KBanner(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageList(String[] images){
        setIndicator(new CircleIndicator(getContext()));
        setAdapter(new ImageAdapter(BannerItem.getTestData()));
        isAutoLoop(true);
        setDelayTime(5000);
        setUserInputEnabled(true);
        setIndicatorGravity(IndicatorConfig.Direction.RIGHT);
        start();
    }


    public  class ImageAdapter extends BannerAdapter<BannerItem, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<BannerItem> data) {
            //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
            super(data);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(parent.getContext());
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return new BannerViewHolder(imageView);
        }

        @Override
        public void onBindView(BannerViewHolder holder, BannerItem data, int position, int size) {
            BnsImageLoader.with(getContext()).load(data.imageUrl).into(holder.imageView);
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public BannerViewHolder(@NonNull ImageView view) {
                super(view);
                this.imageView = view;
            }
        }
    }
}
