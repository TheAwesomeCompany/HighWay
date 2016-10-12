package click.theawesome.mda.yourmechanic.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import click.theawesome.mda.yourmechanic.R;

/**
 * Created by mda on 10/11/16.
 */

public class DestinationView extends View{
    //    Todo In progress. Consider make one view for circles and text bellow.
    private String mFromAddress;
    private String mToAddress;

    //    Todo In progress. In the future I would like to support multiple circles.
    private int mDestinationCount;

    private int mFromAddressColor;
    private int mToAddressColor;

    private Paint mFromPaint;
    private float mRadios;
    private Paint mToPaint;
    private Paint mDashPaint;
    private Path mPath;

    public DestinationView(Context context) {
        super(context);
        init(context, null);
    }

    public DestinationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DestinationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DestinationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if(attrs == null){
            return;
        }

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DestinationView,
                0, 0);

        try {
            mFromAddress = a.getString(R.styleable.DestinationView_fromAddress);
            mToAddress = a.getString(R.styleable.DestinationView_toAddress);
            mDestinationCount = a.getInteger(R.styleable.DestinationView_destinationCount, 2);
            mFromAddressColor = a.getColor(R.styleable.DestinationView_fromAddressColor, getResources().getColor(R.color.colorPrimary));
            mToAddressColor = a.getColor(R.styleable.DestinationView_toAddressColor, getResources().getColor(R.color.colorAccent));
            mRadios = a.getDimensionPixelSize(R.styleable.DestinationView_radios, 10);
        } finally {
            a.recycle();
        }

        mFromPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFromPaint.setStyle(Paint.Style.STROKE);
        mFromPaint.setStrokeWidth(2);
        mFromPaint.setColor(mFromAddressColor);

        mToPaint = new Paint(mFromPaint);
        mToPaint.setColor(mToAddressColor);

        mDashPaint = new Paint(mFromPaint);
        mDashPaint.setPathEffect(new DashPathEffect(new float[] { 10, 5}, 0));

        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredWidth = (int) (getPaddingLeft() + getPaddingRight() + mRadios*6);
        int desiredHeight = (int) (getPaddingTop() + getPaddingBottom() + mRadios*2);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth() ;

        float cFirstCircleX = getPaddingLeft() + mRadios;
        float cFirstCircleY = getPaddingTop() + mRadios;
        canvas.drawCircle(cFirstCircleX, cFirstCircleY, mRadios, mFromPaint);

        int cSecondCircleX = measuredWidth / 2;
        int cSecondCircleY = (int) ((getPaddingTop() + getPaddingBottom())/2 + mRadios);
        canvas.drawCircle(cSecondCircleX, cSecondCircleY, mRadios, mToPaint);

        mPath.reset();
        mPath.moveTo(cFirstCircleX + mRadios, cFirstCircleY);
        mPath.lineTo(cSecondCircleX - mRadios, cSecondCircleY);

//        TODO Move gradient size calculation in onSizeChanged.
        mDashPaint.setShader(new LinearGradient(cFirstCircleX + mRadios,cFirstCircleY,cSecondCircleX - mRadios,cSecondCircleY, mFromAddressColor, mToAddressColor, Shader.TileMode.CLAMP));

        canvas.drawPath(mPath, mDashPaint);
    }
}
