package com.jaychang.demo.srv.model;

public class Book {

  private long id;
  private String title;
  private String author;
  private Category category;

  public Book(long id, String title, String author, Category category) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.category = category;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public long getCategoryId() {
    return category.getId();
  }

  public String getCategoryName() {
    return category.getName();
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }

}
