package com.jaychang.demo.srv;

import android.os.Bundle;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.srv.ScrollPosition;
import com.jaychang.srv.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CellOperationsActivity extends BaseActivity {

  @BindView(R.id.recyclerView)
  SimpleRecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cell_operations);
    ButterKnife.bind(this);
    bindBooks();
  }

  @OnClick(R.id.addButton)
  void add() {
//    int itemCount = recyclerView.getItemCount();
//    Book book = DataUtils.newBook(itemCount);
//    BookCell cell = new BookCell(book);
//    recyclerView.addCell(cell);

    recyclerView.smoothScrollToPosition(5, ScrollPosition.BOTTOM, false);
//    recyclerView.scrollToPosition(2);
  }

  @OnClick(R.id.addOrUpdateButton)
  void addOrUpdate() {
    Book book0 = DataUtils.getBook(0);
    book0.setTitle("(Updated) Book 0");
    Book book1 = DataUtils.getBook(1);
    book1.setTitle("(Updated) Book 1");
    Book newBook1 = DataUtils.newBook(recyclerView.getItemCount());
    Book newBook2 = DataUtils.newBook(recyclerView.getItemCount() + 1);
    List<BookCell> cells = new ArrayList<>();
    cells.add(new BookCell(book0));
    cells.add(new BookCell(book1));
    cells.add(new BookCell(newBook1));
    cells.add(new BookCell(newBook2));
    recyclerView.addOrUpdateCells(cells);
  }

  @OnClick(R.id.updateButton)
  void update() {
    if (recyclerView.isEmpty()) {
      return;
    }

    Book book1 = DataUtils.getBook(1);
    book1.setTitle("(Updated) Book 1");

    recyclerView.updateCell(1, book1);
  }

  @OnClick(R.id.removeButton)
  void remove() {
    if (recyclerView.isEmpty()) {
      return;
    }

    recyclerView.removeCell(recyclerView.getItemCount() - 1);
  }

  private void bindBooks() {
    List<Book> books = DataUtils.getAllBooks();
    List<BookCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    recyclerView.addCells(cells);
  }

}
