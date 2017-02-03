package com.jaychang.demo.srv;

import android.os.Bundle;
import android.view.View;

import com.jaychang.demo.srv.cell.NumberCell;
import com.jaychang.srv.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnappyActivity extends BaseActivity {

  @BindView(R.id.centerRecyclerView)
  SimpleRecyclerView centerRecyclerView;
  @BindView(R.id.startRecyclerView)
  SimpleRecyclerView startRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_snappy);
    ButterKnife.bind(this);
    bindNumber(centerRecyclerView);
    bindNumber(startRecyclerView);
  }

  @OnClick(R.id.centerRadioButton)
  void showCenterRecyclerView() {
    centerRecyclerView.setVisibility(View.VISIBLE);
    startRecyclerView.setVisibility(View.GONE);
  }

  @OnClick(R.id.startRadioButton)
  void showStartRecyclerView() {
    centerRecyclerView.setVisibility(View.GONE);
    startRecyclerView.setVisibility(View.VISIBLE);
  }

  private void bindNumber(SimpleRecyclerView simpleRecyclerView) {
    List<NumberCell> cells = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      NumberCell cell = new NumberCell(i);
      cells.add(cell);
    }

    simpleRecyclerView.addCells(cells);
  }

}
