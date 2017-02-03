package com.jaychang.demo.srv;

import android.os.Bundle;
import android.view.View;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.cell.NumberCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.srv.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DividerActivity extends BaseActivity {

  @BindView(R.id.linearVerRecyclerView)
  SimpleRecyclerView linearVerRecyclerView;
  @BindView(R.id.linearHorRecyclerView)
  SimpleRecyclerView linearHorRecyclerView;
  @BindView(R.id.gridRecyclerView)
  SimpleRecyclerView gridRecyclerView;
  @BindView(R.id.gridSequenceRecyclerView)
  SimpleRecyclerView gridSequenceRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_divider);
    ButterKnife.bind(this);
    bindBooks(linearVerRecyclerView);
    bindBooks(gridRecyclerView);
    bindBooks(gridSequenceRecyclerView);
    bindNumbers(linearHorRecyclerView);
  }

  @OnClick(R.id.linearVerRadioButton)
  void showLinearVerRecyclerView() {
    linearVerRecyclerView.setVisibility(View.VISIBLE);
    linearHorRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.GONE);
    gridSequenceRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.linearHorRadioButton)
  void showLinearHorRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.VISIBLE);
    gridRecyclerView.setVisibility(View.GONE);
    gridSequenceRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.gridRadioButton)
  void showGridRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.VISIBLE);
    gridSequenceRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.gridSequenceRadioButton)
  void showGridSequenceRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.GONE);
    gridSequenceRecyclerView.setVisibility(View.VISIBLE);
  }

  private void bindBooks(SimpleRecyclerView simpleRecyclerView) {
    List<Book> books = DataUtils.getBooks();
    List<BookCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

  private void bindNumbers(SimpleRecyclerView simpleRecyclerView) {
    List<NumberCell> cells = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      NumberCell cell = new NumberCell(i);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }


}
