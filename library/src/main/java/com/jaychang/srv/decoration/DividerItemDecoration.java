/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jaychang.srv.decoration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.jaychang.srv.SimpleAdapter;
import com.jaychang.srv.SimpleRecyclerView;

import java.util.List;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
  public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
  public static final int VERTICAL = LinearLayout.VERTICAL;

  private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

  private Drawable mDivider;

  private int mOrientation;

  private boolean showLastDivider;

  private final Rect mBounds = new Rect();

  private List<String> noDividerCellTypes;
  private RecyclerView.LayoutManager layoutManager;
  private LinearLayoutManager linearLayoutManager;
  private GridLayoutManager gridLayoutManager;

  public DividerItemDecoration(Context context, int orientation) {
    final TypedArray a = context.obtainStyledAttributes(ATTRS);
    mDivider = a.getDrawable(0);
    a.recycle();
    setOrientation(orientation);
  }

  public void setOrientation(int orientation) {
    if (orientation != HORIZONTAL && orientation != VERTICAL) {
      throw new IllegalArgumentException(
        "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
    }
    mOrientation = orientation;
  }

  public void setShowLastDivider(boolean showLastDivider) {
    this.showLastDivider = showLastDivider;
  }

  public void setDrawable(@NonNull Drawable drawable) {
    if (drawable == null) {
      throw new IllegalArgumentException("Drawable cannot be null.");
    }
    mDivider = drawable;
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    if (parent.getLayoutManager() == null) {
      return;
    }

    if (mOrientation == VERTICAL) {
      drawHorizontalDivider(c, parent);
    } else {
      drawVerticalDivider(c, parent);
    }
  }

  @SuppressLint("NewApi")
  private void drawHorizontalDivider(Canvas canvas, RecyclerView parent) {
    canvas.save();
    final int left;
    final int right;
    if (parent.getClipToPadding()) {
      left = parent.getPaddingLeft();
      right = parent.getWidth() - parent.getPaddingRight();
      canvas.clipRect(left, parent.getPaddingTop(), right,
        parent.getHeight() - parent.getPaddingBottom());
    } else {
      left = 0;
      right = parent.getWidth();
    }

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);

      if (ignoreDrawDividerForCellTypes(parent, i) || ignoreDrawHorizontalDivider(parent, child)) {
        continue;
      }

      parent.getDecoratedBoundsWithMargins(child, mBounds);
      final int bottom = mBounds.bottom;
      final int top = bottom - mDivider.getIntrinsicHeight();
      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(canvas);
    }
    canvas.restore();
  }

  @SuppressLint("NewApi")
  private void drawVerticalDivider(Canvas canvas, RecyclerView parent) {
    canvas.save();
    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);

      if (ignoreDrawDividerForCellTypes(parent, i) || ignoreDrawVerticalDivider(parent, child)) {
        continue;
      }

      parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
      final int right = mBounds.right;
      final int left = right - mDivider.getIntrinsicWidth();
      final int bottom = child.getBottom();
      final int top = child.getTop();
      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(canvas);
    }
    canvas.restore();
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                             RecyclerView.State state) {
    if (mOrientation == VERTICAL) {
      if (ignoreDrawHorizontalDivider(parent, view)) {
        outRect.set(0, 0, 0, 0);
      } else {
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
      }
    } else {
      if (ignoreDrawVerticalDivider(parent, view)) {
        outRect.set(0, 0, 0, 0);
      } else {
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
      }
    }
  }

  private boolean ignoreDrawDividerForCellTypes(RecyclerView parent, int position) {
    if (noDividerCellTypes == null) {
      noDividerCellTypes = ((SimpleRecyclerView) parent).getNoDividerCellTypes();
    }

    if (noDividerCellTypes.size() <= 0) {
      return false;
    }

    if (layoutManager == null) {
      layoutManager = parent.getLayoutManager();
    }

    if (layoutManager instanceof LinearLayoutManager) {
      if (linearLayoutManager == null) {
        linearLayoutManager = (LinearLayoutManager) layoutManager;
      }

      int pos = linearLayoutManager.findFirstVisibleItemPosition();

      if (pos == -1) {
        return false;
      }

      String type = ((SimpleAdapter) parent.getAdapter()).getCell(pos + position).getClass().getSimpleName();
      return noDividerCellTypes.contains(type);
    }

    return false;
  }

  private boolean ignoreDrawVerticalDivider(RecyclerView parent, View view) {
    if (showLastDivider) {
      return false;
    }

    if (layoutManager == null) {
      layoutManager = parent.getLayoutManager();
    }

    int position = parent.getChildAdapterPosition(view);
    int totalChildCount = parent.getAdapter().getItemCount();

    if (layoutManager instanceof GridLayoutManager) {
      if (gridLayoutManager == null) {
        gridLayoutManager = (GridLayoutManager) layoutManager;
      }
      int spanCount = gridLayoutManager.getSpanCount();
      int spanSize = gridLayoutManager.getSpanSizeLookup().getSpanSize(position);
      int column = gridLayoutManager.getSpanSizeLookup().getSpanIndex(position, spanCount);
      if (column + spanSize >= spanCount) {
        return true;
      }
    } else if (layoutManager instanceof LinearLayoutManager) {
      return position == totalChildCount - 1;
    }

    return false;
  }

  private boolean ignoreDrawHorizontalDivider(RecyclerView parent, View view) {
    if (showLastDivider) {
      return false;
    }

    if (layoutManager == null) {
      layoutManager = parent.getLayoutManager();
    }

    int position = parent.getChildAdapterPosition(view);
    int totalChildCount = parent.getAdapter().getItemCount();

    if (layoutManager instanceof GridLayoutManager) {
      if (gridLayoutManager == null) {
        gridLayoutManager = (GridLayoutManager) layoutManager;
      }
      int spanCount = gridLayoutManager.getSpanCount();
      int column = gridLayoutManager.getSpanSizeLookup().getSpanIndex(position, spanCount);
      int spanSize = gridLayoutManager.getSpanSizeLookup().getSpanSize(position);
      // check if next row first item's index is the last index
      boolean isLastRow;
      if (spanSize == 1) {
        isLastRow = position + spanCount - column > totalChildCount - 1;
      } else {
        isLastRow = position + spanSize - column > totalChildCount - 1;
      }
      if (isLastRow) {
        return true;
      }
    } else if (layoutManager instanceof LinearLayoutManager) {
      return position == totalChildCount - 1;
    }

    return false;
  }

}

