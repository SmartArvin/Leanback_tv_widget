/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.clendy.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;

import io.github.clendy.leanback.R;


/**
 * A {@link android.view.ViewGroup} that shows items in a vertically scrolling list. The items
 * come from the {@link Adapter} associated with this view.
 * <p>
 * {@link Adapter} can optionally implement {@link FacetProviderAdapter} which
 * provides {@link FacetProvider} for a given view type;  {@link ViewHolder}
 * can also implement {@link FacetProvider}.  Facet from ViewHolder
 * has a higher priority than the one from FacetProiderAdapter associated with viewType.
 * Supported optional facets are:
 * <ol>
 * <li> {@link ItemAlignmentFacet}
 * When this facet is provided by ViewHolder or FacetProviderAdapter,  it will
 * override the item alignment settings set on VerticalGridView.  This facet also allows multiple
 * alignment positions within one ViewHolder.
 * </li>
 * </ol>
 */
public class VerticalGridView extends BaseGridView {

    public VerticalGridView(Context context) {
        this(context, null);
    }

    public VerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLayoutManager.setOrientation(VERTICAL);
        initAttributes(context, attrs);
    }

    protected void initAttributes(Context context, AttributeSet attrs) {
        initBaseGridViewAttributes(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.lbVerticalGridView);
        setColumnWidth(a);
        setNumColumns(a.getInt(R.styleable.lbVerticalGridView_numberOfColumns, 1));
        a.recycle();
    }

    void setColumnWidth(TypedArray array) {
        TypedValue typedValue = array.peekValue(R.styleable.lbVerticalGridView_columnWidth);
        if (typedValue != null) {
            int size = array.getLayoutDimension(R.styleable.lbVerticalGridView_columnWidth, 0);
            setColumnWidth(size);
        }
    }

    /**
     * Sets the number of columns.  Defaults to one.
     */
    public void setNumColumns(int numColumns) {
        mLayoutManager.setNumRows(numColumns);
        requestLayout();
    }

    /**
     * Sets the column width.
     *
     * @param width May be {@link android.view.ViewGroup.LayoutParams#WRAP_CONTENT}, or a size
     *              in pixels. If zero, column width will be fixed based on number of columns
     *              and view width.
     */
    public void setColumnWidth(int width) {
        mLayoutManager.setRowHeight(width);
        requestLayout();
    }

    long mOldTime = 0;
    long mTimeStamp = 280;

    /**
     *  设置按键滚动的时间间隔.
     *  在小于time的间隔内消除掉.
     */
    public void setKeyScrollTime(long time) {
        this.mTimeStamp = time;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        /**
         *  用于优化按键快速滚动卡顿的问题.
         */
        if (event.getRepeatCount() >= 2 && event.getAction()==KeyEvent.ACTION_DOWN){
            long currentTime = System.currentTimeMillis();
            if (currentTime - mOldTime <= mTimeStamp) {
                return true;
            }
            mOldTime = currentTime;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onRequestFocusInDescendants(int direction, Rect rect) {
        int childCount = getChildCount();
        int i4;
        int i2 = -1;
        int i3;
        if ((direction & View.FOCUS_UP) != 0) {
            Rect rect2 = new Rect();
            getChildAt(0).getFocusedRect(rect2);
            offsetDescendantRectToMyCoords(getChildAt(0), rect2);
            i3 = 1;
            i2 = 0;
            while (i3 != childCount) {
                Rect rect3;
                if (getChildAt(i3).getVisibility() == View.VISIBLE) {
                    rect3 = new Rect();
                    getChildAt(i3).getFocusedRect(rect3);
                    offsetDescendantRectToMyCoords(getChildAt(i3), rect3);
                    if (rect3.bottom > rect2.bottom) {
                        i2 = i3;
                        i3++;
                        rect2 = rect3;
                    }
                }
                rect3 = rect2;
                i3++;
                rect2 = rect3;
            }
            if (i2 < childCount && getChildAt(i2).requestFocus(direction, rect)) {
                return true;
            }
        }

        if ((direction & FOCUS_FORWARD) != 0) {
            i2 = childCount;
            i3 = 1;
            i4 = 0;
        } else {
            i4 = childCount - 1;
            i3 = -1;
        }
        //
        while (i4 != i2) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() == View.VISIBLE && childAt.requestFocus(direction, rect)) {
                return true;
            }
            i4 += i3;
        }
        return false;
    }

}
