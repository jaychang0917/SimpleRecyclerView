package com.jaychang.srv.decoration;

import android.support.annotation.NonNull;
import android.view.View;

public interface SectionHeaderProvider<T> {
  @NonNull View getSectionHeaderView(T item, int position);
  boolean isSameSection(T item, T nextItem);
  boolean isSticky();
  int getSectionHeaderMarginTop(T item, int position);
}
