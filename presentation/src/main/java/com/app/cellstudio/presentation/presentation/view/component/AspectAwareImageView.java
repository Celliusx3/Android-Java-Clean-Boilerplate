package com.app.cellstudio.presentation.presentation.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.app.cellstudio.presentation.R;

/**
 * Created by coyan on 04/12/2018.
 */

public class AspectAwareImageView extends android.support.v7.widget.AppCompatImageView {
    private float aspect = 1f;
    private boolean adjustHeight = true;

    public enum Adjust {
        WIDTH, HEIGHT
    }

    public AspectAwareImageView(Context context) {
        super(context);
        init(context, null);
    }

    public AspectAwareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AspectAwareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setAspect(float aspect) {
        this.aspect = aspect;
        requestLayout();
    }
    public void setAdjust(Adjust adjust){
        this.adjustHeight = Adjust.HEIGHT.equals(adjust);
        requestLayout();
    }

    private void init(Context context, AttributeSet attrs) {
        setAdjustViewBounds(false);
        setScaleType(ScaleType.FIT_XY);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AspectAwareImageView, 0, 0);
            float aspectWidth = ta.getFloat(R.styleable.AspectAwareImageView_aspect_width, 1f);
            float aspectHeight = ta.getFloat(R.styleable.AspectAwareImageView_aspect_height, 1f);
            aspect = aspectWidth / aspectHeight;
            adjustHeight = ta.getInteger(R.styleable.AspectAwareImageView_aspect_adjust, 0) == 0;
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width, height;

        if (adjustHeight) {
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = (int) (width / aspect);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (height * aspect);
        }
        setMeasuredDimension(width, height);
    }
}