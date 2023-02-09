package com.marouane.data.filtringspecification.book.application.controller;

import com.marouane.data.filtringspecification.book.domain.BookType;

import java.util.Set;


public record BookFilter(String name, String isbn, BookType bookType, String country, Integer stock, String createDate,
                        Set<String> sort) {


}
