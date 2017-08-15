package com.jaychang.srv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import kotlinx.android.extensions.LayoutContainer;

public class SimpleViewHolder extends RecyclerView.ViewHolder implements LayoutContainer {

  private SimpleCell cell;

  public SimpleViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  void bind(SimpleCell cell) {
    this.cell = cell;
  }

  void unbind() {
    cell = null;
  }

  SimpleCell getCell() {
    return cell;
  }

  @Nullable
  @Override
  public View getContainerView() {
    return itemView;
  }

}
