package com.example.libraryjdbc.service;

import com.example.libraryjdbc.model.Book;
import com.example.libraryjdbc.repository.BookRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;

class BookServiceTest {

    private final BookRepository br = mock(BookRepository.class);
    private final BookService bs = new BookService(br);

    @Test
    void getReturnBookById() {
        Book book = new Book("Title", "Author", 2000);
        book.setId(1L);
        when(br.getBookById(1L)).thenReturn(Optional.of(book));
        Optional<Book> result = bs.getBookById(1L);

        assertTrue(result.isPresent());
        assertEquals("Title", result.get().getTitle());
    }
}
