package com.bestarmedia.widget.container;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bestarmedia.widget.data.ElementAttr;
import com.bestarmedia.widget.element.KBanner;
import com.bestarmedia.widget.element.DynamicImageView;
import com.bestarmedia.widget.element.KList;

import java.util.ArrayList;
import java.util.List;

public class HLayout extends LinearLayout implements View.OnClickListener {

    private List<ElementAttr> elementData = new ArrayList<>();

    public HLayout(Context context) {
        super(context);
        init();
    }

    public HLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
    }

    public void createModules(List<ElementAttr> data) {
        this.elementData = data;
        for (ElementAttr element : this.elementData) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(element.width, element.height);
            if (element.margins != null && element.margins.length == 4) {
                layoutParams.setMargins(element.margins[0], element.margins[1], element.margins[2], element.margins[3]);
            }
            layoutParams.gravity = element.layoutGravity;
            if (element.viewType.startsWith("c_")) {//容器
                addView(createContainer(element), layoutParams);
            } else {//元素
                addView(createElement(element), layoutParams);
            }
        }
    }

    private View createElement(ElementAttr element) {
        View view;
        switch (element.viewType) {
            case "v_text":
                TextView text = new TextView(getContext());
                text.setId(View.generateViewId());
                if (!TextUtils.isEmpty(element.action)) {
                    text.setOnClickListener(this);
                    text.setTag(element.action);
                }
                if (!TextUtils.isEmpty(element.bgColor)) {
                    text.setBackgroundColor(Color.parseColor(element.bgColor));
                }
                if (!TextUtils.isEmpty(element.text)) {
                    text.setText(element.text);
                    if (!TextUtils.isEmpty(element.textColor)) {
                        text.setTextColor(Color.parseColor(element.textColor));
                    }
                    if (element.textSize > 0) {
                        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, element.textSize);
                    }
                }
                text.setGravity(element.gravity);
                if (element.paddings != null && element.paddings.length == 4) {
                    text.setPadding(element.paddings[0], element.paddings[1], element.paddings[2], element.paddings[3]);
                }
                view = text;
                break;
            case "v_banner":
                KBanner banner = new KBanner(getContext());
                banner.setId(View.generateViewId());
                if (!TextUtils.isEmpty(element.bgColor)) {
                    banner.setBackgroundColor(Color.parseColor(element.bgColor));
                }
                if (element.images != null) {
                    banner.setImageList(element.images);
                }
                if (element.paddings != null && element.paddings.length == 4) {
                    banner.setPadding(element.paddings[0], element.paddings[1], element.paddings[2], element.paddings[3]);
                }
                if (!TextUtils.isEmpty(element.action)) {
//                    banner.setOnBannerListener(new OnBannerListener() {
//                        @Override
//                        public void OnBannerClick(Object data, int position) {
//                            Toast.makeText(getContext(), "banner click:" + position, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onBannerChanged(int position) {
//                        }
//                    });
                    banner.setTag(element.action);
                }
                view = banner;
                break;
            case "v_VList":
            case "v_HList":
                KList kList = new KList(getContext());
                kList.setId(View.generateViewId());
                if (!TextUtils.isEmpty(element.bgColor)) {
                    kList.setBackgroundColor(Color.parseColor(element.bgColor));
                }
                if (element.images != null) {
                    kList.setItemList(element.images, "v_VList".equals(element.viewType) ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
                }
                if (element.paddings != null && element.paddings.length == 4) {
                    kList.setPadding(element.paddings[0], element.paddings[1], element.paddings[2], element.paddings[3]);
                }
                if (!TextUtils.isEmpty(element.action)) {
//                    banner.setOnBannerListener(new OnBannerListener() {
//                        @Override
//                        public void OnBannerClick(Object data, int position) {
//                            Toast.makeText(getContext(), "banner click:" + position, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onBannerChanged(int position) {
//                        }
//                    });
                    kList.setTag(element.action);
                }
                view = kList;
                break;
            default:
                DynamicImageView imageView = new DynamicImageView(getContext());
                imageView.setId(View.generateViewId());
                if (!TextUtils.isEmpty(element.action)) {
                    imageView.setOnClickListener(this);
                    imageView.setPressScale(true);
                    imageView.setTag(element.action);
                }
                if (!TextUtils.isEmpty(element.bgColor)) {
                    imageView.setBackgroundColor(Color.parseColor(element.bgColor));
                }
                imageView.loadUrl(element.url, element.corner, false);
                if (!TextUtils.isEmpty(element.text)) {
                    imageView.setText(element.text, element.textColor, element.textSize, element.gravity);
                }
                if (element.paddings != null && element.paddings.length == 4) {
                    imageView.setPadding(element.paddings[0], element.paddings[1], element.paddings[2], element.paddings[3]);
                }
                view = imageView;
                break;
        }
        return view;
    }

    private LinearLayout createContainer(ElementAttr element) {
        LinearLayout layout = new LinearLayout(getContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(element.width, element.height);
        layout.setOrientation(element.viewType.equals("c_HLayout") ? HORIZONTAL : VERTICAL);
        layout.setLayoutParams(layoutParams);
        if (!TextUtils.isEmpty(element.bgColor)) {
            layout.setBackgroundColor(Color.parseColor(element.bgColor));
        }
        for (ElementAttr e : element.elements) {
            if (e.viewType.startsWith("c_")) {
                layout.addView(createContainer(e), new LinearLayout.LayoutParams(e.width, e.height));
            } else {
                layout.addView(createElement(e), new LinearLayout.LayoutParams(e.width, e.height));
            }
        }
        return layout;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), v.getId() + " tag:" + v.getTag(), Toast.LENGTH_SHORT).show();
    }
}
