package com.jaychang.srv;

import android.support.annotation.NonNull;

public interface Updatable<T> {

  boolean areContentsTheSame(@NonNull T newItem);

  Object getChangePayload(@NonNull T newItem);

}
