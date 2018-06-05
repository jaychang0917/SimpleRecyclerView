package com.jaychang.demo.srv;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.demo.srv.util.ToastUtils;
import com.jaychang.srv.OnLoadMoreListener;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class AutoLoadMoreActivity extends BaseActivity {

  @BindView(R.id.recyclerView)
  SimpleRecyclerView recyclerView;
  @BindView(R.id.loadMoreToTopCheckbox)
  CheckBox loadMoreToTopCheckbox;
  @BindView(R.id.thresholdTextView)
  TextView thresholdTextView;
  @BindView(R.id.thresholdSeekBar)
  SeekBar thresholdSeekBar;

  private boolean hasMoreData = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auto_load_more);
    ButterKnife.bind(this);
    init();
    bindBooks(DataUtils.getBooks());
  }

  @OnCheckedChanged(R.id.loadMoreToTopCheckbox)
  void onCheckboxChanged(CompoundButton button, boolean isChecked) {
    recyclerView.setLoadMoreToTop(isChecked);
  }

  private String formatThresholdMsg(int threshold) {
    return String.format(getString(R.string.auto_load_more_threshold), threshold);
  }

  private void init() {
    thresholdTextView.setText(formatThresholdMsg(0));
    thresholdSeekBar.setMax(10);
    thresholdSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        recyclerView.setAutoLoadMoreThreshold(progress);
        thresholdTextView.setText(formatThresholdMsg(progress));
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });

    recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
      @Override
      public boolean shouldLoadMore() {
        return hasMoreData;
      }

      @Override
      public void onLoadMore() {
        loadBooks();
      }
    });
  }

  private void loadBooks() {
    recyclerView.showLoadMoreView();
    DataUtils.getBooksAsync(this, new DataUtils.DataCallback() {
      @Override
      public void onSuccess(List<Book> books) {
        recyclerView.hideLoadMoreView();
        bindBooks(books);
        hasMoreData = false;
        ToastUtils.show(AutoLoadMoreActivity.this.getApplicationContext(), "Load more " + books.size() + " books.");
      }
    });
  }

  private void bindBooks(List<Book> books) {
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    if (recyclerView.isLoadMoreToTop()) {
      recyclerView.addCells(0, cells);
    } else {
      recyclerView.addCells(cells);
    }
  }

}
