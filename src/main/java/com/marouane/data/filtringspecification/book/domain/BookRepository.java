package com.marouane.data.filtringspecification.book.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface BookRepository {

    List<Book> searchBook(Specification bookSpecification, Sort sort);

}
