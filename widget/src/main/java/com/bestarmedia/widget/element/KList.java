package com.bestarmedia.widget.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bestarmedia.widget.data.KListItem;
import com.youth.banner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

public class KList extends RecyclerView {

    public KList(@NonNull Context context) {
        super(context);
    }

    public KList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KList(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setItemList(String[] items, int orientation) {
        List<KListItem> kListItems = new ArrayList<>();
        for (String text : items) {
            kListItems.add(new KListItem(text, text));
        }
        setLayoutManager(new LinearLayoutManager(getContext(), orientation, false));
        setAdapter(new ImageAdapter(kListItems));
    }


    public class ImageAdapter extends BannerAdapter<KListItem, ImageAdapter.BannerViewHolder> {

        public ImageAdapter(List<KListItem> data) {
            super(data);
        }

        //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
        @Override
        public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER_VERTICAL);
            TextView tvTile = new TextView(getContext());
            tvTile.setGravity(Gravity.LEFT);
            LinearLayout.LayoutParams paramsTitle = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            layout.addView(tvTile, paramsTitle);
            return new BannerViewHolder(layout, tvTile);
        }

        @Override
        public void onBindView(BannerViewHolder holder, KListItem data, int position, int size) {
            holder.tvTitle.setText(data.title);
        }

        class BannerViewHolder extends RecyclerView.ViewHolder {

            LinearLayout item;
            TextView tvTitle;

            public BannerViewHolder(@NonNull LinearLayout item, TextView tvTitle) {
                super(item);
                this.item = item;
                this.tvTitle = tvTitle;
            }
        }
    }
}
