package com.jaychang.demo.srv.model;

public class Category {

  private long id;
  private String name;

  public Category(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
