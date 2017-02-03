package com.jaychang.demo.srv.model;

import android.graphics.Color;

import java.util.Random;

public class Ad {

  private long id;
  private String title;
  private int color;

  public Ad(long id, String title) {
    this.id = id;
    this.title = title;
    this.color = generateColor();
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getColor() {
    return color;
  }

  private int generateColor() {
    Random rnd = new Random();
    return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
  }

}
