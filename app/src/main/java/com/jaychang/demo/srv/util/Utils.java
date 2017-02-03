package com.jaychang.demo.srv.util;

import android.content.Context;

public final class Utils {

  private Utils() {
  }

  public static int dp2px(Context context, int dp) {
    float density = context.getApplicationContext().getApplicationContext().getResources().getDisplayMetrics().density;
    return (int) (dp * density + 0.5f);
  }

}
