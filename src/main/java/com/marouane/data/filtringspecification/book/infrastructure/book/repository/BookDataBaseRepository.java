package com.marouane.data.filtringspecification.book.infrastructure.book.repository;

import com.marouane.data.filtringspecification.book.application.controller.BookFilter;
import com.marouane.data.filtringspecification.book.domain.Book;
import com.marouane.data.filtringspecification.book.domain.BookRepository;
import com.marouane.data.filtringspecification.book.infrastructure.book.BookCriteriaBuilder;
import com.marouane.data.filtringspecification.book.infrastructure.book.BookSpecification;
import com.marouane.data.filtringspecification.book.infrastructure.book.entity.BookEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDataBaseRepository implements BookRepository {

    private final BookJpaRepository bookJpaRepository;

    public BookDataBaseRepository(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Book> searchBook(Specification bookSpecification, Sort sort) {
        List<BookEntity> books = bookJpaRepository.findAll(bookSpecification, sort);
        return books.stream()
                .map(BookEntity::toBook)
                .toList();
    }

    @Override
    public List<Book> searchBook(BookFilter bookFilter) {
        Specification bookSpecification = new BookSpecification(bookFilter);
        List<BookEntity> bookEntities = bookJpaRepository.findAll(bookSpecification);

        return bookEntities.stream()
                .map(BookEntity::toBook)
                .toList();
    }

    @Override
    public List<Book> searchBookWithByExample(BookFilter bookFilter) {
        Example<BookEntity> exampleQuery = BookCriteriaBuilder.buildBookSearchCriteriaByFilter(bookFilter);

        Sort ordersFilter = BookCriteriaBuilder.buildSortOrderByFilter(bookFilter.sort(), Sort.Direction.DESC);

        List<BookEntity> bookEntities = bookJpaRepository.findAll(exampleQuery, ordersFilter);

        return bookEntities.stream()
                .map(BookEntity::toBook)
                .toList();
    }
}
