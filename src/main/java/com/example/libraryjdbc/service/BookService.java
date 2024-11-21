package com.example.libraryjdbc.service;

import com.example.libraryjdbc.model.Book;
import com.example.libraryjdbc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class BookService {

    private final BookRepository br;

    @Autowired
    public BookService(BookRepository br) {
        this.br = br;
    }

    public Book createBook(Book b) {
        return br.createBook(b);
    }

    public Optional<Book> getBookById(Long id) {
        return br.getBookById(id);
    }

    public Optional<Book> updateBook(Long id, Book b) {
        return br.updateBook(id, b);
    }

    public boolean deleteBook(Long id) {
        return br.deleteBook(id);
    }

    public List<Book> getAllBooks() {
        return br.getAllBooks();
    }
}
