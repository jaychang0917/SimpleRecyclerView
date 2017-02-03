package com.jaychang.srv.behavior;

import android.graphics.Canvas;
import android.view.View;

import com.jaychang.srv.SimpleRecyclerView;

public abstract class SwipeToDismissCallback<T> {

  public boolean enableDefaultFadeOutEffect() {
    return true;
  }

  public void onCellSwiping(SimpleRecyclerView simpleRecyclerView, View itemView,  T item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
  }

  public void onCellSettled(SimpleRecyclerView simpleRecyclerView, View itemView, T item, int position) {
  }

  public void onCellDismissed(SimpleRecyclerView simpleRecyclerView, T item, int position) {
  }

}
