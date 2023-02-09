package com.marouane.data.filtringspecification.book.infrastructure.entity;

import com.marouane.data.filtringspecification.book.domain.Book;
import com.marouane.data.filtringspecification.book.domain.BookType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column
    private String name;

    @Column
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BookType bookType;

    @Column
    private String country;

    @Column
    private Integer stock;

    @Column
    private ZonedDateTime createDate;

    public BookEntity() {
    }

    public BookEntity(String name, String isbn, BookType bookType, String country, int stock, ZonedDateTime createDate) {
        this.name = name;
        this.isbn = isbn;
        this.bookType = bookType;
        this.country = country;
        this.stock = stock;
        this.createDate = createDate;
    }

    public static Book toBook(BookEntity from) {
        return new Book(from.name, from.isbn, from.bookType, from.country, from.stock, from.createDate);
    }

}
