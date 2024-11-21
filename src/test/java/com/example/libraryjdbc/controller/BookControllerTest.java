package com.example.libraryjdbc.controller;

import com.example.libraryjdbc.model.Book;
import com.example.libraryjdbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import java.util.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private BookService bs;

    @Test
    void getBookById() throws Exception {
        Book b = new Book("Book", "Author", 2024);
        b.setId(1L);
        when(bs.getBookById(1L)).thenReturn(Optional.of(b));

        mock.perform(get("/books/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book"))
                .andExpect(jsonPath("$.author").value("Author"));
    }

    @Test
    void createBook() throws Exception {
        Book b = new Book("New Book", "New Author", 2024);
        b.setId(2L);
        when(bs.createBook(any(Book.class))).thenReturn(b);

        mock.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("{\"title\": \"New book\", \"author\": \"New author\", \"publicationYear\": 2024}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("New book"));
    }
}
