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

public class BookAdCell extends SimpleCell<Ad, BookAdCell.ViewHolder> {

  public BookAdCell(Ad ad) {
    super(ad);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.cell_book_ad;
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

  @Override
  protected long getItemId() {
    return getItem().getId();
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
