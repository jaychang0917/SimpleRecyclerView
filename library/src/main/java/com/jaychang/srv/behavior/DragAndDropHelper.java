package com.jaychang.srv.behavior;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class DragAndDropHelper extends ItemTouchHelper
  implements OnStartDragListener {

  private int dragHandleId;

  private DragAndDropHelper(Callback callback) {
    super(callback);
  }

  public static DragAndDropHelper create(OnItemMoveListener callback,
                                         DragAndDropOptions options) {
    DragAndDropItemCallback simpleCallback = new DragAndDropItemCallback(callback, options);
    DragAndDropHelper helper = new DragAndDropHelper(simpleCallback);
    helper.dragHandleId = options.getDragHandleId();
    return helper;
  }

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    startDrag(viewHolder);
  }

  public int getDragHandleId() {
    return dragHandleId;
  }

}
