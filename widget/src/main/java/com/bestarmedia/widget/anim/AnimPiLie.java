package com.bestarmedia.widget.anim;

import android.graphics.Canvas;
import android.graphics.Region;

import com.bestarmedia.widget.container.EnterAnimLayout;

/**
 * 劈裂效果
 */
public class AnimPiLie extends Anim {
    public AnimPiLie(EnterAnimLayout view) {
        super(view);
    }

    @Override
    public void handleCanvas(Canvas canvas, float rate) {
        float rectLeft = w / 2 * rate;
        float rectRight = w - w / 2 * rate;
        canvas.clipRect(rectLeft, 0, rectRight, h, Region.Op.XOR);

        canvas.save();
    }
}
