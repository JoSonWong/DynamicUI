package com.bestarmedia.widget.anim;

import android.graphics.Canvas;
import android.graphics.Region;

import com.bestarmedia.widget.container.EnterAnimLayout;

/**
 * 盒状效果
 */
public class AnimHeZhuang extends Anim {
    public AnimHeZhuang(EnterAnimLayout view) {
        super(view);
    }

    @Override
    public void handleCanvas(Canvas canvas, float rate) {
        float rectLeft = (w / 2 * rate);
        float rectRight = w - rectLeft;
        float rectTop = (h / 2 * rate);
        float rectBottom = h - rectTop;

        //剪切需要展示的区域
        canvas.clipRect(rectLeft, rectTop, rectRight, rectBottom, Region.Op.DIFFERENCE);

        canvas.save();
    }
}
