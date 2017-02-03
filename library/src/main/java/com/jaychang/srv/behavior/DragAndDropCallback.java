package com.jaychang.srv.behavior;

import android.view.View;

import com.jaychang.srv.SimpleRecyclerView;

public abstract class DragAndDropCallback<T> {

  public boolean enableDefaultRaiseEffect() {
    return true;
  }

  public void onCellDragStarted(SimpleRecyclerView simpleRecyclerView, View itemView, T item, int position) {
  }

  public void onCellMoved(SimpleRecyclerView simpleRecyclerView, View itemView, T item, int fromPosition, int toPosition) {
  }

  public void onCellDropped(SimpleRecyclerView simpleRecyclerView, View itemView, T item, int initialPosition, int toPosition) {
  }

  public void onCellDragCancelled(SimpleRecyclerView simpleRecyclerView, View itemView, T item, int currentPosition) {
  }

}
