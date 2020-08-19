package com.dshliazhko.android.homeworkfour;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class CustomView extends View {

    interface TouchAction {
        void onTouchDown(int x, int y);
    }

    private int width = 0;
    private int height = 0;
    private int radius = getContext().getResources().getDimensionPixelSize(R.dimen.radius);
    private TouchAction touchAction;
    private int side;
    private Paint paintSector1;
    private Paint paintnew;
    private Canvas canvas;

    private int getX ;
    private int getY ;
    public void setPaintSector1(Paint paintSector1) {
        this.paintSector1 = paintSector1;
    }

    public Paint getPaintSector1() {
        return paintSector1;
    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        side = radius * 3;
        super.onDraw(canvas);

        Paint paintCentrCircle = new Paint();
        paintSector1 = new Paint();
        Paint paintSector2 = new Paint();
        Paint paintSector3 = new Paint();
        Paint paintSector4 = new Paint();
        paintCentrCircle.setColor(ContextCompat.getColor(getContext(), R.color.colorblack));

        paintSector1.setColor(ContextCompat.getColor(getContext(), R.color.colorred));
        paintSector2.setColor(ContextCompat.getColor(getContext(), R.color.colorgreen));
        paintSector3.setColor(ContextCompat.getColor(getContext(), R.color.colorblue));
        paintSector4.setColor(ContextCompat.getColor(getContext(), R.color.colorellow));
        paintSector1.setStyle(Paint.Style.FILL);
        paintSector2.setStyle(Paint.Style.FILL);
        paintSector3.setStyle(Paint.Style.FILL);
        paintSector4.setStyle(Paint.Style.FILL);


        RectF rect = new RectF();
        rect.set((width / 2) - side, (height / 2) - side, (width / 2) + side, (height / 2) + side);

        canvas.drawArc(rect, 0, 90, true, paintSector1);
        canvas.drawArc(rect, 90, 90, true, paintSector2);
        canvas.drawArc(rect, 180, 90, true, paintSector3);
        canvas.drawArc(rect, 270, 90, true, paintSector4);
        canvas.drawCircle(width / 2, height / 2, radius, paintCentrCircle);

    if (getX > 500){
        paintSector1.setColor(ContextCompat.getColor(getContext(), R.color.colorDefolt));
        invalidate();
    }
        //  change(canvas);


    }
/*
    public void change(int x, int y) {
        Log.d("Dima", "создание нового элемента" + canvas);
        paintnew = new Paint();
        paintnew.setColor(ContextCompat.getColor(getContext(), R.color.colorDefolt));
        paintnew.setStyle(Paint.Style.FILL);
        paintSector1.setColor(ContextCompat.getColor(getContext(), R.color.colorDefolt));
        canvas.drawCircle(300, 300, 50, paintnew);

        invalidate();
    }
*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       getX = (int) event.getX();
       getY = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchAction != null) {
                touchAction.onTouchDown((int) event.getX(), (int) event.getY());


            }
        }

        return super.onTouchEvent(event);
    }

    public void setTouchAction(TouchAction touchAction) {
        this.touchAction = touchAction;
    }
}
