package com.viettel.utils.ui;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.UpdateAppearance;

public abstract class TexViewClickableSpan extends ClickableSpan implements UpdateAppearance {

    public void updateDrawState(TextPaint ds) {
        //ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}