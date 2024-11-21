package com.example.libraryjdbc.repository;

import com.example.libraryjdbc.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@JdbcTest
class BookRepositoryJdbcTemplateTest {

    private BookRepository br;
    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    void setUp() {
        br = new BookRepository(jdbc);
        jdbc.execute("create table books (id bigint auto_increment primary key, title varchar(255), author varchar(255), publication_year int)");
    }

    @Test
    void createSaveBook() {
        Book b = new Book("TestBook", "Author", 1999);
        br.createBook(b);

        Optional<Book> result = br.getBookById(1L);
        assertTrue(result.isPresent());
        assertEquals("TestBook", result.get().getTitle());
    }
}
