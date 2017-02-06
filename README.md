# SimpleRecyclerView
[![Release](https://jitpack.io/v/jaychang0917/SimpleRecyclerView.svg)](https://jitpack.io/#jaychang0917/SimpleRecyclerView)
[![Release](http://img.shields.io/badge/Android%20Weekly-%23243-2CB3E5.svg?style=flat)](http://androidweekly.net/issues/issue-243)

A RecyclerView extension for building list more easily.

<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/basic_usage.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/multi_types.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/cell_ops.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/divider.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/spacing.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/empty_state.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/section_header.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/loadmore.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/drag.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/swipe.gif" width="160" height="280">
<img src="https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/snappy.gif" width="160" height="280">

##Table of Contents
- [Basic Usage](#basic_usage) 
- [Multiple Types](#multi_types)
- [Cell Operations](#cell_ops) 
- [Divider](#divider)
- [Spacing](#spacing)
- [Empty State View](#empty_view) 
- [Section Header](#section_header) 
- [Auto Load More](#auto_load_more) 
- [Drag & Drop](#drag_drop)
- [Swipe To Dismiss](#swipe_dismiss)
- [Snappy](#snappy)
- [References](#refs)
 - [Attributes](#attr)
 - [Cell Operations](#cell_ops_list)

##Sample Project
[Sample apk](https://github.com/jaychang0917/SimpleRecyclerView/blob/master/art/SimpleRecyclerView_1_0_0.apk)

##Installation
In your project level build.gradle :

```java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

In your app level build.gradle :

```java
dependencies {
    compile 'com.github.jaychang0917:SimpleRecyclerView:1.0.1'
}
```

---

##<a name=basic_usage>Basic Usage</a>
Basically, there are three steps to build your list.
####1. Configure the SimpleRecyclerView
>[Full attributes list](#attr)

```xml
<com.jaychang.srv.SimpleRecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:srv_layoutMode="linearVertical|linearHorizontal|grid" 
    app:srv_gridSpanCount="integer"
    app:srv_gridSpanSequence="integer string (e.g. 2233)"
    ... />
```
####2. Define the cell by extending `SimpleCell<T,VH>`
```java
/**
 * Accept two type arguments,
 * 1st is the data model this cell represents, 2nd is the view holder.
 * */
public class BookCell extends SimpleCell<Book, BookCell.ViewHolder> {

  /** 
   * Mandatory constructor, pass your data model as argument 
   * */
  public BookCell(Book item) {
    super(item);
  }

  /**
   * Define the layout resource of this cell
   * */
  @Override
  protected int getLayoutRes() {
    return R.layout.cell_book;
  }

  @NonNull
  @Override
  protected ViewHolder onCreateViewHolder(ViewGroup parent, View cellView) {
    return new ViewHolder(cellView);
  }

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, List<Object> payloads) {
    holder.textView.setText(getItem().getTitle());
  }

  /**
   * The unique identifier of your data model.
   * If you use updateCell() or addOrUpdateCell(), you must return a valid
   * unique id. If you're not going to use those operations, you can simply
   * return 0.
   * */
  @Override
  protected long getItemId() {
    return getItem().getId();
  }

  /**
   * Define your view holder, which must extend SimpleViewHolder.
   * */
  static class ViewHolder extends SimpleViewHolder {
    @BindView(R.id.textView)
    TextView textView;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
```
####3. Create cell(s) and add them to the SimpleRecyclerView
```java
List<Book> books = DataUtils.getBooks();
List<BookCell> cells = new ArrayList<>();

for (Book book : books) {
  BookCell cell = new BookCell(book);
  // There are two default cell listeners: OnCellClickListener<T> and OnCellLongClickListener<T>
  cell.setOnCellClickListener(new SimpleCell.OnCellClickListener<Book>() {
    @Override
    public void onCellClicked(Book item) {
      ...
    }
  });
  cell.setOnCellLongClickListener(new SimpleCell.OnCellLongClickListener<Book>() {
    @Override
    public void onCellLongClicked(Book item) {
      ...
    }
  });
  cells.add(cell);
}

simpleRecyclerView.addCells(cells);
```
Then, there you go!

##<a name=multi_types>Multiple Types</a>
SimpleRecyclerView supports multiple cell types. You can add different type of cells to it just like adding different type of objects to a list. The SimpleRecyclerView will handle the rest for you.
```java
List<Book> books = DataUtils.getBooks();
List<Ad> ads = DataUtils.getAds();
List<SimpleCell> cells = new ArrayList<>();

for (Book book : books) {
  BookCell cell = new BookCell(book);
  cells.add(cell);
}

for (Ad ad : ads) {
  BookAdCell cell = new BookAdCell(ad);
  int position = (ads.indexOf(ad) + 1) * 3;
  // take up full row
  cell.setSpanSize(simpleRecyclerView.getGridSpanCount());
  cells.add(position, cell);
}

simpleRecyclerView.addCells(cells);
```

##<a name=cell_ops>Cell Operations</a>
SimpleRecyclerView provides basic CRUD cell operations. 
>[Full cell operations list](#cell_ops_list)

It is common that loading cache data first and then fetch new data from network to update the list. The library provides `addOrUpdateCell()` and `addOrUpdateCells()` operation to achieve that. The cells will not be updated (i.e. receive `onBindViewHolder()` callback) if their bounded data models are the same, otherwsie they will be added to the end of list. 
To enable this feature, the cells must be implemented `Updatable` interface.
```java
public interface Updatable<T> {

  boolean areContentsTheSame(T newItem);

  Object getChangePayload(T newItem);

}
```
```java
public class BookCell extends SimpleCell<Book, BookCell.ViewHolder>
  implements Updatable<Book> {

  ...

  @Override
  protected void onBindViewHolder(ViewHolder holder, int position, Context context, Object payload) {
    if (payload != null) {
      // partial update
      if (payload instanceof Bundle) {
        Bundle bundle = ((Bundle) payload);
        for (String key : bundle.keySet()) {
          if (KEY_TITLE.equals(key)) {
            holder.textView.setText(bundle.getString(key));
          }
        }
      }
      return;
    }
    ...
  }
  
  /**
   * If the titles of books are same, no need to update the cell, onBindViewHolder() will not be called.
   */
  @Override
  public boolean areContentsTheSame(Book newItem) {
    return getItem().getTitle().equals(newItem.getTitle());
  }

  /**
   * If getItem() is the same as newItem (i.e. their return value of getItemId() are the same)
   * and areContentsTheSame()  return false, then the cell need to be updated,
   * onBindViewHolder() will be called with this payload object.
   * */
  @Override
  public Object getChangePayload(Book newItem) {
    Bundle bundle = new Bundle();
    bundle.putString(KEY_TITLE, newItem.getTitle());
    return bundle;
  }
  
  ...
}
```

##<a name=divider>Divider</a>
```xml
<com.jaychang.srv.SimpleRecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:srv_layoutMode="linearVertical" 
    app:srv_showDivider="true|false"
    app:srv_showLastDivider="true|false"
    app:srv_dividerOrientation="vertical|horizontal|both"
    app:srv_dividerColor="@color/your_color"
    app:srv_dividerPaddingLeft="dp"
    app:srv_dividerPaddingRight="dp"
    app:srv_dividerPaddingTop="dp"
    app:srv_dividerPaddingBottom="dp" />
```

##<a name=spacing>Spacing</a>
```xml
<com.jaychang.srv.SimpleRecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:srv_layoutMode="linearVertical" 
    app:srv_spacing="dp"
    app:srv_verticalSpacing="dp"
    app:srv_horizontalSpacing="dp"
    app:srv_isSpacingIncludeEdge="true|false" />
```

##<a name=empty_view>Empty State View</a>
The empty state view will be shown automatically when there is no data. 
```xml
<com.jaychang.srv.SimpleRecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:srv_layoutMode="linearVertical" 
    app:srv_emptyStateView="@layout/view_empty_state" />
```

##<a name=section_header>Section Header</a>
You can group cells together by providing a `SectionHeaderProvider<T>` to `setSectionHeader(provider)`. A shorthand method `SectionHeaderProviderAdapter<T>` is also provided. 
```java
SectionHeaderProvider<Book> sectionHeaderProvider = new SectionHeaderProviderAdapter<Book>() {
  // Your section header view here
  @NonNull
  @Override
  public View getSectionHeaderView(Book item, int position) {
    View view = LayoutInflater.from(SectionHeaderActivity.this).inflate(R.layout.view_section_header, null, false);
    TextView textView = (TextView) view.findViewById(R.id.textView);
    textView.setText(String.format(getString(R.string.category), item.getCategoryName()));
    return view;
  }
  // Your grouping logic here
  @Override
  public boolean isSameSection(Book item, Book nextItem) {
    return item.getCategoryId() == nextItem.getCategoryId();
  }

  // Optional, whether the header is sticky, default false
  @Override
  public boolean isSticky() {
    return stickyCheckbox.isChecked();
  }

  // Optional, top margin of each section header
  @Override
  public int getSectionHeaderMarginTop(Book item, int position) {
    return position == 0 ? 0 : Utils.dp2px(SectionHeaderActivity.this, 16);
  }
};

simpleRecyclerView.setSectionHeader(sectionHeaderProvider);
```

##<a name=auto_load_more>Auto Load More</a>
```java
// if the total beneath hidden cells count <= 4, onLoadMore() will be called. Default threshold is 0.
simpleRecyclerView.setAutoLoadMoreThreshold(4);

simpleRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
  @Override
  public void onLoadMore(SimpleRecyclerView simpleRecyclerView) {
    loadBooks();
  }
});
```
If you are going to build a list like chatting, i.e. the cells add to top of the list, you should set `setLoadMoreToTop(true)`. This instructs the SimpleRecyclerView to check threshold for the top hidden cells.
```java
simpleRecyclerView.setLoadMoreToTop(true);
```

##<a name=drag_drop>Drag & Drop</a>
You can enable drag and drop by providing a `DragAndDropCallback<T>` to `enableDragAndDrop(callback)` or `enableDragAndDrop(dragHandleResId, callback)`, the latter one accepts a drag handle view resource id, only pressing this view will trigger drag behavior. Default long press to trigger drag behavior. Also, all callback methods of `DragAndDropCallback<T>` are optional.
```java
DragAndDropCallback<Book> dragAndDropCallback = new DragAndDropCallback<Book>() {
  // Optional, return false if you manipulate custom drag effect in the rest of callbacks.
  @Override
  public boolean enableDefaultRaiseEffect() {
    // default return true
    return super.enableDefaultRaiseEffect();
  }

  // Optional
  @Override
  public void onCellDragStarted(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int position) {
    resultView.setText("Started dragging " + item);
  }

  // Optional
  @Override
  public void onCellMoved(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int fromPosition, int toPosition){
    resultView.setText("Moved " + item + " from " + fromPosition + " to " + toPosition);
  }

  // Optional
  @Override
  public void onCellDropped(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int fromPosition, int toPosition) {
    resultView.setText("Dragged " + item + " from " + fromPosition + " to " + toPosition);
  }

  // Optional
  @Override
  public void onCellDragCancelled(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int currentPosition) {
    resultView.setText("Cancelled dragging " + item);
  }
};

simpleRecyclerView.enableDragAndDrop(R.id.dragHandle, dragAndDropCallback); 
// or
simpleRecyclerView.enableDragAndDrop(dragAndDropCallback);
```

##<a name=swipe_dismiss>Swipe To Dismiss</a>
You can enable swipe to dismiss by providing a `SwipeToDismissCallback<T>` to `enableSwipeToDismiss(callback, swipeDirections)`. All callback methods of `SwipeToDismissCallback<T>` are optional.
```java
SwipeToDismissCallback<Book> swipeToDismissCallback = new SwipeToDismissCallback<Book>() {
  // Optional, return false if you manipulate custom swipe effect in the rest of callbacks.
  @Override
  public boolean enableDefaultFadeOutEffect() {
    // default return true
    return super.enableDefaultFadeOutEffect();
  }

  // Optional
  @Override
  public void onCellSwiping(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int position, Canvas canvas, float dX, float dY, boolean isControlledByUser) {
    resultView.setText("Item " + item + " is swiping.");
  }

  // Optional
  @Override
  public void onCellSettled(SimpleRecyclerView simpleRecyclerView, View itemView, Book item, int position) {
    resultView.setText("Item " + item + " is settled.");
  }

  // Optional
  @Override
  public void onCellDismissed(SimpleRecyclerView simpleRecyclerView, Book item, int position) {
    resultView.setText("Item: " + item + " is dismissed.");
  }
};

// enable swipe left or right to dismiss
simpleRecyclerView.enableSwipeToDismiss(swipeToDismissCallback, LEFT, RIGHT);
```

##<a name=snappy>Snappy</a>
```xml
<com.jaychang.srv.SimpleRecyclerView
   android:id="@+id/recyclerView"
   android:layout_width="match_parent"
   android:layout_height="wrap_content"
   app:srv_layoutMode="linearHorizontal"
   app:srv_snappy="true|false"
   app:srv_snap_alignment="center|start" />
```
---

##<a name=refs>Reference</a>

###<a name=attr>Attributes</a>

>All attrs have coressponding java method.

| attr                         | Description                                     |
|  --------------------------- | ------------------------------------------------|
| srv_layoutMode | Set which layout mode to be used. Support `linearVertical`, `linearHorizontal` and `grid`. Default `linearVertical` |
| srv_gridSpanCount | Set span count for `grid` layout mode |
| srv_gridSpanSequence | Set span sequence for `grid` layout mode |
| srv_spacing | Cell spacing, if `srv_verticalSpacing` or `srv_horizontalSpacing` are also set, this takes precedence over them |
| srv_verticalSpacing | Vertical cell spacing for `grid` layout mode |
| srv_horizontalSpacing | Horizontal cell spacing for `grid` layout mode |
| srv_isSpacingIncludeEdge | If set to true, spacing will be included in the edges of recyclerview. i.e. top & bottom for linear, all edges for grid. Default `false`  | 
| srv_showDivider | If set to true, add dividers between cells. Default `false` |
| srv_showLastDivider | If set to true, show last divider. Default `false` |
| srv_dividerOrientation | Divider orientation, works with `grid` layout mode. Support `vertical`, `horizontal` and `both`. Default `both` |
| srv_dividerColor | Divider color. Default `#e0e0e0` |
| srv_dividerPaddingLeft | Divider padding left |
| srv_dividerPaddingRight | Divider padding right |
| srv_dividerPaddingTop | Divider padding top |
| srv_dividerPaddingBottom | Divider padding bottom |
| srv_emptyView | Layout resource of empty state view to be shown when there is no data. |
| srv_snappy | If set to true, snappy mode is enabled. Default `false` |
| srv_snap_alignment | Snap alignment. Support `center` and `start` |

###<a name=cell_ops_list>Cell Operations</a>
| Operation                         | Remark                                |
|  --------------------------------------------- | ------------------------------------------------|
| addCell(SimpleCell cell)                       | Add the cell to the end of list|
| addCell(int atPosition, SimpleCell cell) | Add the cell to a specific position of list |
| addCells(List<? extends SimpleCell> cells) | Add the cells to the end of list |
| addCells(SimpleCell... cells) | Same as above |
| addCells(int fromPosition, List<? extends SimpleCell> cells) | Add the cells to list from a specific position |
| addCells(int fromPosition, SimpleCell... cells) | Same as above |
| addOrUpdateCell(T cell) | Add or udpate the cell. The cell should be implemented `Updatable` interface |
| addOrUpdateCells(List<T> cells) | Add or update the cells. The cells should be implemented `Updatable` interface |
| addOrUpdateCells(T... cells) | Same as above |
| removeCell(SimpleCell cell) | Remove the cell from list |
| removeCell(int atPosition) | Remove the cell at specific position |
| removeCells(int fromPosition, int toPosition) | Remove range of cells `[fromPosition..toPosition]` from list |
| removeCells(int fromPosition) | Remove range of cells `[fromPosition..end]` from list |
| removeAllCells() | Remove all cells from list |
| removeAllCells(boolean showEmptyStateView) | Remove all cells from list and tell the SimpleRecyclerView if need to show empty state view. Default `true` |
| updateCell(int atPosition, Object payload) | Update the specific cell with payload. |
| updateCells(int fromPosition, int toPosition, List<Object> payloads) | Update the range of cells `[fromPosition..toPosition]` with payloads.｜
| getCell(int atPosition) | Get the cell at specific postion |
| getCells(int fromPosition, int toPosition) | Get a range of cells `[fromPosition..toPosition]` |
| getAllCells() | Get all cells |



##License
```
Copyright 2017 Jay Chang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
