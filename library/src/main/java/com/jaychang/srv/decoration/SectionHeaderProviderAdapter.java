package com.jaychang.srv.decoration;

import android.support.annotation.NonNull;
import android.view.View;

public abstract class SectionHeaderProviderAdapter<T> implements SectionHeaderProvider<T> {

  @NonNull
  public abstract View getSectionHeaderView(T item, int position);

  public abstract boolean isSameSection(T item, T nextItem);

  @Override
  public boolean isSticky() {
    return false;
  }

  @Override
  public int getSectionHeaderMarginTop(T item, int position) {
    return 0;
  }

}
