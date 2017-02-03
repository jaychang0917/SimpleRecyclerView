package com.jaychang.demo.srv.util;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.IntRange;

import com.jaychang.demo.srv.model.Ad;
import com.jaychang.demo.srv.model.Book;
import com.jaychang.demo.srv.model.Category;

import java.util.ArrayList;
import java.util.List;

public final class DataUtils {

  public interface DataCallback {
    void onSuccess(List<Book> books);
  }

  public static List<Book> getBooks() {
    List<Book> books = getAllBooks();
    return books.subList(0, 9);
  }

  public static List<Book> getBooks(@IntRange(from = 0, to = 30) int count) {
    List<Book> books = getAllBooks();
    return books.subList(0, count);
  }

  public static List<Book> getAllBooks() {
    List<Book> books = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
      books.add(newBook(i));
    }
    return books;
  }

  public static Book getBook(int id) {
    List<Book> books = getAllBooks();
    return books.get(id);
  }

  public static void getBooksAsync(final Activity activity, final DataCallback callback) {
    AsyncTask.execute(new Runnable() {
      @Override
      public void run() {
        SystemClock.sleep(500);
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            callback.onSuccess(getBooks());
          }
        });
      }
    });
  }

  public static Book newBook(long id) {
    String title = "Book " + id;
    String author = "Foo " + id;
    long categoryId = id / 3;
    Category category = new Category(categoryId, String.valueOf(categoryId));
    return new Book(id, title, author, category);
  }

  public static List<Ad> getAds() {
    List<Ad> ads = new ArrayList<>();
    ads.add(new Ad(0, "Ad 0"));
    ads.add(new Ad(1, "Ad 1"));
    return ads;
  }

}
