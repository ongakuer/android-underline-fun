package me.relex.underline;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.DynamicDrawableSpan;

public class UnderlineImageSpan extends DynamicDrawableSpan {

    private Paint.FontMetricsInt mTextFontMetricsInt;

    private UnderlineDrawable mUnderlineDrawable;

    private int mSpaceHeight;

    public UnderlineImageSpan(int lineHeight, TextPaint textPaint, String text,
            int underlineColor) {

        mTextFontMetricsInt = textPaint.getFontMetricsInt();
        mUnderlineDrawable = new UnderlineDrawable(textPaint, text, underlineColor);
        mSpaceHeight =
                Math.abs(mTextFontMetricsInt.descent - mTextFontMetricsInt.ascent - lineHeight);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y,
            int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = bottom - b.getBounds().bottom;

        transY -= mTextFontMetricsInt.descent;

        if (mSpaceHeight > 0) {
            transY -= mSpaceHeight;
        }

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

    @Override public Drawable getDrawable() {
        return mUnderlineDrawable;
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end,
            Paint.FontMetricsInt fm) {

        Drawable d = getDrawable();
        Rect rect = d.getBounds();

        if (fm != null) {

            if (mTextFontMetricsInt == null) {
                fm.ascent = -rect.bottom;
                fm.descent = 0;
                fm.top = fm.ascent;
                fm.bottom = 0;
            } else {
                fm.ascent = mTextFontMetricsInt.ascent;
                fm.descent = mTextFontMetricsInt.descent;
                fm.top = mTextFontMetricsInt.top;
                fm.bottom = mTextFontMetricsInt.bottom;
            }
        }

        return rect.right;
    }

    public class UnderlineDrawable extends Drawable {

        private TextPaint mTextPaint;

        private Paint mLinePaint;

        private Paint.FontMetricsInt mTextFontMetricsInt;

        private String mText;

        private int mIntrinsicWidth;

        private int mIntrinsicHeight;

        public UnderlineDrawable(TextPaint textPaint, String text, int underlineColor) {
            mText = text;
            mTextPaint = textPaint;
            mLinePaint = new Paint();

            mLinePaint.setColor(underlineColor);
            mLinePaint.setStrokeWidth(mTextPaint.getTextSize() / 18);

            mTextFontMetricsInt = textPaint.getFontMetricsInt();
            mIntrinsicWidth = getIntrinsicWidth();
            mIntrinsicHeight = getIntrinsicHeight();

            setBounds(0, 0, mIntrinsicWidth, mIntrinsicHeight);
        }

        @Override public void draw(Canvas canvas) {

            Rect rect = getBounds();

            canvas.drawText(mText, 0, mIntrinsicHeight, mTextPaint);
            canvas.drawLine(rect.left, mIntrinsicHeight + mTextFontMetricsInt.bottom, rect.right,
                    mIntrinsicHeight + mTextFontMetricsInt.bottom, mLinePaint);
        }

        @Override public void setAlpha(int alpha) {

        }

        @Override public void setColorFilter(ColorFilter cf) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }

        @Override
        public int getIntrinsicHeight() {
            return (int) (Math.abs(mTextFontMetricsInt.ascent));
        }

        @Override
        public int getIntrinsicWidth() {
            Rect bounds = new Rect();
            mTextPaint.getTextBounds(mText, 0, mText.length(), bounds);
            return bounds.right;
        }
    }
}


