package com.jaychang.srv.kae

import android.view.View
import android.view.ViewGroup

abstract class SimpleCell<T>(item: T) : com.jaychang.srv.SimpleCell<T, SimpleViewHolder>(item) {
  override fun onCreateViewHolder(parent: ViewGroup, cellView: View): SimpleViewHolder {
    return SimpleViewHolder(cellView)
  }
}