package com.bestarmedia.widget.anim;

import android.graphics.Canvas;

import com.bestarmedia.widget.container.EnterAnimLayout;


/**
 * 擦除效果
 */
public class AnimCaChu extends Anim {
    public AnimCaChu(EnterAnimLayout view) {
        super(view);
    }

    @Override
    public void handleCanvas(Canvas canvas, float rate) {
        float rectTop = (h - h * rate);
        //剪切当前需要展示区域的左上右下
        canvas.clipRect(0, rectTop, w, h);

        canvas.save();
    }
}
