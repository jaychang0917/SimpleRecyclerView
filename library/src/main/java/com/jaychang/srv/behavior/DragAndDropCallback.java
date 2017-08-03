package com.jaychang.srv.behavior;

import android.support.annotation.NonNull;
import android.view.View;

import com.jaychang.srv.SimpleRecyclerView;

public abstract class DragAndDropCallback<T> {

  public boolean enableDefaultRaiseEffect() {
    return true;
  }

  public void onCellDragStarted(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull T item, int position) {
  }

  public void onCellMoved(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull T item, int fromPosition, int toPosition) {
  }

  public void onCellDropped(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull T item, int initialPosition, int toPosition) {
  }

  public void onCellDragCancelled(@NonNull SimpleRecyclerView simpleRecyclerView, @NonNull View itemView, @NonNull T item, int currentPosition) {
  }

}
