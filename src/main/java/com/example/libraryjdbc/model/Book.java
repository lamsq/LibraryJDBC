package com.example.libraryjdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("books")
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.year = publicationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        this.title = t;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String a) {
        this.author = a;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int y) {
        this.year = y;
    }
}

