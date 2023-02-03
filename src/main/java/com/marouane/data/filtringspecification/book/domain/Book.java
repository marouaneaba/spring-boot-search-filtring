package com.marouane.data.filtringspecification.book.domain;

import java.time.ZonedDateTime;

public record Book(String name, String isbn, BookType bookType, String country, ZonedDateTime createDate) {
}
