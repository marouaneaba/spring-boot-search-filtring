package com.marouane.data.filtringspecification.book.application.controller;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.Map;

public record BookSpecification(Sort sort) {
    public static Map toMap(Specification<BookSpecification> specification) {
        return new HashMap();
    }
}
