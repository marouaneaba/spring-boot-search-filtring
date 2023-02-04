package com.marouane.data.filtringspecification;

import com.marouane.data.filtringspecification.book.application.controller.BookController;
import com.marouane.data.filtringspecification.book.domain.Book;
import com.marouane.data.filtringspecification.book.domain.BookRepository;
import com.marouane.data.filtringspecification.book.domain.BookType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;


    @Test
    void shouldReturn200Status_andBookList() throws Exception {
        // Given
        Book book = new Book("myBook", "123456789", BookType.PDF, "USA", 9, ZonedDateTime.now());
        Mockito.when(bookRepository.searchBook(any(), any())).thenReturn(List.of(book));

        // When
        mockMvc.perform(
                        get("/v1/books")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
    }

}
