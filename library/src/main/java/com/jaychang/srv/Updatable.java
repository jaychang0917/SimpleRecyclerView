package com.jaychang.srv;

public interface Updatable<T> {

  boolean areContentsTheSame(T newItem);

  Object getChangePayload(T newItem);

}
