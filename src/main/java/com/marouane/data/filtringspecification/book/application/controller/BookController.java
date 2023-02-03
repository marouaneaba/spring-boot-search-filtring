package com.marouane.data.filtringspecification.book.application.controller;

import com.marouane.data.filtringspecification.book.domain.BookRepository;
import com.marouane.data.filtringspecification.book.domain.Book;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Book> get(@And({
            @Spec(path = "name", params = "name", spec = Like.class),
            @Spec(path = "isbn", params = "isbn", spec = Like.class),
            @Spec(path = "stock", params = "stock", spec = Equal.class),
            @Spec(path = "stock", params = "stockGt", spec = GreaterThan.class),
            @Spec(path = "type", params = "type", spec = Like.class),
            @Spec(path = "country", params = "country", spec = Like.class),
            @Spec(path = "createDate", params = "createDate", spec = Equal.class),
            @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
    }) Specification<Book> specification, Sort sort) {
        return bookRepository.searchBook(specification, sort);
    }
}
