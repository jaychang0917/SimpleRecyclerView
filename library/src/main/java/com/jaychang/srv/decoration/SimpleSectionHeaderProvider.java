package com.jaychang.srv.decoration;

import android.support.annotation.NonNull;
import android.view.View;

public abstract class SimpleSectionHeaderProvider<T> implements SectionHeaderProvider<T> {

  @NonNull
  public abstract View getSectionHeaderView(@NonNull T item, int position);

  public abstract boolean isSameSection(@NonNull T item, @NonNull T nextItem);

  @Override
  public boolean isSticky() {
    return false;
  }

  @Override
  public int getSectionHeaderMarginTop(@NonNull T item, int position) {
    return 0;
  }

}
