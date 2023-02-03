package com.marouane.data.filtringspecification.it;
import com.marouane.data.filtringspecification.book.application.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200Status_andBookList() throws Exception {
        // Given
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
    void shouldReturn200Status_andBookByCountry() throws Exception {
        //GET /api/cars?country=USA

        // Given
        // When
        mockMvc.perform(
                        get("/books?country=USA")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[*].country", equalTo("USA")));
    }

    @Test
    void shouldReturn200Status_andBookByCreateDate() throws Exception {
        //GET /api/cars?createDate=2019–11–11
        // Given
        // When
        mockMvc.perform(
                        get("/books?createDate=2019–11–11")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[*].createDate", equalTo("2019–11–11")));

    }

    @Test
    void shouldReturn200Status_andBookByCreateDateAsc() throws Exception {
        //GET /api/cars?sort=createDate,asc

        // When
        mockMvc.perform(
                        get("/books?sort=createDate,asc")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[*].createDate", stringContainsInOrder("2019–11–11", "2019–11–14", "2019–11–16")));
    }

    @Test
    void shouldReturn200Status_andBookByCreateDateDesc() throws Exception {
        //GET /api/cars?sort=createDate,desc
        // When
        mockMvc.perform(
                        get("/books?sort=createDate,desc")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[*].createDate", stringContainsInOrder("2019–11–16", "2019–11–14", "2019–11–11")));
    }

    @Test
    void shouldReturn200Status_andBookLimited() throws Exception {
        //GET /api/cars?limit=10

        // When
        mockMvc.perform(
                        get("/books?limit=10")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void shouldReturn200Status_andBookByOffset() throws Exception {
        //GET /api/cars?offset=2

        // When
        mockMvc.perform(
                        get("/books?offset=1")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[*].createDate", equalTo("2019–11–11")));
    }


    @Test
    void shouldReturn200Status_andBookByLimit_andBookByOffset() throws Exception {
        //GET /api/cars?country=USA&sort=createDate,desc&limit=100&offset=2

        // When
        mockMvc.perform(
                        get("/books?country=USA&sort=createDate,desc&limit=100&offset=2")
                                .accept(MediaType.APPLICATION_JSON)
                )
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$[*].createDate", equalTo("2019–11–11")));
    }

}
