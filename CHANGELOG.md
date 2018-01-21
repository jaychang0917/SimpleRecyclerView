## Change Log

### Version 1.2.8 (2018-01-21)
- Handle load more state automatically. No need to call `setLoadingMore()` manually, and the method is demoted to a private method now.
- Add `OnLoadMoreListener.shouldLoadMore()`.

### Version 1.2.7 (2017-12-09)
- Fix [Issue#34](https://github.com/jaychang0917/SimpleRecyclerView/issues/34)

### Version 1.2.6 (2017-09-16)
- Fix [Issue#31](https://github.com/jaychang0917/SimpleRecyclerView/issues/31)

### Version 1.2.3 (2017-09-09)
- Update to `com.android.support:recyclerview-v7:26.0.2`

### Version 1.2.2 (2017-09-06)
- Fix empty cell not being shown after call `removeAllCells()`

### Version 1.2.1 (2017-08-18)
- Remove `OnCellClickListener2` and `OnCellLongClickListener2`

### Version 1.2.0 (2017-08-16)
- `SimpleViewHolder` implements `LayoutContainer` to support view cache feature added in kotlin `1.1.4`

### Version 1.1.25 (2017-08-10)
- `SimpleCell.getItemId()` has default implementation

### Version 1.1.24 (2017-08-10)
- Bug fix

### Version 1.1.20 (2017-08-03)
- Add `@NonNull` annotation to avoid unnecessary unwrap in kotlin  
- Rename `SectionHeaderProviderAdapter` to `SimpleSectionHeaderProvider`

### Version 1.1.19 (2017-08-02)
- Fix attribute autocomplete not showing issue

### Version 1.1.18 (2017-07-05)
- Bug fix

### Version 1.1.17 (2017-06-05)
- Fix [Issue#27](https://github.com/jaychang0917/SimpleRecyclerView/issues/27)

### Version 1.1.16 (2017-04-28)
- Update `updateCells()` signature

### Version 1.1.11 (2017-04-03)
- Fix [Issue#24](https://github.com/jaychang0917/SimpleRecyclerView/issues/24)

### Version 1.1.10 (2017-03-11)
- Fix section header bug 

### Version 1.1.9 (2017-03-11)
- Fix [Issue#21](https://github.com/jaychang0917/SimpleRecyclerView/issues/21)

### Version 1.1.8 (2017-02-19)
- Expose `showEmptyStateView` and `hideEmptyStateView` methods
- Add `showEmptyStateView` attribute

### Version 1.1.7 (2017-02-16)
- Fix [Issue#15](https://github.com/jaychang0917/SimpleRecyclerView/issues/15)

### Version 1.1.6 (2017-02-15)
- Fix [Issue#8](https://github.com/jaychang0917/SimpleRecyclerView/issues/8)

### Version 1.1.5 (2017-02-14)
- Fix [Issue#10](https://github.com/jaychang0917/SimpleRecyclerView/issues/10) and disable item animator by default 

### Version 1.1.2 (2017-02-14)
- Fix [Issue#6](https://github.com/jaychang0917/SimpleRecyclerView/issues/6)

### Version 1.1.1 (2017-02-14)
- Fix [Issue#7](https://github.com/jaychang0917/SimpleRecyclerView/issues/7)

### Version 1.1.0 (2017-02-12)
- Add load more view

### Version 1.0.6 (2017-02-10)
- Update default cell listeners

### Version 1.0.5 (2017-02-10)
- Update `minSdkVersion` to 14

### Version 1.0.4 (2017-02-09)
- Fix [Issue#5](https://github.com/jaychang0917/SimpleRecyclerView/issues/5)

### Version 1.0.3 (2017-02-09)
- Fix [Issue#4](https://github.com/jaychang0917/SimpleRecyclerView/issues/4)

### Version 1.0.2 (2017-02-07)
- Fix incorrect divider for grid sequence mode issue.

### Version 1.0.1 (2017-02-06)
- Fix invalid viewholder adapter position issue.
