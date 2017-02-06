package com.jaychang.demo.srv;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.cell.NumberCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;
import com.jaychang.srv.behavior.SwipeToDismissCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jaychang.srv.behavior.SwipeDirection.DOWN;
import static com.jaychang.srv.behavior.SwipeDirection.LEFT;
import static com.jaychang.srv.behavior.SwipeDirection.RIGHT;
import static com.jaychang.srv.behavior.SwipeDirection.UP;

public class SwipeToDismissActivity extends BaseActivity {

  @BindView(R.id.linearVerRecyclerView)
  SimpleRecyclerView linearVerRecyclerView;
  @BindView(R.id.linearHorRecyclerView)
  SimpleRecyclerView linearHorRecyclerView;
  @BindView(R.id.resultView)
  TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_swipe_to_dismiss);
    ButterKnife.bind(this);
    init();
    bindBooks(linearVerRecyclerView);
    bindNumbers(linearHorRecyclerView);
  }

  private void init() {
    SwipeToDismissCallback<Book> bookCallback = new SwipeToDismissCallback<Book>() {
      // Optional
      @Override
      public boolean enableDefaultFadeOutEffect() {
        // return false if you manipulate custom swipe effect in other 3 callbacks.
        return super.enableDefaultFadeOutEffect();
      }

      // Optional
      @Override
      public void onCellSwiping(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
        resultView.setText("Item " + item + " at position " + position + " is swiping.");
      }

      // Optional
      @Override
      public void onCellSettled(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int position) {
        resultView.setText("Item " + item  + " at position " + position + " is settled.");
      }

      @Override
      public void onCellDismissed(SimpleRecyclerView simpleRecyclerView, Book item, int position) {
        resultView.setText("Item: " + item  + " at position " + position + " is dismissed.");
      }
    };

    SwipeToDismissCallback<Integer> numberCallback = new SwipeToDismissCallback<Integer>() {
      // Optional
      @Override
      public boolean enableDefaultFadeOutEffect() {
        // return false if you manipulate custom swipe effect in other 3 callbacks.
        return super.enableDefaultFadeOutEffect();
      }

      // Optional
      @Override
      public void onCellSwiping(SimpleRecyclerView simpleRecyclerView, View itemView, Integer item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
        resultView.setText("Item " + item + " at position " + position + " is swiping.");
      }

      // Optional
      @Override
      public void onCellSettled(SimpleRecyclerView simpleRecyclerView, View itemView, Integer item, int position) {
        resultView.setText("Item " + item  + " at position " + position + " is settled.");
      }

      @Override
      public void onCellDismissed(SimpleRecyclerView simpleRecyclerView, Integer item, int position) {
        resultView.setText("Item: " + item  + " at position " + position + " is dismissed.");
      }
    };

    linearVerRecyclerView.enableSwipeToDismiss(bookCallback, LEFT, RIGHT);
    linearHorRecyclerView.enableSwipeToDismiss(numberCallback, UP, DOWN);
  }

  @OnClick(R.id.linearVerRadioButton)
  void showLinearVerRecyclerView() {
    linearVerRecyclerView.setVisibility(View.VISIBLE);
    linearHorRecyclerView.setVisibility(View.GONE);
    resultView.setText("");
  }

  @OnClick(R.id.linearHorRadioButton)
  void showLinearHorRecyclerView() {
    linearVerRecyclerView.setVisibility(View.GONE);
    linearHorRecyclerView.setVisibility(View.VISIBLE);
    resultView.setText("");
  }

  private void bindBooks(SimpleRecyclerView simpleRecyclerView) {
    List<Book> books = DataUtils.getBooks();
    List<SimpleCell> cells = new ArrayList<>();

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
