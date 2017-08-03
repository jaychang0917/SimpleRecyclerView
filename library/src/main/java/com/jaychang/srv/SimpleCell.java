package com.jaychang.srv;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public abstract class SimpleCell<T, VH extends SimpleViewHolder> {

  public interface OnCellClickListener<T> {
    void onCellClicked(@NonNull T item);
  }

  public interface OnCellLongClickListener<T> {
    void onCellLongClicked(@NonNull T item);
  }

  public interface OnCellClickListener2<CELL, VH, T> {
    void onCellClicked(@NonNull CELL cell, @NonNull VH viewHolder, @NonNull T item);
  }

  public interface OnCellLongClickListener2<CELL, VH, T> {
    void onCellLongClicked(@NonNull CELL cell, @NonNull VH viewHolder, @NonNull T item);
  }

  private int spanSize = 1;
  private T item;
  private OnCellClickListener onCellClickListener;
  private OnCellClickListener2 onCellClickListener2;
  private OnCellLongClickListener onCellLongClickListener;
  private OnCellLongClickListener2 onCellLongClickListener2;

  public SimpleCell(@NonNull T item) {
    this.item = item;
  }

  @LayoutRes protected abstract int getLayoutRes();

  @NonNull protected abstract VH onCreateViewHolder(@NonNull ViewGroup parent, @NonNull View cellView);

  protected abstract void onBindViewHolder(@NonNull VH holder, int position, @NonNull Context context, @NonNull Object payload);

  protected void onUnbindViewHolder(@NonNull VH holder) {
  }

  @NonNull public T getItem() {
    return item;
  }

  public void setItem(@NonNull T item) {
    this.item = item;
  }

  protected abstract long getItemId();

  public int getSpanSize() {
    return spanSize;
  }

  public void setSpanSize(int spanSize) {
    this.spanSize = spanSize;
  }

  public void setOnCellClickListener(@NonNull OnCellClickListener onCellClickListener) {
    this.onCellClickListener = onCellClickListener;
  }

  public void setOnCellLongClickListener(@NonNull OnCellLongClickListener onCellLongClickListener) {
    this.onCellLongClickListener = onCellLongClickListener;
  }

  public OnCellClickListener getOnCellClickListener() {
    return onCellClickListener;
  }

  public OnCellLongClickListener getOnCellLongClickListener() {
    return onCellLongClickListener;
  }

  public void setOnCellClickListener2(@NonNull OnCellClickListener2 onCellClickListener2) {
    this.onCellClickListener2 = onCellClickListener2;
  }

  public void setOnCellLongClickListener2(@NonNull OnCellLongClickListener2 onCellLongClickListener2) {
    this.onCellLongClickListener2 = onCellLongClickListener2;
  }

  public OnCellClickListener2 getOnCellClickListener2() {
    return onCellClickListener2;
  }

  public OnCellLongClickListener2 getOnCellLongClickListener2() {
    return onCellLongClickListener2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SimpleCell<?, ?> cell = (SimpleCell<?, ?>) o;

    return getItemId() == cell.getItemId();

  }

  @Override
  public int hashCode() {
    return (int) (getItemId() ^ (getItemId() >>> 32));
  }

}
