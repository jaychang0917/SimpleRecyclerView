package com.jaychang.demo.srv;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;
import com.jaychang.srv.behavior.DragAndDropCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DragAndDropActivity extends BaseActivity {

  @BindView(R.id.linearRecyclerView)
  SimpleRecyclerView linearRecyclerView;
  @BindView(R.id.gridRecyclerView)
  SimpleRecyclerView gridRecyclerView;
  @BindView(R.id.resultView)
  TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drag_and_drop);
    ButterKnife.bind(this);
    init();
    bindBooks(linearRecyclerView, true);
    bindBooks(gridRecyclerView, false);
  }

  private void init() {
    DragAndDropCallback<Book> dragAndDropCallback = new DragAndDropCallback<Book>() {
      // Optional
      @Override
      public boolean enableDefaultRaiseEffect() {
        // return false if you manipulate custom drag effect in other 3 callbacks.
        return super.enableDefaultRaiseEffect();
      }

      // Optional
      @Override
      public void onCellDragStarted(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int position) {
        resultView.setText("Started dragging " + item + " at position " + position);
      }

      // Optional
      @Override
      public void onCellMoved(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int fromPosition, int toPosition) {
        resultView.setText("Moved " + item + " from position " + fromPosition + " to position " + toPosition);
      }

      @Override
      public void onCellDropped(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int fromPosition, int toPosition) {
        resultView.setText("Dragged " + item + " from position " + fromPosition + " to position " + toPosition);
      }

      @Override
      public void onCellDragCancelled(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int currentPosition) {
        resultView.setText("Cancelled dragging " + item + " at position " + currentPosition);
      }
    };

    linearRecyclerView.enableDragAndDrop(R.id.dragHandle, dragAndDropCallback);
    gridRecyclerView.enableDragAndDrop(dragAndDropCallback);
  }

  @OnClick(R.id.linearRadioButton)
  void showLinearRecyclerView() {
    linearRecyclerView.setVisibility(View.VISIBLE);
    gridRecyclerView.setVisibility(View.GONE);
    resultView.setText("");
  }

  @OnClick(R.id.gridRadioButton)
  void showGridRecyclerView() {
    linearRecyclerView.setVisibility(View.GONE);
    gridRecyclerView.setVisibility(View.VISIBLE);
    resultView.setText("");
  }

  private void bindBooks(SimpleRecyclerView simpleRecyclerView, boolean showHandle) {
    List<Book> books = DataUtils.getBooks();
    List<SimpleCell> cells = new ArrayList<>();

    for (Book book : books) {
      BookCell cell = new BookCell(book);
      cell.setShowHandle(showHandle);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

}
