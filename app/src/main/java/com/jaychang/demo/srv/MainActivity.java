package com.jaychang.demo.srv;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.basicUsageButton)
  void goToLayoutModePage() {
    startActivity(new Intent(this, BasicUsageActivity.class));
  }

  @OnClick(R.id.multiTypesButton)
  void goToMultiTypesPage() {
    startActivity(new Intent(this, MultiTypesActivity.class));
  }

  @OnClick(R.id.cellOperationsButton)
  void goToCellOperationsPage() {
    startActivity(new Intent(this, CellOperationsActivity.class));
  }

  @OnClick(R.id.dividerButton)
  void goToDividerPage() {
    startActivity(new Intent(this, DividerActivity.class));
  }

  @OnClick(R.id.spacingButton)
  void goToSpacingPage() {
    startActivity(new Intent(this, SpacingActivity.class));
  }

  @OnClick(R.id.emptyViewButton)
  void goToEmptyViewPage() {
    startActivity(new Intent(this, EmptyStateViewActivity.class));
  }

  @OnClick(R.id.sectionHeaderButton)
  void goToSectionHeaderPage() {
    startActivity(new Intent(this, SectionHeaderActivity.class));
  }

  @OnClick(R.id.loadMoreViewButton)
  void goToLoadMoreViewPage() {
    startActivity(new Intent(this, AutoLoadMoreActivity.class));
  }

  @OnClick(R.id.dragAndDropButton)
  void goToDragAndDropPage() {
    startActivity(new Intent(this, DragAndDropActivity.class));
  }

  @OnClick(R.id.swipeToDismissButton)
  void goToSwipeToDismissPage() {
    startActivity(new Intent(this, SwipeToDismissActivity.class));
  }

  @OnClick(R.id.snappyButton)
  void goToSnappyPage() {
    startActivity(new Intent(this, SnappyActivity.class));
  }

}
