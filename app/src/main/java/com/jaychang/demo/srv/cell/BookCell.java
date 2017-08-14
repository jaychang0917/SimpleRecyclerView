package com.jaychang.demo.srv.cell;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaychang.demo.srv.R;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;
import com.jaychang.srv.Updatable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookCell extends SimpleCell<Book, BookCell.ViewHolder>
  implements Updatable<Book> {

  private static final String KEY_TITLE = "KEY_TITLE";
  private boolean showHandle;

  public BookCell(Book item) {
    super(item);
  }

  @Override
  protected int getLayoutRes() {
    return R.layout.cell_book;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new ViewHolder(cellView);
  }

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
    if (payload != null) {
      // payload from updateCell()
      if (payload instanceof Book) {
        holder.textView.setText(((Book) payload).getTitle());
      }
      // payloads from updateCells()
      if (payload instanceof ArrayList) {
        List<Book> payloads = ((ArrayList<Book>) payload);
        holder.textView.setText(payloads.get(position).getTitle());
      }
      // payload from addOrUpdate()
      if (payload instanceof Bundle) {
        Bundle bundle = ((Bundle) payload);
        for (String key : bundle.keySet()) {
          if (KEY_TITLE.equals(key)) {
            holder.textView.setText(bundle.getString(key));
          }
        }
      }
      return;
    }

    holder.textView.setText(getItem().getTitle());

    if (showHandle) {
      holder.dragHandle.setVisibility(View.VISIBLE);
    } else {
      holder.dragHandle.setVisibility(View.GONE);
    }
  }

  // Optional
  @Override
  protected void onUnbindViewHolder(ViewHolder holder) {
    // do your cleaning jobs here when the item view is recycled.
  }

  public void setShowHandle(boolean showHandle) {
    this.showHandle = showHandle;
  }

  /**
   * If the titles of books are same, no need to update the cell, onBindViewHolder() will not be called.
   */
  @Override
  public boolean areContentsTheSame(Book newItem) {
    return getItem().getTitle().equals(newItem.getTitle());
  }

  /**
   * If getItem() is the same as newItem (i.e. their return value of getItemId() are the same)
   * and areContentsTheSame()  return false, then the cell need to be updated,
   * onBindViewHolder() will be called with this payload object.
   */
  @Override
  public Object getChangePayload(Book newItem) {
    Bundle bundle = new Bundle();
    bundle.putString(KEY_TITLE, newItem.getTitle());
    return bundle;
  }

  public static class ViewHolder extends SimpleViewHolder {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.dragHandle)
    ImageView dragHandle;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
