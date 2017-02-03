package com.jaychang.demo.srv;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyStateViewActivity extends BaseActivity {

  @BindView(R.id.gridRecyclerView)
  SimpleRecyclerView recyclerView;
  @BindView(R.id.swipeRefreshLayout)
  SwipeRefreshLayout swipeRefreshLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_empty_view);
    ButterKnife.bind(this);

    init();
    loadBooksFromNetwork();
  }

  private void init() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        clearBooks();
        loadBooksFromNetwork();
      }
    });
  }

  @OnClick(R.id.addButton)
  void addCell() {
    int itemCount = recyclerView.getItemCount();
    Book book = DataUtils.newBook(itemCount);
    BookCell cell = new BookCell(book);
    recyclerView.addCell(cell);
  }

  @OnClick(R.id.removeButton)
  void removeAllCell() {
    if (recyclerView.isEmpty()) {
      return;
    }
    recyclerView.removeAllCells();
  }

  private void loadBooksFromNetwork() {
    swipeRefreshLayout.setRefreshing(true);
    DataUtils.getBooksAsync(this, new DataUtils.DataCallback() {
      @Override
      public void onSuccess(List<Book> books) {
        bindBooks(books);
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  private void clearBooks() {
    recyclerView.removeAllCells(false);
  }

  private void bindBooks(List<Book> books) {
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    recyclerView.addCells(cells);
  }

}
