package com.bestarmedia.widget.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

public class BnsImageLoader {

    @NonNull
    public static BnsImageLoader with(@NonNull Context context) {
        return new BnsImageLoader(Glide.with(context));
    }

    @NonNull
    public static BnsImageLoader with(@NonNull Activity activity) {
        return new BnsImageLoader(Glide.with(activity));
    }

    @NonNull
    public static BnsImageLoader with(@NonNull FragmentActivity activity) {
        return new BnsImageLoader(Glide.with(activity));
    }

    @NonNull
    public static BnsImageLoader with(@NonNull Fragment fragment) {
        return new BnsImageLoader(Glide.with(fragment));
    }


    @NonNull
    public static BnsImageLoader with(@NonNull View view) {
        return new BnsImageLoader(Glide.with(view));
    }

    private RequestManager requestManager;
    private BnsImageBuilder bnsImageBuilder;
    private boolean isGif;


    public BnsImageLoader(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public BnsImageLoader asGif() {
        this.isGif = true;
        return this;
    }

    public BnsImageBuilder load(Uri uri) {
        RequestBuilder builder;
        String path;
        if (this.isGif || ((path = uri.getPath()) != null && path.toLowerCase().contains(".gif"))) {
            Log.d("BnsImageLoader", "图片格式为 gif >>>>>>>>>>>>>>");
            builder = requestManager.asGif().load(uri);
        } else {
            builder = requestManager.load(uri);
        }
        bnsImageBuilder = new BnsImageBuilder(builder);
        return bnsImageBuilder;
    }

    public BnsImageBuilder load(String url) {
        RequestBuilder builder;
        if (this.isGif || (url.toLowerCase().contains(".gif"))) {
            Log.d("BnsImageLoader", "图片格式为 gif >>>>>>>>>>>>>>");
            builder = requestManager.asGif().load(url);
        } else {
            builder = requestManager.load(url);
        }
        bnsImageBuilder = new BnsImageBuilder(builder);
        return bnsImageBuilder;
    }


    public BnsImageBuilder load(int resId) {
        RequestBuilder builder;
        if (this.isGif) {
            builder = requestManager.asGif().load(resId);
            Log.d("BnsImageLoader", "图片格式为 gif >>>>>>>>>>>>>>");
        } else {
            builder = requestManager.load(resId);
        }
        bnsImageBuilder = new BnsImageBuilder(builder);
        return bnsImageBuilder;
    }

    public class BnsImageBuilder {

        private RequestBuilder builder;

        private int placeholderResourceId;
        private int errorResourceId;
        private int corners;
        private boolean skipMemoryCache;
        private int width;
        private int height;

        private BnsImageBuilder(RequestBuilder builder) {
            this.builder = builder;
        }

        public BnsImageBuilder placeholder(int placeholderResourceId) {
            this.placeholderResourceId = placeholderResourceId;
            return this;
        }

        public BnsImageBuilder error(int errorResourceId) {
            this.errorResourceId = errorResourceId;
            return this;
        }

        public BnsImageBuilder corners(int corners) {
            this.corners = corners;
            return this;
        }

        public BnsImageBuilder width(int width) {
            this.width = width;
            return this;
        }

        public BnsImageBuilder height(int height) {
            this.height = height;
            return this;
        }

        public BnsImageBuilder skipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        private RequestBuilder toBuild() {
            if (placeholderResourceId > 0) {
                builder.placeholder(placeholderResourceId);
            }
            if (errorResourceId > 0) {
                builder.error(errorResourceId);
            }
            if (corners > 0) {
                builder.apply(RequestOptions.bitmapTransform(new RoundedCorners(corners)));
            }
            if (width > 0 || height > 0) {
                builder.override(width, height);
                builder.centerCrop();
            } else {
                builder.override(Target.SIZE_ORIGINAL);
            }
            builder.skipMemoryCache(skipMemoryCache);
            builder.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            return builder;
        }

        public void into(ImageView imageView) {
            toBuild().into(imageView);
        }


        public void into(final IImageLoadDrawableListener loadListener) {
            toBuild();
            builder.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                    if (loadListener != null) {
                        loadListener.onLoadImageDrawable(drawable);
                    }
                }
            });
        }
    }


    public interface IImageLoadListener {
        void onLoadImage(Bitmap bitmap);
    }

    public interface IImageLoadDrawableListener {
        void onLoadImageDrawable(Drawable drawable);
    }
}
