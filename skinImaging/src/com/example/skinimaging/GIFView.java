package com.example.skinimaging;

import java.io.InputStream;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class GIFView extends View {
	private String mExampleString; // TODO: use a default from R.string...
	private int mExampleColor = Color.RED; // TODO: use a default from
											// R.color...
	private float mExampleDimension = 0; // TODO: use a default from R.dimen...
	private Drawable mExampleDrawable;

	private TextPaint mTextPaint;
	private float mTextWidth;
	private float mTextHeight;
	private int mDrawLeftPos;
	
	Movie movie,movie1;
	InputStream is=null,is1=null;
	long moviestart;
	private int gifId;

	public GIFView(Context context) {
		super(context);
		//init(null, 0);
		initializeView();

	}

	public GIFView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//init(attrs, 0);
	}

	public GIFView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//init(attrs, defStyle);
	}

	private void initializeView(){
		if (gifId !=0) {
			is = getContext().getResources().openRawResource(gifId);
		} else {
			is = getContext().getResources().openRawResource(R.drawable.skingif1);
		}
		movie = Movie.decodeStream(is);
	}
	
	public void setGIFResource(int resId) {
	    this.gifId = resId;
	    initializeView();
	}

	public int getGIFResource() {
	    return this.gifId;
	}
	
	private void setAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GIFView, 0, 0);
            String gifSource = a.getString(R.styleable.GIFView_src);
            //little workaround here. Who knows better approach on how to easily get resource id - please share
            String sourceName = Uri.parse(gifSource).getLastPathSegment().replace(".gif", "");
            setGIFResource(getResources().getIdentifier(sourceName, "drawable", getContext().getPackageName()));
            a.recycle();
        }
    }
	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.GIFView, defStyle, 0);

		mExampleString = a.getString(R.styleable.GIFView_exampleString);
		mExampleColor = a.getColor(R.styleable.GIFView_exampleColor,
				mExampleColor);
		// Use getDimensionPixelSize or getDimensionPixelOffset when dealing
		// with
		// values that should fall on pixel boundaries.
		mExampleDimension = a.getDimension(
				R.styleable.GIFView_exampleDimension, mExampleDimension);

		if (a.hasValue(R.styleable.GIFView_exampleDrawable)) {
			mExampleDrawable = a
					.getDrawable(R.styleable.GIFView_exampleDrawable);
			mExampleDrawable.setCallback(this);
		}

		a.recycle();

		// Set up a default TextPaint object
		mTextPaint = new TextPaint();
		mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextAlign(Paint.Align.LEFT);

		// Update TextPaint and text measurements from attributes
		invalidateTextPaintAndMeasurements();
	}

	private void invalidateTextPaintAndMeasurements() {
		mTextPaint.setTextSize(mExampleDimension);
		mTextPaint.setColor(mExampleColor);
		mTextWidth = mTextPaint.measureText(mExampleString);

		Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
		mTextHeight = fontMetrics.bottom;
	}
	
	/*@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec )
	{   
	    int p_top = this.getPaddingTop(), p_bottom = this.getPaddingBottom();

	    // Calculate new desired height
	    final int desiredHSpec = MeasureSpec.makeMeasureSpec( movie.height() + p_top + p_bottom , MeasureSpec.EXACTLY );

	    setMeasuredDimension( widthMeasureSpec, desiredHSpec );
	    super.onMeasure( widthMeasureSpec, desiredHSpec );

	    // Update the draw left position
	    mDrawLeftPos = Math.max( ( this.getWidth() - movie.width() ) / 2, 0) ;
	}*/

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		super.onDraw(canvas);
		if (movie!=null) {
			long now=android.os.SystemClock.uptimeMillis();
			//System.out.println("now="+now);
			if (moviestart == 0) { // first time
				moviestart = now;
			}
			//System.out.println("\tmoviestart="+moviestart);
			int relTime = (int)((now - moviestart) % movie.duration()) ;
			//System.out.println("time="+relTime+"\treltime="+movie.duration());
			movie.setTime(relTime);
			double scalefactorx = (double) this.getWidth() / (double) movie.width();
			double scalefactory = (double) this.getHeight() / (double) movie.height();
			canvas.scale((float) scalefactorx, (float) scalefactory); 
			movie.draw(canvas,(float) scalefactorx, (float)scalefactory);
		    //mDrawLeftPos = Math.max( ( this.getWidth() - movie.width() ) / 2, 0) ;
			//movie.draw(canvas, mDrawLeftPos, this.getPaddingTop());
			this.invalidate();
		}
	}

	/**
	 * Gets the example string attribute value.
	 * 
	 * @return The example string attribute value.
	 */
	public String getExampleString() {
		return mExampleString;
	}

	/**
	 * Sets the view's example string attribute value. In the example view, this
	 * string is the text to draw.
	 * 
	 * @param exampleString
	 *            The example string attribute value to use.
	 */
	public void setExampleString(String exampleString) {
		mExampleString = exampleString;
		invalidateTextPaintAndMeasurements();
	}
//
//	/**
//	 * Gets the example color attribute value.
//	 * 
//	 * @return The example color attribute value.
//	 */
//	public int getExampleColor() {
//		return mExampleColor;
//	}
//
//	/**
//	 * Sets the view's example color attribute value. In the example view, this
//	 * color is the font color.
//	 * 
//	 * @param exampleColor
//	 *            The example color attribute value to use.
//	 */
//	public void setExampleColor(int exampleColor) {
//		mExampleColor = exampleColor;
//		invalidateTextPaintAndMeasurements();
//	}
//
//	/**
//	 * Gets the example dimension attribute value.
//	 * 
//	 * @return The example dimension attribute value.
//	 */
//	public float getExampleDimension() {
//		return mExampleDimension;
//	}
//
//	/**
//	 * Sets the view's example dimension attribute value. In the example view,
//	 * this dimension is the font size.
//	 * 
//	 * @param exampleDimension
//	 *            The example dimension attribute value to use.
//	 */
//	public void setExampleDimension(float exampleDimension) {
//		mExampleDimension = exampleDimension;
//		invalidateTextPaintAndMeasurements();
//	}
//
//	/**
//	 * Gets the example drawable attribute value.
//	 * 
//	 * @return The example drawable attribute value.
//	 */
//	public Drawable getExampleDrawable() {
//		return mExampleDrawable;
//	}
//
//	/**
//	 * Sets the view's example drawable attribute value. In the example view,
//	 * this drawable is drawn above the text.
//	 * 
//	 * @param exampleDrawable
//	 *            The example drawable attribute value to use.
//	 */
//	public void setExampleDrawable(Drawable exampleDrawable) {
//		mExampleDrawable = exampleDrawable;
//	}
}
