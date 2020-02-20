package com.bestarmedia.widget.anim;

import android.graphics.Canvas;

import com.bestarmedia.widget.container.EnterAnimLayout;

/**
 * 切入效果
 */
public class AnimQieRu extends Anim {
    public AnimQieRu(EnterAnimLayout view) {
        super(view);
    }

    @Override
    public void handleCanvas(Canvas canvas, float rate) {

        canvas.translate(0,h-h*rate);

        canvas.save();
    }
}
