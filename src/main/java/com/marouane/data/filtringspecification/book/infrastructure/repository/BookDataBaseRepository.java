package com.marouane.data.filtringspecification.book.infrastructure.repository;

import com.marouane.data.filtringspecification.book.application.controller.BookFilter;
import com.marouane.data.filtringspecification.book.domain.Book;
import com.marouane.data.filtringspecification.book.domain.BookRepository;
import com.marouane.data.filtringspecification.book.infrastructure.BookSpecification;
import com.marouane.data.filtringspecification.book.infrastructure.entity.BookEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
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
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        BookEntity bookEntity = new BookEntity(bookFilter.name(), bookFilter.isbn(), bookFilter.bookType(), bookFilter.country(), bookFilter.stock(), ZonedDateTime.parse(bookFilter.createDate()));
        Example<BookEntity> exampleQuery = Example.of(bookEntity, matcher);
        List<Sort.Order> orders = bookFilter.sort().stream().map(sortFiled -> new Sort.Order(Sort.Direction.DESC, sortFiled)).toList();

        List<BookEntity> bookEntities = bookJpaRepository.findAll(exampleQuery, Sort.by(orders));

        return bookEntities.stream()
                .map(BookEntity::toBook)
                .toList();
    }
}
