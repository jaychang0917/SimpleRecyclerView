package com.jaychang.demo.srv.cell;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaychang.demo.srv.R;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberCell extends SimpleCell<Integer, NumberCell.ViewHolder> {

  public NumberCell(Integer number) {
    super(number);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.cell_book_num;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new ViewHolder(cellView);
  }

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
    holder.textView.setText(String.valueOf(getItem()));
  }

  static class ViewHolder extends SimpleViewHolder {
    @BindView(R.id.textView)
    TextView textView;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
