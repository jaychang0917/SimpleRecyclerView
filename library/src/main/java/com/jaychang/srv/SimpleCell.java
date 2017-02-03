package com.jaychang.srv;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

public abstract class SimpleCell<T, VH extends SimpleViewHolder> {

  public interface OnCellClickListener<T> {
    void onCellClicked(T item);
  }

  public interface OnCellLongClickListener<T> {
    void onCellLongClicked(T item);
  }

  private int spanSize = 1;
  private T item;
  private OnCellClickListener onCellClickListener;
  private OnCellLongClickListener onCellLongClickListener;

  public SimpleCell(T item) {
    this.item = item;
  }

  @LayoutRes protected abstract int getLayoutRes();

  @NonNull protected abstract VH onCreateViewHolder(ViewGroup parent, View cellView);

  protected abstract void onBindViewHolder(VH holder, int position, Context context, Object payload);

  protected void onUnbindViewHolder(VH holder) {
  }

  public T getItem() {
    return item;
  }

  public void setItem(T item) {
    this.item = item;
  }

  protected abstract long getItemId();

  public int getSpanSize() {
    return spanSize;
  }

  public void setSpanSize(int spanSize) {
    this.spanSize = spanSize;
  }

  public void setOnCellClickListener(OnCellClickListener onCellClickListener) {
    this.onCellClickListener = onCellClickListener;
  }

  public void setOnCellLongClickListener(OnCellLongClickListener onCellLongClickListener) {
    this.onCellLongClickListener = onCellLongClickListener;
  }

  public OnCellClickListener getOnCellClickListener() {
    return onCellClickListener;
  }

  public OnCellLongClickListener getOnCellLongClickListener() {
    return onCellLongClickListener;
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
