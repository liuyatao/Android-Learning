package com.example.circleimage;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * onMeasure的用法
 * 
 * @author liuyatao 2014上午10:56:10
 */
public class CustomView1 extends View {

	private int height;
	private int width;
	private static final String Tag = "CostumView1";

	public CustomView1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public CustomView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		int cx = width / 2; // 圆心x
		int cy = height / 2; // 圆心y
		int r = cx; // 半径
		Paint paint = new Paint();
		paint.setARGB(40, 20, 20, 20);
		canvas.drawCircle(cx, cy, cx, paint); // 画一个圆形轮廓

		Paint paint2 = new Paint();
		paint2.setARGB(100, 100, 100, 100);
		paint2.setStrokeWidth(20);
		canvas.drawLine(cx, cy,
				(float) (cx + (r - 100) * Math.cos(getTime()[0])),
				(float) (cy + (r - 100) * Math.sin(getTime()[0])), paint2); // 画时针

		Paint paint3 = new Paint();
		paint3.setARGB(255, 255, 255, 255);
		paint3.setStrokeWidth(10);
		canvas.drawLine(cx, cy,
				(float) (cx + (r - 60) * Math.cos(getTime()[1])),
				(float) (cy + (r - 60) * Math.sin(getTime()[1])), paint3); // 画分针

		Paint paint5 = new Paint();
		paint5.setTextSize(40);
		paint5.setARGB(255, 0, 0, 255);
		paint5.setStrokeWidth(10);
		canvas.drawLine(cx, cy,
				(float) (cx + (r - 10) * Math.cos(getTime()[2])),
				(float) (cy + (r - 10) * Math.sin(getTime()[2])), paint5); // 画分针

		for (int i = 0; i < 12; i++) { // 画数字
			if (i == 0) {
				canvas.drawText("12",
						(float) (cx + Math.cos(i * (2 * Math.PI) / 12)
								* (r - 20)),
						(float) (cy + Math.sin(i * (2 * Math.PI) / 12)
								* (r - 20)), paint5);
			} else {
				canvas.drawText(i + "",
						(float) (cx + Math.cos(i * (2 * Math.PI) / 12)
								* (r - 20)),
						(float) (cy + Math.sin(i * (2 * Math.PI) / 12)
								* (r - 20)), paint5);

			}

		}

	}

	private float[] getTime() {
		// 获取时针、分针、表针现在对应的角度
		Calendar calendar = Calendar.getInstance();
		float Second = (float) ((((float) calendar.get(Calendar.SECOND)) / 60) * 2 * Math.PI); // 秒针当前的弧度
		float Minute = (float) ((((float) calendar.get(Calendar.MINUTE)) / 60)
				* Math.PI * 2); // 分针当前的弧度
		float Hour = (float) ((((float) calendar.get(Calendar.HOUR)) / 12)
				* Math.PI * 2 + Minute / 60);// 当前时针的位置
		// 小时对应的弧度+分钟转化为小时对应的弧度
		float[] Time = { Hour, Minute, Second };
		return Time;

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int widthMeasureSpec) {
		// TODO Auto-generated method stub
		int result = 0; // 结果
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		switch (specMode) {
		case MeasureSpec.AT_MOST: // 子容器可以是声明大小内的任意大小
			Log.e(Tag, "子容器可以是声明大小内的任意大小");
			Log.e(Tag, "大小为:" + specSize);
			result = specSize;
			break;
		case MeasureSpec.EXACTLY: // 父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.
									// 比如EditTextView中的DrawLeft
			Log.e(Tag, "父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间");
			Log.e(Tag, "大小为:" + specSize);
			result = specSize;
			width = specSize;
			break;
		case MeasureSpec.UNSPECIFIED: // 父容器对于子容器没有任何限制,子容器想要多大就多大.
										// 所以完全取决于子view的大小
			Log.e(Tag, "父容器对于子容器没有任何限制,子容器想要多大就多大");
			Log.e(Tag, "大小为:" + specSize);
			result = 4000;
			break;

		default:
			break;
		}
		return result;
	}

	private int measureHeight(int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int result = 0; // 结果
		int specMode = MeasureSpec.getMode(heightMeasureSpec);
		int specSize = MeasureSpec.getSize(heightMeasureSpec);
		switch (specMode) {
		case MeasureSpec.AT_MOST: // 子容器可以是声明大小内的任意大小
			Log.e(Tag, "子容器可以是声明大小内的任意大小");
			Log.e(Tag, "大小为:" + specSize);
			result = specSize;
			break;
		case MeasureSpec.EXACTLY: // 父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.
									// 比如EditTextView中的DrawLeft
			Log.e(Tag, "父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间");
			Log.e(Tag, "大小为:" + specSize);
			result = specSize;
			height = specSize;
			break;
		case MeasureSpec.UNSPECIFIED: // 父容器对于子容器没有任何限制,子容器想要多大就多大.
										// 所以完全取决于子view的大小
			Log.e(Tag, "父容器对于子容器没有任何限制,子容器想要多大就多大");
			Log.e(Tag, "大小为:" + specSize);
			result = 4000;
			break;

		default:
			break;
		}
		return result;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

}
