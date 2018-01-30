package com.jaychang.srv.layout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

class ScrollableLinearLayoutManager extends LinearLayoutManager {

  public ScrollableLinearLayoutManager(Context context) {
    super(context);
  }

  @Override
  public void scrollToPosition(int position) {
    super.scrollToPosition(position);
  }
}
