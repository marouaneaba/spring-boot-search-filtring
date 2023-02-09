package com.marouane.data.filtringspecification.book.infrastructure.repository;

import com.marouane.data.filtringspecification.book.application.controller.BookFilter;
import com.marouane.data.filtringspecification.book.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

@DataJpaTest
@Sql(scripts = {"/data/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class BookDataBaseRepositoryTest {

    private BookDataBaseRepository bookDataBaseRepository;

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @BeforeEach
    void setUp() {
        bookDataBaseRepository = new BookDataBaseRepository(bookJpaRepository);
    }

    @Test
    void shouldSearchBook_whenUsingSpecificationCustom() {
        // Given
        BookFilter bookFilter =
                new BookFilter("jeton", null, null, null, Integer.valueOf(3), null, Set.of("stock"));

        // When
        List<Book> books = bookDataBaseRepository.searchBook(bookFilter);

        // Then
        Assertions.assertThat(books)
                .isNotEmpty();
    }

    @Test
    void shouldSearchBook_whenUsingQueryByExample() {
        // Given
        BookFilter bookFilter =
                new BookFilter("jeton", null, null, null, null, null, Set.of("stock"));

        // When
        List<Book> books = bookDataBaseRepository.searchBook(bookFilter);

        // Then
        Assertions.assertThat(books)
                .isNotEmpty();
    }

}