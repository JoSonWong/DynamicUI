package com.bestarmedia.widget.element;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestarmedia.widget.anim.EnterAnimUtil;
import com.bestarmedia.widget.container.EnterAnimLayout;
import com.bestarmedia.widget.util.BnsImageLoader;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import java.net.URL;

public class DynamicImageView extends EnterAnimLayout {

    private TimeInterpolator interpolator = new DecelerateInterpolator();
    private boolean isPressScale = false;
    private ImageView imageView;
    private SVGAImageView svgaImageView;
    private TextView textView;

    private final static String TAG = "DynamicImageView";

    public DynamicImageView(Context context) {
        super(context);
        initView(context, null);
    }

    public DynamicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public DynamicImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        if (imageView == null) {
            imageView = new ImageView(context, attrs);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setId(View.generateViewId());
        }
        if (svgaImageView == null) {
            svgaImageView = new SVGAImageView(context, attrs);
            svgaImageView.setId(View.generateViewId());
        }

        FrameLayout.LayoutParams paramsImageView = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        addView(imageView, paramsImageView);

        FrameLayout.LayoutParams paramsSvga = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        addView(svgaImageView, paramsSvga);
    }


    public void setText(String text, String textColor, int textSize, int layoutGravity) {
        Log.d(getClass().getSimpleName(), "layoutGravity :" + layoutGravity);
        if (textView == null) {
            textView = new TextView(getContext());
            textView.setId(View.generateViewId());
        }
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
        if (!TextUtils.isEmpty(textColor)) {
            textView.setTextColor(Color.parseColor(textColor));
        }
        if (textSize > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = layoutGravity;
        addView(textView, params);

    }

    public void setPressScale(boolean isPressScale) {
        this.isPressScale = isPressScale;
    }

    public void loadUrl(String url, int corner, boolean showAnimation) {
        loadUrl(url, 0, 0, corner, showAnimation);
    }

    public void loadUrl(String url, int placeholder, int errorResId, int corner, boolean showAnimation) {
        if (!TextUtils.isEmpty(url)) {
            if (url.toLowerCase().endsWith(".svga")) {
                imageView.setVisibility(GONE);
                svgaImageView.setVisibility(VISIBLE);
                playSvga(url);
            } else {
                imageView.setVisibility(VISIBLE);
                svgaImageView.setVisibility(GONE);
                BnsImageLoader.with(getContext()).load(url).placeholder(placeholder).error(errorResId).corners(corner).into(imageView);
                if (showAnimation) {
                    EnterAnimUtil.getRandomEnterAnim(this).startAnimation(500);
                }
            }
        }
    }

    public void loadUrl(int resId, int corner, boolean showAnimation) {
        imageView.setVisibility(VISIBLE);
        svgaImageView.setVisibility(GONE);
        BnsImageLoader.with(getContext()).load(resId).corners(corner).into(imageView);
        if (showAnimation) {
            EnterAnimUtil.getRandomEnterAnim(this).startAnimation(500);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (svgaImageView != null && svgaImageView.isAnimating()) {
            svgaImageView.stopAnimation(true);
        }
        super.onDetachedFromWindow();
    }

    private void playSvga(String url) {
        SVGAParser parser = new SVGAParser(getContext());
        try {
            parser.decodeFromURL(new URL(url), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(SVGAVideoEntity videoItem) {
                    svgaImageView.setVideoItem(videoItem);
                    svgaImageView.setClearsAfterStop(true);
                    svgaImageView.setLoops(10000);
                    svgaImageView.setCallback(new SVGACallback() {
                        @Override
                        public void onPause() {
                            svgaImageView.stopAnimation(true);
                        }

                        @Override
                        public void onFinished() {
                            svgaImageView.stopAnimation(true);
                        }

                        @Override
                        public void onRepeat() {
                        }

                        @Override
                        public void onStep(int i, double v) {
                        }
                    });
                    svgaImageView.stepToFrame(0, true);
                }

                @Override
                public void onError() {
                    Log.e(TAG, "播放SVGA decodeFromURL 出错了");
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "播放SVGA出错了", e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isPressScale) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    this.animate().scaleX(0.95f).scaleY(0.95f).setDuration(0).setInterpolator(interpolator);
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    this.animate().scaleX(1).scaleY(1).setDuration(0).setInterpolator(interpolator);
//                performClick();
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
