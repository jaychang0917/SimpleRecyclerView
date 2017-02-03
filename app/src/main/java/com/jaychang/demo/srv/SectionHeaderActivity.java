package com.jaychang.demo.srv;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jaychang.demo.srv.cell.BookCell;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.util.DataUtils;
import com.jaychang.demo.srv.util.Utils;
import com.jaychang.srv.SimpleRecyclerView;
import com.jaychang.srv.decoration.SectionHeaderProviderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class SectionHeaderActivity extends BaseActivity {

  @BindView(R.id.recyclerView)
  SimpleRecyclerView recyclerView;
  @BindView(R.id.stickyCheckbox)
  CheckBox stickyCheckbox;
  @BindView(R.id.marginTopCheckbox)
  CheckBox marginTopCheckbox;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section_header);
    ButterKnife.bind(this);
    init();
    bindBooks();
  }

  private void init() {
    recyclerView.setSectionHeader(new SectionHeaderProviderAdapter<Book>() {
      @NonNull
      @Override
      public View getSectionHeaderView(Book item, int position) {
        View view = LayoutInflater.from(SectionHeaderActivity.this).inflate(R.layout.view_section_header, null, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(String.format(getString(R.string.category), item.getCategoryName()));
        return view;
      }

      @Override
      public boolean isSameSection(Book item, Book nextItem) {
        return item.getCategoryId() == nextItem.getCategoryId();
      }

      // optional
      @Override
      public boolean isSticky() {
        return stickyCheckbox.isChecked();
      }

      // optional
      @Override
      public int getSectionHeaderMarginTop(Book item, int position) {
        if (!marginTopCheckbox.isChecked()) {
          return 0;
        }
        return position == 0 ? 0 : Utils.dp2px(SectionHeaderActivity.this, 16);
      }
    });
  }

  @OnCheckedChanged(R.id.marginTopCheckbox)
  void onSectionHeaderMarginTopChanged(CompoundButton button, boolean isChecked) {
    recyclerView.removeAllCells();
    bindBooks();
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
