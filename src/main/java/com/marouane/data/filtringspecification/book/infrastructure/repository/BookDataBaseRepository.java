package com.marouane.data.filtringspecification.book.infrastructure.repository;

import com.marouane.data.filtringspecification.book.domain.Book;
import com.marouane.data.filtringspecification.book.domain.BookRepository;
import com.marouane.data.filtringspecification.book.infrastructure.entity.BookEntity;
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
}
