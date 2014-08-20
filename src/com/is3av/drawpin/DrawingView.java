package com.is3av.drawpin;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
	private Paint       mPaint;
	public int width;
	public  int height;
	private Bitmap  mBitmap;
	private Canvas  mCanvas;
	private Path    mPath;
	private Paint   mBitmapPaint;
	Context context;
	private Paint circlePaint;
	private Path circlePath;
	ArrayList<Float> pressureList = new ArrayList<Float>();
	ArrayList<Float> sizeList = new ArrayList<Float>();

	public DrawingView(Context c) {
		super(c);
		context = c; 
		init();
	}
	public DrawingView(Context context,AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public DrawingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public void init() {

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.GREEN);  //use a variable to set the color
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(36);  

		mPath = new Path();
		mBitmapPaint = new Paint(Paint.DITHER_FLAG);  
		circlePaint = new Paint();
		circlePath = new Path();
		circlePaint.setAntiAlias(true);
		circlePaint.setColor(Color.GRAY);
		circlePaint.setStyle(Paint.Style.STROKE);
		circlePaint.setStrokeJoin(Paint.Join.MITER);
		circlePaint.setStrokeWidth(4f); 

	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mCanvas.drawColor(Color.BLACK);

	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);

		canvas.drawPath( mPath,  mPaint);

		canvas.drawPath( circlePath,  circlePaint);
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}
	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
			mX = x;
			mY = y;

			circlePath.reset();
			circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
		}
	}
	private void touch_up() {
		mPath.lineTo(mX, mY);
		circlePath.reset();
		// commit the path to our offscreen
		mCanvas.drawPath(mPath,  mPaint);
		// kill this so we don't double draw
		mPath.reset();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		float pressure;
		float size;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pressure = event.getPressure();
			size = event.getSize();
			touch_start(x, y);
			pressureList.add(pressure);
			sizeList.add(size);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			pressure = event.getPressure();
			size = event.getSize();
			touch_move(x, y);
			pressureList.add(pressure);
			sizeList.add(size);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			pressure = event.getPressure();
			size = event.getSize();
			touch_up();
			pressureList.add(pressure);
			sizeList.add(size);
			invalidate();
			break;
		}
		return true;
	}

	public void clearCanvas() {		
		mCanvas.drawColor(Color.BLACK);
		invalidate();
	}  
	public void saveCanvas() {
		//save it in a file on sd card or DB
		//upload the arrayLists to server
		clearCanvas();
	}
	
	public void authenticate() {
		//Authentication code goes here
		//Call async task
	}
}
