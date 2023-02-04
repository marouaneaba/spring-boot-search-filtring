package com.marouane.data.filtringspecification.book.domain;

import com.marouane.data.filtringspecification.book.application.controller.BookFilter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookRepository {

    List<Book> searchBook(Specification bookSpecification, Sort sort);

    List<Book> searchBook(BookFilter bookFilter);

}
