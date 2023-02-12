package com.marouane.data.filtringspecification.book.infrastructure.book;

import com.marouane.data.filtringspecification.book.application.controller.BookFilter;
import com.marouane.data.filtringspecification.book.infrastructure.book.entity.BookEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public class BookCriteriaBuilder {

    private BookCriteriaBuilder() {}

    public static Example<BookEntity> buildBookSearchCriteriaByFilter(BookFilter bookFilter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues();

        BookEntity bookEntity =
                new BookEntity(
                        bookFilter.name(),
                        bookFilter.isbn(),
                        bookFilter.bookType(),
                        bookFilter.country(),
                        bookFilter.stock(),
                        ZonedDateTime.parse(bookFilter.createDate()));

        return Example.of(bookEntity, matcher);
    }

    public static Sort buildSortOrderByFilter(Set<String> sortFiled, Sort.Direction direction) {
        List<Sort.Order> ordersFilter = sortFiled
                .stream()
                .map(filed -> new Sort.Order(direction, filed))
                .toList();

        return Sort.by(ordersFilter);
    }
}
