package com.jaychang.demo.srv.cell;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaychang.demo.srv.R;
import com.jaychang.demo.srv.model.Ad;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberAdCell extends SimpleCell<Ad, NumberAdCell.ViewHolder> {

  public NumberAdCell(Ad ad) {
    super(ad);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.cell_num_ad;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new ViewHolder(cellView);
  }

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
    holder.textView.setText(getItem().getTitle());
    holder.itemView.setBackgroundColor(getItem().getColor());
  }

  @Override
  protected void onUnbindViewHolder(ViewHolder holder) {
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
