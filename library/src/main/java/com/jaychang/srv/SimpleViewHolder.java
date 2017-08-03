package com.jaychang.srv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleViewHolder extends RecyclerView.ViewHolder {

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

}
