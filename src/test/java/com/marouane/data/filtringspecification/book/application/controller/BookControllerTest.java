package com.marouane.data.filtringspecification.book.application.controller;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = BookController.class)
class BookControllerTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void shouldReturn200Status_andBookList() throws Exception {
        // Given
        Book book = new Book("myBook", "123456789", BookType.PDF, "USA", ZonedDateTime.now());
        Mockito.doReturn(List.of(book)).when(bookService.fetchBook());

        // When
        mockMvc.perform(
                        get("/books")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", greaterThanOrEqualTo(0)));
    }


    @Test
    void shouldReturn200Status_andBookById() throws Exception {
        // Given
        Book book = new Book("myBook", "123456789", BookType.PDF, "USA", ZonedDateTime.now());
        Mockito.doReturn(List.of(book)).when(bookService.fetchBook());

        // When
        mockMvc.perform(
                        get("/books/1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo(1)))
                .andExpect(content().json("""
                        [
                            {
                                "name": "",
                                "isbn": "",
                                "type": "",
                                "country": "",
                                "createDate": ""
                            }
                        ]
                        """));
    }*/
}
