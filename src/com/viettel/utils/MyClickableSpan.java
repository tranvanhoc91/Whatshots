package com.viettel.utils;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.UpdateAppearance;

public abstract class MyClickableSpan extends ClickableSpan implements UpdateAppearance {

    public void updateDrawState(TextPaint ds) {
        //ds.setColor(ds.linkColor);
       // ds.setUnderlineText(false);
    }
}