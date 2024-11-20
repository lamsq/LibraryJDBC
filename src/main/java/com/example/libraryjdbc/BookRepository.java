package com.example.libraryjdbc;

import com.example.libraryjdbc.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.*;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book createBook(Book book) {
        String sql = "insert into books (title, author, publication_year) values (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getYear());
        return book;
    }

    public Optional<Book> getBookById(Long id) {
        String sql = "select * from books where id = ?";
        List<Book> books = jdbcTemplate.query(sql, new Object[]{id}, new BookRowMapper());
        return books.stream().findFirst();
    }

    public Optional<Book> updateBook(Long id, Book b) {
        String sql = "update books set title = ?, author = ?, publication_year = ? where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, b.getTitle(), b.getAuthor(), b.getYear(), id);
        return rowsAffected > 0 ? Optional.of(b) : Optional.empty();
    }

    public boolean deleteBook(Long id) {
        String sql = "delete from books where id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    public List<Book> getAllBooks() {
        String sql = "select * from books";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book b = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("year")
            );
            b.setId(rs.getLong("id"));
            return b;
        }
    }
}
