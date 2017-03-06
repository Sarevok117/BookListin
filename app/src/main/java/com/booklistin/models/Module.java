package com.booklistin.models;

import java.util.List;

/**
 * Created by Varkiil on 06/03/2017.
 */

public class Module {
    private String type;
    private String title;
    private String seeAll;
    private List<Book> books;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeeAll() {
        return seeAll;
    }

    public void setSeeAll(String seeAll) {
        this.seeAll = seeAll;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
