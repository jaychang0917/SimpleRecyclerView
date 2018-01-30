package com.jaychang.srv;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class SimpleLinearSmoothScroller extends LinearSmoothScroller {
  private ScrollPosition verticalScrollPosition;
  private ScrollPosition horizontalScrollPosition;
  private boolean skipSpacing;

  SimpleLinearSmoothScroller(Context context, boolean skipSpacing) {
    super(context);
    this.skipSpacing = skipSpacing;
  }

  void setVerticalScrollPosition(ScrollPosition verticalScrollPosition) {
    this.verticalScrollPosition = verticalScrollPosition;
  }

  void setHorizontalScrollPosition(ScrollPosition horizontalScrollPosition) {
    this.horizontalScrollPosition = horizontalScrollPosition;
  }

  @Override
  protected int getVerticalSnapPreference() {
    return verticalScrollPosition == null ? super.getVerticalSnapPreference() : verticalScrollPosition.value;
  }

  @Override
  protected int getHorizontalSnapPreference() {
    return horizontalScrollPosition == null ? super.getHorizontalSnapPreference() : horizontalScrollPosition.value;
  }

  @Override
  public int calculateDyToMakeVisible(View view, int snapPreference) {
    RecyclerView.LayoutManager layoutManager = getLayoutManager();
    if (layoutManager == null || !layoutManager.canScrollVertically()) {
      return 0;
    }
    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
    int top = verticalScrollPosition == ScrollPosition.TOP ?
      (layoutManager.getDecoratedTop(view) +
      (skipSpacing ? layoutManager.getTopDecorationHeight(view) : 0) +
      layoutManager.getPaddingTop() -
      params.topMargin) :
      layoutManager.getDecoratedTop(view) - params.topMargin;
    int bottom = verticalScrollPosition == ScrollPosition.BOTTOM ?
      (layoutManager.getDecoratedBottom(view) -
      (skipSpacing ? layoutManager.getBottomDecorationHeight(view) : 0) -
      layoutManager.getPaddingBottom() +
      params.bottomMargin) :
      layoutManager.getDecoratedBottom(view) + params.bottomMargin;
    int start = layoutManager.getPaddingTop();
    int end = layoutManager.getHeight() - layoutManager.getPaddingBottom();
    return calculateDtToFit(top, bottom, start, end, snapPreference);
  }

  @Override
  public int calculateDxToMakeVisible(View view, int snapPreference) {
    RecyclerView.LayoutManager layoutManager = getLayoutManager();
    if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
      return 0;
    }
    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
    int left = layoutManager.getDecoratedLeft(view) +
      (skipSpacing ? layoutManager.getLeftDecorationWidth(view) : -layoutManager.getLeftDecorationWidth(view)) +
      layoutManager.getPaddingLeft() -
      params.leftMargin;
    int right = layoutManager.getDecoratedRight(view) -
      (skipSpacing ? layoutManager.getRightDecorationWidth(view) : -layoutManager.getRightDecorationWidth(view)) -
      layoutManager.getPaddingRight() +
      params.rightMargin;
    int start = layoutManager.getPaddingLeft();
    int end = layoutManager.getWidth() - layoutManager.getPaddingRight();
    return calculateDtToFit(left, right, start, end, snapPreference);
  }
}
