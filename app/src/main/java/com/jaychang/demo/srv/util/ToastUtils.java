package com.jaychang.demo.srv.util;

import android.content.Context;
import android.widget.Toast;

public final class ToastUtils {

  public static void show(Context context, String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }

}
