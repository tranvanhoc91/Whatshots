package com.viettel.listener;

import android.graphics.Bitmap;

public interface ImageDownLoadListener {
	public void onStart();
	public void onFinished(Bitmap bitmap);
}
