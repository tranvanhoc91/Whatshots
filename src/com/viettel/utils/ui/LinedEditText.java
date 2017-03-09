/**
 * Copyright 2010 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.utils.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Control EditText hien thi line
 * 
 * @author: TruongHN
 * @version: 1.0
 * @since: 1.0
 */
public class LinedEditText extends EditText {
	private Rect mRect;
	private Paint mPaint;

	// we need this constructor for LayoutInflater
	public LinedEditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		mRect = new Rect();
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(0xffCCCCCC);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Rect bounds = new Rect();
		Paint paint = mPaint;
		int fontDescent = paint.getFontMetricsInt().descent;
		int firstLineY = getLineBounds(0, bounds) + fontDescent;
		int lineHeight = getLineHeight();
		int totalLines = Math.max(getLineCount(), getHeight() / lineHeight);
		for (int i = 0; i < totalLines; i++) {
			int lineY = firstLineY + i * lineHeight;
			canvas.drawLine(bounds.left, lineY + 1, bounds.right, lineY + 1, mPaint);
		}

		super.onDraw(canvas);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_ENTER) {
			// Just ignore the [Enter] key
			return true;
		}
		// Handle all other keys in the default way
		return super.onKeyDown(keyCode, event);
	}
}
